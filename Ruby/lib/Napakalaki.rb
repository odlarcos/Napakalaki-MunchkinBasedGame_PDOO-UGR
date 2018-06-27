# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  require_relative "CardDealer.rb"
  require_relative "CombatResult.rb"
  require_relative "Player"
  require "singleton"

  class Napakalaki

    include Singleton

    attr_accessor :currentPlayer, :players, :currentMonster, :dealer

    def initialize
      @players = Array.new
      @currentPlayer = nil
      @currentMonster = nil
      @dealer = CardDealer.instance
    end

    def getCurrentMonster
      @currentMonster
    end
    
    def getCurrentPlayer
      @currentPlayer
    end
    
    def initPlayers(names)
      names.each do |x|
        @players << Player.new(x)
      end
    end

    def nextPlayer
      
      if @currentPlayer == nil
        siguiente = rand(@players.size)
      else
        siguiente = ( @players.index(@currentPlayer)+1) % @players.size
      end
      
      @currentPlayer = @players[siguiente]
    end

    def nextTurnAllowed
      return true if @currentPlayer.nil?
      @currentPlayer.validState
    end

    def setEnemies()

      pos_enemigo = rand(@players.size)

      @players.each do |e|
        pos_jugador = @players.index(e)
        while ( pos_jugador == pos_enemigo)
          pos_enemigo = rand(@players.size)
        end
        e.enemy = @players[pos_enemigo]

      end

    end


    def developCombat()
      resultado = @currentPlayer.combat(@currentMonster)
      @dealer.giveMonsterBack(@currentMonster)

      if(resultado == CombatResult::LOSEANDCONVERT)
        c = @dealer.nextCultist()
        p = CultistPlayer.new(c, @currentPlayer)
        pos = @players.index(@currentPlayer)
        @players[pos] = p
        
        @players.each do |x|
          if(x.enemy() == @currentPlayer)
            x.enemy(p)
          end
        end
        
        @currentPlayer = p
        
      end
      
      return resultado
    end

    def discardVisibleTreasures(treasures)

      treasures.each do |t|
        @currentPlayer.discardVisibleTreasure(t)
      end

    end

    def discardHiddenTreasures(treasures)

      treasures.each do |t|
        @currentPlayer.discardHiddenTreasure(t)
      end
    end

    def makeTreasuresVisible(treasures)
      treasures.each do |t|
        @currentPlayer.makeTreasureVisible(t)
      end
    end

    def initGame(players)
      initPlayers(players)
      setEnemies
      @dealer.initCards
      nextTurn
    end

    def nextTurn
      stateOK = nextTurnAllowed
      if (stateOK)
        @currentMonster=@dealer.nextMonster
        @currentPlayer=nextPlayer
        dead=@currentPlayer.dead
        if (dead)
          @currentPlayer.initTreasures
        end  
      end
      stateOK
    end

    def endOfGame(result)
      return result = CombatResult::WINGAME
    end

  end
end
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "Prize.rb"
require_relative "BadConsequence.rb"

module NapakalakiGame
  class Monster

    attr_accessor :name, :combatLevel, :prize, :bc, :levelChangeAgainstCultistPlayer

    def initialize(name, combatLevel, prize, bc, levelChangeAgainstCultistPlayer)
      @name = name
      @combatLevel = combatLevel
      @prize = prize
      @bc = bc
      @levelChangeAgainstCultistPlayer = levelChangeAgainstCultistPlayer
    end
    
    def self.newMonster(name, combatLevel, prize, bc)
      new(name, combatLevel, prize, bc, 0)
    end
    
    def self.newCultist(name, combatLevel, prize, bc, levelChangeAgainstCultistPlayer)
      new(name, combatLevel, prize, bc, levelChangeAgainstCultistPlayer)
    end
    
    def getCombatLevelAgainstCultistPlayer()
      return @combatLevel + @levelChangeAgainstCultistPlayer
    end
    
    def getLevelsGained
      return @prize.level
    end
    
    def getTreasuresGained
      @prize.treasures
    end
    
    def to_s
      "\nName: #{@name} \nCombat level: #{@combatLevel} \nPremio:" + @prize.to_s + "\nMal rollo: " + @bc.to_s
    end

  
  end
end
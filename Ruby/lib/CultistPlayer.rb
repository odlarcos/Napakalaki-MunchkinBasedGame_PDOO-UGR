# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  require_relative "Cultist"
  require_relative "Player.rb"
  
  class CultistPlayer < Player
        @@totalCultistPlayers = 0
    
    def initialize( c, p ) 
       @myCultistCard = c
       @@totalCultistPlayers += 1
       self.copy(p)
    end
    
    def self.getTotalCultistPlayers
      @@totalCultistPlayers
    end
    
    protected
    def getCombatLevel()
      return (super + 0.7 * super + @myCulistCard.getLevelsGained*@@totalCultistPlayers)
        #return (Player.instance_method(:getCombatLevel).bind(self).call*1.7+@myCultistCard.gainedLevels*@@totalCultistPlayers).to_i
    end
    
    protected
    def getOponentLevel(m)
      m.getCombatLevelAgainstCultistPlayer()
    end
    
    protected
    def shouldConvert()
      false
    end
    
    protected
    def giveMeaTreasure()
      @visibleTreasures.delete_at(rand(@visibleTreasures.size))
    end
    
    protected
    def canYouGiveMeATreasure()
      !@visibleTreasures.empty?
    end
    
    
  end

end
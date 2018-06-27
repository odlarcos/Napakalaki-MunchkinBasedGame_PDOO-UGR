# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  
  require_relative "CultistPlayer"

  class Cultist
    
    attr_reader :gainedLevels
    
    def initialize(n, gainedLevels)
      @name = n
      @gainedLevels = gainedLevels
    end
  end

end
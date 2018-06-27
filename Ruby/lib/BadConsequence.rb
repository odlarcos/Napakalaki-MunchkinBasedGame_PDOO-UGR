# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  require_relative "CardDealer.rb"
  require_relative "TreasureKind.rb"
  class BadConsequence

    attr_accessor :text
    attr_accessor :levels
    
    private_class_method :new
    @@MAXTREASURES = 10

    def initialize(aText, someLevels)
      @text = aText
      @levels = someLevels
    end
    
    def self.MAXTREASURES
      @@MAXTREASURES
    end

    def isEmpty()

    end

    def substractVisibleTreasure(treasure) 
      
    end

    def substractHiddenTreasure(treasure)
      
    end  

    def adjustToFitTreasureLists(v, h)
      
    end

    def to_s
      "#{@text}"
    end

  end
end
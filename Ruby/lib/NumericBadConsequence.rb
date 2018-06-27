# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  require_relative 'BadConsequence.rb'
  
  class NumericBadConsequence < BadConsequence
    
    attr_reader :nVisibleTreasures, :nHiddenTreasures
    public_class_method :new

    def initialize(text, levels, visible, hidden)
      super(text, levels)
      @nVisibleTreasures = visible
      @nHiddenTreasures = hidden
    end
    
    def isEmpty()
       @nVisibletreasures == 0 && @nHiddenTreasures == 0
    end
    
    def substractVisibleTreasure(t)
      if @nVisibleTreasures != 0
        @nVisibleTreasures -= 1
      end
    end
     
    def substractHiddenTreasure(t)
      if @nHiddenTreasures != 0
        @nHiddenTreasures -= 1
      end
    end
    
    def adjustToFitTreasureLists(v, h)
      NumericBadConsequence.new("",0,[v.size,@nVisibleTreasures].min, \
      [h.size,@nHiddenTreasures].min)
    end
    
    def to_s
      super  +
      "Tesoros visibles: #{@nVisibleTreasures}\n" +
      "Tesoros ocultos: #{@nHiddenTreasures}\n"
    end 
    
 end
end
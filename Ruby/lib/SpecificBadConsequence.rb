# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  require_relative 'BadConsequence.rb'

  class SpecificBadConsequence < BadConsequence
    
    attr_accessor :specificVisibleTreasures, :specificHiddenTreasures
    public_class_method :new
    
    def initialize(text, levels, tVisible, tHidden)
      super(text, levels)
      @specificVisibleTreasures = tVisible
      @specificHiddenTreasures = tHidden
    end
    
    def is_empty()
      @levels==0 && @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty? 
    end

    def substractVisibleTreasure(t)
      if(!((i=@specificVisibleTreasures.index(t.type)).nil?))
        @specificVisibleTreasures.delete_at(i)
      end
    end

    def substractHiddenTreasure(t)
      if(!((i=@specificHiddenTreasures.index(t.type)).nil?))
        @specificHiddenTreasures.delete_at(i)
      end
    end
    
    def adjustToFitTreasureList(v,h)
       SpecificBadConsequence.new("",0, \
       sameSpecific(v,@specificVisibleTreasures), sameSpecific(h,@specificHiddenTreasures))
    end
  
  def to_s
    super +
    "Tesoros visibles:#{@specificVisibleTreasures}\n" +
    "Tesoros ocultos: #{@specificHiddenTreasures}\n" 
  end
    
  end

end
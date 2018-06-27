# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  require_relative 'Player.rb'
  require_relative 'BadConsequence.rb'
  require_relative 'NumericBadConsequence.rb'
  
  class DeathBadConsequence < NumericBadConsequence
    
    attr_accessor :death
    
    def initialize(text)
      super(text,10, @@MAXTREASURES, @@MAXTREASURES)
    end
    
  end

end
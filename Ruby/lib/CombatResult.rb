# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "Prize.rb"
require_relative "BadConsequence.rb"
require_relative "Monster.rb"
require_relative "Napakalaki"

module NapakalakiGame
  
module CombatResult
    WINGAME = :wingame
    WIN = :win
    LOSE = :lose
    LOSEANDCONVERT = :loseandconvert
end

end #module
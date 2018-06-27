# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
class Prize
  
  attr_accessor :treasures
  attr_accessor :level
  
  def initialize(tesoros, niveles)
     @treasures = tesoros
     @level = niveles
  end
  
  def to_s
    "\n\tTesoros ganados: #{@treasures}\n\tNiveles ganados: #{@level}"
  end
  
end
end
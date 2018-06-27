# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
class Treasure
  
  attr_reader :nombre
  attr_reader :bonus
  attr_reader :type
  
  def initialize(n, b, t)
    @nombre = n
    @bonus = b
    @type = t
  end
  
  def to_s
    "\nName: #{@nombre} \nTipo: #{@type} \nBonus: #{@bonus}"
  end
  
end
end
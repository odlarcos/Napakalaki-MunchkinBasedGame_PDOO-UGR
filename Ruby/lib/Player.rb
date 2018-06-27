# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
class Player
  
  require_relative "Napakalaki.rb"
  require_relative "Dice.rb"
  require_relative "CombatResult.rb"
  attr_reader :dead, :name, :level, :visibleTreasures, :hiddenTreasures, :pendingBadConsequence, :dead
  attr_accessor :enemy
  @@MAXLEVEL = 10
  
  def initialize(name, bc=nil, player=nil)
      @name = name
      @dead = true
      @canISteal = true
      @level = 1 
      @visibleTreasures = Array.new
      @hiddenTreasures = Array.new
      @pendingBadConsequence = bc
      @enemy = player  
    end
    
  def copy(p)
    @enemy=p.enemy
    @name=p.name
    @level=p.level
    @dead=p.dead
    @canISteal=p.canISteal
    @hiddenTreasures=Array.new(p.hiddenTreasures)
    @visibleTreasures=Array.new(p.visibleTreasures)
    @pendingBadConsequence=p.pendingBadConsequence
  end
  
  # Definidos por necesidad del Main
  def to_s
    @name
  end
  
  def getVisibleTreasures 
    @visibleTreasures
  end
  
  def getHiddenTreasures
    @hiddenTreasures
  end
  
  public
  def getCombatLevel()
    l=@level;
    @visibleTreasures.each{|t| l=l+t.bonus}
    l
  end
  
  
  def getOponentLevel(m)
    m.combatLevel
  end
  
  
  def getEnemy()
    @enemy
  end
  
  def setEnemy(e)
    @enemy = e
  end

  
  def bringToLife
    @dead = false
  end
  
  def incrementLevels(l)
      @level = @level + l
  end
  
  def decrementLevels(l)
    @level = @level - l
    if @level < 1
      @level = 1
    end
  end
  
  def setPendingBadConsequence(bc)
    @pendingBadConsequence = bc
  end
  
  def applyPrize(m)
    nLevels = m.getLevelsGained
    incrementLevels(nLevels)
    nTreasures = m.getTreasuresGained
    
    if(nTreasures > 0)
      dealer = CardDealer.instance
      
      nTreasures.times do
        treasure = dealer.nextTreasure()
        @hiddenTreasures << treasure
      end
    end
    
  end

  def applyBadConsequence(m)
    badConsequence = m.bc
    nlevels = badConsequence.levels
    decrementLevels(nlevels)
    
    pendingBad = badConsequence.adjustToFitTreasureLists(@visibleTreasures, @hiddenTreasures)
    
    setPendingBadConsequence(pendingBad)
  end
  
  def canMakeTreasureVisible(t)
    return false if (!@pendingBadConsequence.nil? && !@pendingBadConsequence.isEmpty)
    if (t.type==TreasureKind::BOTHHANDS)
      @visibleTreasures.each {|t1| return false if (t1.type==TreasureKind::BOTHHANDS || t1.type==TreasureKind::ONEHAND)}
    elsif (t.type==TreasureKind::ONEHAND)
      contador=0;
      @visibleTreasures.each do |t1|
          contador += 2 if t1.type==TreasureKind::BOTHHANDS
          contador += 1 if t1.type==TreasureKind::ONEHAND
      end
      return contador<2
    else
      @visibleTreasures.each{|t1| return false if t.type==t1.type}
    end
    true
    end
    
    #Este método lo he hecho personalmente para no duplicar codigo en el método anterior
    def is_treasure_kind_in_use(type) 

      result = false
      @visibleTreasures.each do |tv|
        if type == tv.type then

          result = true
          break

        end

      end
      return result
        
    end
  
  def howManyVisibleTreasures(t)
    contador = 0
    i = 0
    while i < @visibleTreasures.size
      if t == @visibleTreasures[i].type
        contador = contador +1 
      end
    end
    contador
  end
  
  def dieIfNoTreasures
    if @visibleTreasures.size == 0 && @hiddenTreasures.size == 0
      @dead=true
    end
  end
  
  def combat (m)
    
    myLevel = @level
    monsterLevel = getOponentLevel(m)
    
    if !canISteal
      dice = Dice.instance
      number = dice.nextNumber
      if number < 3
        monsterLevel += enemy.combatLevel
      end
    end
    
    if (myLevel >= monsterLevel)
      applyPrize(m)
      if @level >= @@MAXLEVEL
        result = CombatResult::WINGAME
      else
        result = CombatResult::WIN
      end
    else
      applyBadConsequence(m)
      convert = shouldConvert()
      if (convert)
        result = CombatResult::LOSEANDCONVERT
      else
        result = CombatResult::LOSE
      end
    end
    
    return result
  end
  
  def discardVisibleTreasure(t)
    @visibleTreasures.delete(t)
    if @pendingBadConsequence != nil && !@pendingBadConsequence.isEmpty
                @pendingBadConsequence.substractVisibleTreasure(t) #FALTA
        dieIfNoTreasures
    end
  end
  
  def discardHiddenTreasure(t)
    @visibleTreasures.delete(t)
    if @pendingBadConsequence != nil && !@pendingBadConsequence.isEmpty
                @pendingBadConsequence.substractVisibleTreasure(t) #FALTA
        dieIfNoTreasures
    end
  end
  
  def validState
    return ((@pendingBadConsequence.nil? || @pendingBadConsequence.isEmpty) && @hiddenTreasures.length<=4)
  end
  
  def initTreasures()
    dealer = CardDealer.instance
    dice = Dice.instance
    bringToLife()
    treasure = dealer.nextTreasure()
   
    @hiddenTreasures << treasure
    number = dice.nextNumber()
    
    if(number > 1)
      treasure = dealer.nextTreasure()
      @hiddenTreasures << treasure
    end
    
    if(number == 6)
      treasure = dealer.nextTreasure()
      @hiddenTreasures << treasure
    end
    
  end
  
  def stealTreasure()
    canI = canISteal()
    
    if(canI)
      canYou = @enemy.canYouGiveMeATreasure()
      if(canYou)
        treasure = @enemy.giveMeATreasure()
        @hiddenTreasures << treasure
        return treasure
      end
    end
    
    return nil
  end

  
  def setEnemy(e)
    @enemy = e
  end
  
  def giveMeATreasure()
    num = rand(@hiddenTreasures.length)
    random_treasure = @hiddenTreasures[num]
    
    return random_treasure
  end
  
  def canISteal
    @canISteal
  end
  
  def canYouGiveMeATreasure
    !@hiddenTreasures.empty?
  end
  
  def haveStolen
    @canISteal = false
  end
  
  def discardAllTreasures()
    @visibleTreasures.each do |tv|
      discardVisibleTreasure(tv)
    end
    
    @hiddenTreasures.each do |th|
      discardHiddenTreasure(th)
    end
    
  end

  def makeTreasureVisible(t)
        canI = canMakeTreasureVisible(t);
        if canI
          @visibleTreasures << (t);
          @hiddenTreasures.delete(t);
        end
  end
  
  def shouldConvert
    dice = Dice.instance
    number = dice.nextNumber
    
    if(number == 1)
      return true
    else
      return false
    end
    
  end
  
  protected :canYouGiveMeATreasure, :giveMeATreasure, :getCombatLevel, :getOponentLevel
  protected :shouldConvert
  
end
end
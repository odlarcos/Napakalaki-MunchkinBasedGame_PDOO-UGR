# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  
  require "singleton"
  require_relative "Prize.rb"
  require_relative "BadConsequence.rb"
  require_relative "SpecificBadConsequence.rb"
  require_relative "NumericBadConsequence.rb"
  require_relative "DeathBadConsequence.rb"
  require_relative "Treasure.rb"
  require_relative "Player.rb"
  require_relative "TreasureKind.rb"
  require_relative "Monster.rb"
  require_relative "Cultist.rb"

  class CardDealer
    
    include Singleton

    attr_accessor :unusedTreasures, :usedTreasures, :unusedMonsters, :usedMonsters, :unusedCultists
    def initialize()
      @unusedTreasures = Array.new
      @unusedMonsters = Array.new
      @usedTreasures = Array.new
      @usedMonsters = Array.new
      @unusedCultists = Array.new
    end

    def initTreasureCardDeck()
      # Creacion de tesoros

      @unusedTreasures << Treasure.new('Si mi amo', 4, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new('Botas de investigacion', 3, TreasureKind::SHOES)
      @unusedTreasures << Treasure.new('Capucha de Cthulhu', 3, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new('A prueba de babas', 2, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new('Botas de lluvia acida', 1, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Casco de minero', 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new('Ametralladora ACME', 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Camiseta de la ETSIIT', 1, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new('Clavo de rail ferroviario', 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Cuchillo de sushi arcano', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Fez alopodo', 3, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new('Hacha prehistorica', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('El aparato del Pr. Tesla', 4, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new('Gaita', 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Insecticida', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Escopeta de 3 canones', 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Garabato mistico', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('La rebeca metalica', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Lanzallamas', 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Necro-comicon', 1, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Necronomicon', 5, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Linterna 2 manos', 3, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Necro-gnomicon', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Necrotelecom', 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new('Mazo de los antiguos', 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Necro-playboycon', 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Porra preternatural', 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Shogulador', 1, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new('Varita de atizamiento', 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new('Tentaculo de pega', 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new('Zapato deja-amigos', 1, TreasureKind::SHOES)
    end

    def initMonsterCardDeck()
      # Creacion de Byakhees de bonanza
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('Pierdes tu armadura visible y otra oculta',0 , TreasureKind::ARMOR, TreasureKind::ARMOR)
      @unusedMonsters << Monster.newMonster('Byakhees de bonanza', 8, prize, badConsequence)

      # Creacion de Tenochtitlan
      prize = Prize.new(1, 1)
      badConsequence = SpecificBadConsequence.new('Embobados con el lindo primigenio te descartas de tu casco visible',0 , TreasureKind::HELMET, Array.new)
      @unusedMonsters << Monster.newMonster('Tenochtitlan', 2, prize, badConsequence)

      # Creacion de El sopor de Dunwich
      prize = Prize.new(1, 1)
      badConsequence = SpecificBadConsequence.new('El primordial bostezo contagioso. Pierdes el calzado visible', 0, TreasureKind::SHOES, Array.new)
      @unusedMonsters << Monster.newMonster('El sopor de Dunwich', 2, prize, badConsequence)

      # Creacion de Demonios de Magaluf
      prize = Prize.new(4, 1)
      badConsequence = SpecificBadConsequence.new('Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta', 0, [TreasureKind::ONEHAND], [TreasureKind::ONEHAND])
      @unusedMonsters << Monster.newMonster('Demonios de Magaluf', 2, prize, badConsequence)

      # Creacion de El gorrón en el umbral
      prize = Prize.new(3, 1)
      badConsequence = NumericBadConsequence.new('Pierdes todos tus tesoros visibles', 0 , 5, 0)
      @unusedMonsters << Monster.newMonster('El gorron en el umbral', 13, prize, badConsequence)

      # Creacion de H.P Munchcraft
      prize = Prize.new(2, 1)
      badConsequence = SpecificBadConsequence.new('Pierdes la armadura visible', 0, TreasureKind::ARMOR, Array.new)
      @unusedMonsters << Monster.newMonster('H.P Munchcraft', 6, prize, badConsequence)

      # Creacion de Necrófago
      prize = Prize.new(1, 1)
      badConsequence = SpecificBadConsequence.new('Sientes bichos bajo la ropa. Descarta la armadura visible', 0, TreasureKind::ARMOR, Array.new)
      @unusedMonsters << Monster.newMonster('Necrofago', 13, prize, badConsequence)

      # Creacion de El rey de rosado
      prize = Prize.new(3, 2)
      badConsequence = NumericBadConsequence.new('Pierdes 5 niveles y 3 tesoros visibles', 5, 3, 0)
      @unusedMonsters << Monster.newMonster('El rey de rosado', 11, prize, badConsequence)

      # Creacion de Flecher
      prize = Prize.new(1, 1)
      badConsequence = NumericBadConsequence.new('Toses los pulmones y pierdes 2 niveles', 2, 0, 0)
      @unusedMonsters << Monster.newMonster('Flecher', 2, prize, badConsequence)

     # Creacion de Los hondos
     prize = Prize.new(2,1)
     badConsequence = DeathBadConsequence.new('Estos monstruos resultan bastante superficiales y te aburren mortalemente. Estas muerto')
     @unusedMonsters << Monster.newMonster('Los hondos', 8, prize, badConsequence)

     # Creacion de Semillas de Cthulhu
     prize = Prize.new(3,1)
     badConsequence = NumericBadConsequence.new('Pierdes 2 niveles y 2 tesoros ocultos', 0, 0, 2)
     @unusedMonsters << Monster.newMonster('Semilas de Cthulhu', 4, prize, badConsequence)

     # Creacion de Dameargo
     prize = Prize.new(2, 1)
     badConsequence = SpecificBadConsequence.new('Te intentas escaquear. Pierdes una mano visible', 0, Array.new, [TreasureKind::ONEHAND])
     @unusedMonsters << Monster.newMonster('Dameargo', 1, prize, badConsequence)

     # Creacion de Pollipólipo volante
     prize = Prize.new(2,1)
     badConsequence = NumericBadConsequence.new('Da mucho asquito. Pierdes 3 niveles', 3, 0, 0)
     @unusedMonsters << Monster.newMonster('Pollipolipo volante',3 , prize, badConsequence)

      # Creacion de Yskhtihyssg-Goth
      prize = Prize.new(3,1)
      badConsequence = DeathBadConsequence.new('No le hace gracia que pronuncien mal su nombre. Estas muerto')
      @unusedMonsters << Monster.newMonster('Yskhtihyssg-Goth', 14, prize, badConsequence)

      # Creacion de Familia feliz
      prize = Prize.new(3,1)
      badConsequence = DeathBadConsequence.new('La familia te atrapa. Estas muerto')
      @unusedMonsters << Monster.newMonster('Familia feliz', 1, prize, badConsequence)

      # Creacion de Roboggoth
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible', 2, [TreasureKind::BOTHHANDS], Array.new)
      @unusedMonsters << Monster.newMonster('Roboggoth', 8, prize, badConsequence)

      #Creacion de El espía sordo
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('Te asusta en la noche. Pierdes un casco visible', 0, [TreasureKind::HELMET], Array.new)
      @unusedMonsters << Monster.newMonster('El espia sordo', 5, prize, badConsequence)

      #Creacion de Tongue
      prize = Prize.new(2,1)
      badConsequence = NumericBadConsequence.new('Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles', 2, 5, 0)
      @unusedMonsters << Monster.newMonster('Tongue', 19, prize, badConsequence)

      #Creacion de Bicéfalo
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos', 3, [TreasureKind::ONEHAND, TreasureKind::BOTHHANDS], Array.new)
      @unusedMonsters << Monster.newMonster('Bicefalo', 21, prize, badConsequence)
      
      #CREACION DE CULTISTS
      
      #Creacion de El mal indecible impronunciable
      prize = Prize.new(3,1)
      badConsequence = SpecificBadConsequence.new('Pierdes 1 mano visible', 0, [TreasureKind::ONEHAND], Array.new)
      @unusedMonsters << Monster.newCultist('El mal indecible impronunciable', 10, prize, badConsequence, -2)
      
      #Creacion de Testigos Oculares
      prize = Prize.new(2,1)
      badConsequence = NumericBadConsequence.new('Pierdes todos tus tesoros visibles. Jajaja', 0 , 5, 0)
      @unusedMonsters << Monster.newCultist('Testigos oculares', 8, prize, badConsequence, 2)
      
      #Creacion de El gran cthulhu
      prize = Prize.new(2,5)
      badConsequence = DeathBadConsequence.new('Hoy no es tu dia de suerte. Mueres')
      @unusedMonsters << Monster.newCultist('El gran cthulhu', 20, prize, badConsequence, 4)
      
      #Creacion de Serpiente Político
      prize = Prize.new(2,1)
      badConsequence = NumericBadConsequence.new('Tu gobierno te recorta 2 niveles', 2, 0, 0)
      @unusedMonsters << Monster.newCultist('Serpiente Politico', 8, prize, badConsequence, -2)
      
      #Creacion de Felpuggoth
      prize = Prize.new(1, 1)
      badConsequence = SpecificBadConsequence.new('Pierdes tu casco y tu armadura visible. Piernes tus manos ocultas', 0, [TreasureKind::HELMET, TreasureKind::ARMOR], [TreasureKind::BOTHHANDS])
      @unusedMonsters << Monster.newCultist('Felpuggoth', 2, prize, badConsequence, 5)
      
      #Creacion de Shoggoth
      prize = Prize.new(4,2)
      badConsequence = NumericBadConsequence.new('Pierdes 2 niveles', 2, 0, 0)
      @unusedMonsters << Monster.newCultist('Shoggoth', 16, prize, badConsequence, -4)
      
      #Creacion de Lolitagooth
      prize = Prize.new(1,1)
      badConsequence = NumericBadConsequence.new('Pintalabios negro. Pierdes 2 niveles', 2, 0, 0)
      @unusedMonsters << Monster.newCultist('Lolitagooth', 2, prize, badConsequence, 3)

    end
    
    def initCultistCardDeck()
        @unusedCultists << Cultist.new('Sectario', 1)
        @unusedCultists << Cultist.new('Sectario', 2)
        @unusedCultists << Cultist.new('Sectario', 1)
        @unusedCultists << Cultist.new('Sectario', 2)
        @unusedCultists << Cultist.new('Sectario', 1)
        @unusedCultists << Cultist.new('Sectario', 1) 
    end
    
    def shuffleTreasures()
      @unusedTreasures = @unusedTreasures.shuffle
    end

    def shuffleMonsters()
      @unusedMonsters = @unusedMonsters.shuffle
    end
    
    def shuffleCultists()
      @unusedCultists = @unusedCultists.shuffle
    end

    def nextTreasure()

      if(@unusedTreasures.size == 0)
    
          @unusedTreasures = @usedTreasures
        shuffleTreasures()
        @usedTreasures.clear
      end
      
      t = @unusedTreasures[0]
      
      #giveTreasureBack(t)
      @unusedTreasures.delete(t)
    
      return t  

    end

    def nextMonster()

      if(@unusedMonsters.size == 0)
        @usedMonsters.each do |um|
          @unusedMonsters << um
        end
        shuffleMonsters()
        @usedMonsters.clear
      end

      m = @unusedMonsters[0]
      
      #puts m.to_s
      @usedMonsters << m
      @unusedMonsters.delete(m)

      return m
    end
    
    def nextCultist
      c = @unusedCultists.pop()
      return c
    end


    def giveTreasureBack(t)
      @usedTreasures << t
    end

    def giveMonsterBack(monster)
      @usedMonsters << monster
    end
    

    def initCards()
      initMonsterCardDeck()
      initCultistCardDeck()
      initTreasureCardDeck()
      shuffleTreasures()
      shuffleMonsters()
      shuffleCultists()
    end

  end
end
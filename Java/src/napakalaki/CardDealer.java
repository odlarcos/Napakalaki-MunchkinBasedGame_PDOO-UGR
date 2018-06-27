/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author jaime
 */
public class CardDealer {
    private static CardDealer instance = null;
    private ArrayList<Treasure> unusedTreasures;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Cultist> unusedCultists;
  
    private CardDealer(){
        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList();
        unusedMonsters = new ArrayList();
        usedMonsters = new ArrayList();
        unusedCultists = new ArrayList();
    }
    
    public static CardDealer getInstance(){
        if(instance == null){
            instance = new CardDealer();
        }
        return instance;
    }

    private void initTreasureCardDeck(){
        //Creacion de los tesoros
        
        unusedTreasures.add(new Treasure("Sí mi amo", 4, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigación", 3, TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Casco minero", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora ACME", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Camiseta de la ETSIIT", 1, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehístórica", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 4, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Garabato místico", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La rebeca metálica", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-comicón", 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón", 5, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 3, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-gnomicón", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necro-playboycón", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Porra preternatural", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentáculo de pega", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOES));
    }
    
    private void initMonsterCardDeck(){
        Prize p;
        BadConsequence b;
        
         //Creacion Byakhees de bonanza
        b = new SpecificBadConsequence("Pierdes tu armadura visible y otra oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Byakhees de bonanza", 8, p, b));
        
        //Creacion Tenochtitlan
        b = new SpecificBadConsequence("Embobados con el lindo primigenio te descartas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Tenochtitlan", 2, p, b));
        
        //Creacion El sopor de Dunwich
        b = new SpecificBadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0, new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList());
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, p, b));
        
        //Creacion Demonios de Magaluf
        b = new SpecificBadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        p = new Prize(4,1);
        unusedMonsters.add(new Monster("Demonios de Magaluf", 2, p, b));
        
        //Creacion El gorrón en el umbral
        b = new NumericBadConsequence("Pierdes todos tus tesoros visibles",0 , 5, 0);
        p = new Prize(3,1);
        unusedMonsters.add(new Monster("El gorrón en el umbral", 13, p, b));
        
        //Creacion H.P.Munchcraft
        b = new SpecificBadConsequence("Pierdes la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, p, b));
        
        //Creacion Necrófago
        b = new SpecificBadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Necrófago", 13, p, b));
        
        //Creacion El rey de rosado
        b = new NumericBadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        p = new Prize(3,2);
        unusedMonsters.add(new Monster("El rey de rosado", 11, p, b));
        
        //Creacion Flecher
        b = new NumericBadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Flecher", 2, p, b));
        
        //Creación Los hondos
        b = new DeathBadConsequence("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto", true);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Los hondos", 8, p, b));
        
        //Creación Semillas Cthulhu
        b = new NumericBadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2,0,2);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, p, b));
        
        //Creación Dameargo
        b = new SpecificBadConsequence("Te intentas escaquear. Pierdes una mano visible.", 
                0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Dameargo", 1, p, b));
        
        //Creación Pollipólipo volante
        b = new NumericBadConsequence("Da mucho asquito, pierdes 3 niveles", 3, 0, 0);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Pollipólipo volante", 3, p, b));
        
        //Creación Yskhtihyssg-Goth
        b = new DeathBadConsequence("No le hace gracia que pronuncien mal su nombre. Estas muerto", true);
        p = new Prize(3,1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth",14, p, b));
        
        //Creación Familia Feliz
        b = new DeathBadConsequence("La familia te atrapa. Est́as muerto.", true);
        p = new Prize(3,1);
        unusedMonsters.add(new Monster("Familia feliz", 1, p, b));
        
        //Creación Roboggoth
        b = new SpecificBadConsequence("La quinta directiva primaria te obliga a perder 2 niveles "
                + "y un tesoro 2 manos visible", 2, new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList());
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Roboggoth",8, p, b));
        
        //Creación El espía sordo
        b = new SpecificBadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0,
        new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("El espía sordo",5, p, b));
        
        //Creación Tongue
        b = new NumericBadConsequence("Menudo susto te llevas. Pierdes"
                + " 2 niveles y 5 tesoros visibles.",2,5,0);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Tongue",19, p ,b));
        
        //Creación Bicéfalo
        b = new SpecificBadConsequence("Te faltan manos para tanta cabeza. "
                + "Pierdes 3 niveles y tus tesoros visibles de las manos.", 3, 
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND,TreasureKind.BOTHHANDS)), 
                new ArrayList());
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Bicéfalo",21, p, b));
        
        // SECTARIOS
        
        b = new SpecificBadConsequence("Pierdes 1 mano visible", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), 
                new ArrayList());
        p = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable",10, p, b, -2));
        
        b = new NumericBadConsequence("Pierdes tus tesoros visibles. Jajaja.", 0, 5, 0);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Testigos Oculares",6, p, b, 2));
        
        b = new DeathBadConsequence("Hoy no es tu d́ıa de suerte. Mueres.", true);
        p = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu",20, p, b, 4));
        
        b = new NumericBadConsequence("Tu gobierno te recorta 2 niveles.", 2,0,0);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Serpiente Pol ́ıtico",8, p, b, -2));
        
        b = new SpecificBadConsequence("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.", 
                0, new ArrayList(Arrays.asList(TreasureKind.HELMET,TreasureKind.ARMOR)), 
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND,TreasureKind.BOTHHANDS)));
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Felpuggoth",2, p, b, 5));
        
        b = new NumericBadConsequence("Pierdes 2 niveles.", 2,0,0);
        p = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth",16, p, b, -4));
        
        b = new NumericBadConsequence("Pintalabios negro. Pierdes 2 niveles.", 2,0,0);
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Lolitagooth",2, p, b, 3));
        
    }
    
    private void initCultistCardDeck(){
        unusedCultists.add(new Cultist("Sectario", 1));
        unusedCultists.add(new Cultist("Sectario", 2));
        unusedCultists.add(new Cultist("Sectario", 1));
        unusedCultists.add(new Cultist("Sectario", 2));
        unusedCultists.add(new Cultist("Sectario", 1));
        unusedCultists.add(new Cultist("Sectario", 1));    
    }
    
    private void suffleCultist(){
        Collections.shuffle(unusedCultists);
    }
    
    private void shuffleTreasures(){
        Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonsters(){
        Collections.shuffle(unusedMonsters);
    }
    
    public Cultist NextCultist(){
        Cultist c = unusedCultists.get(0);
        unusedCultists.remove(c);
        
        return c;
    }
    
    public Treasure nextTreasure(){
        Treasure t;
        
        if(unusedTreasures.isEmpty()){
            
            for (int i = 0; i < usedTreasures.size(); i++){
                unusedTreasures.add(usedTreasures.get(i));
            }
            
            shuffleTreasures();
            usedTreasures.clear();
        }
        
        t = unusedTreasures.get(0);
        usedTreasures.add(t);
        unusedTreasures.remove(t);
        
        return t;
            
    }

    
    public Monster nextMonster(){
        Monster m;
        
        if(unusedMonsters.isEmpty()){
            for (int i = 0; i < usedMonsters.size(); i++){
                unusedMonsters.add(usedMonsters.get(i));
            }
            shuffleMonsters();
            usedMonsters.clear();
        }
        
        m = unusedMonsters.get(0);
        usedMonsters.add(m);
        unusedMonsters.remove(m);
        
        return m;
    }

    
    public void GiveTreasureBack(Treasure t){
        
        usedTreasures.add(t);
    }
    
    public void giveMonsterBack(Monster m){
        usedMonsters.add(m);
    }
    
    public void initCards(){
        
        initTreasureCardDeck();
        initMonsterCardDeck();
        initCultistCardDeck();
        shuffleTreasures();
        shuffleMonsters();
        suffleCultist();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Oscar
 */
public class PruebaNapakalaki {
    
    static ArrayList<Monster> monstruos = new ArrayList();
    
    static ArrayList<Monster> vector = new ArrayList();
    
        public static  ArrayList<Monster> ConsultaCombate(ArrayList<Monster> monstruos){
        ArrayList<Monster> salida = new ArrayList();
        
        for (Monster m: monstruos){
            if(m.getCombatLevel() > 10)
                salida.add(m);
        }
        
        return salida;
    }
    
    public static ArrayList<Monster> PerdidadNivel(ArrayList<Monster> monstruos){
        ArrayList<Monster> salida = new ArrayList();
        
        for (Monster m: monstruos){
            if(m.getBc().getLevels() > 0)
                salida.add(m);
        }
        return salida;
    }
    
    
    public static ArrayList<Monster> Masde1nivel (){
        
        ArrayList<Monster> salida = new ArrayList();
        
        for(Monster m: monstruos){
            if(m.getPrize().getLevel()>1)
                salida.add(m);
        }
        
        return salida;
    }
    
    public static ArrayList<Monster> PierdeTesoroEspecífico(ArrayList<Monster> monstruos){
        
        ArrayList<Monster> salida = new ArrayList();
        
        for(Monster m: monstruos){
        //    if(!(m.getBc().getSpecificHiddenTreasures().isEmpty()) || !( m.getBc().getSpecificVisibleTreasures().isEmpty()));
                salida.add(m);
        }
        
        return salida;
    }
    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) {
        // TODO code application logic here
        
        //Creacion Byakhees de bonanza
        BadConsequence b1 = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        Prize p1 = new Prize(2,1);
        monstruos.add(new Monster("Byakhees de bonanza", 8, p1, b1));

        //Creacion Tenochtitlan
        BadConsequence b2 = new BadConsequence("Embobados con el lindo primigenio te descartas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        Prize p2 = new Prize(1,1);
        monstruos.add(new Monster("Tenochtitlan", 2, p2, b2));

        //Creacion El sopor de Dunwich
        BadConsequence b3 = new BadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0, new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList());
        Prize p3 = new Prize(1,1);
        monstruos.add(new Monster("El sopor de Dunwich", 2, p3, b3));

        //Creacion Demonios de Magaluf
        BadConsequence b4 = new BadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        Prize p4 = new Prize(4,1);
        monstruos.add(new Monster("Demonios de Magaluf", 2, p4, b4));

        //Creacion El gorrón en el umbral
        BadConsequence b5 = new BadConsequence("Pierdes todos tus tesoros visibles",0 , 5, 0);
        Prize p5 = new Prize(3,1);
        monstruos.add(new Monster("El gorrón en el umbral", 13, p5, b5));

        //Creacion H.P.Munchcraft
        BadConsequence b6 = new BadConsequence("Pierdes la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        Prize p6 = new Prize(2,1);
        monstruos.add(new Monster("H.P. Munchcraft", 6, p6, b6));

        //Creacion Necrófago
        BadConsequence b7 = new BadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        Prize p7 = new Prize(1,1);
        monstruos.add(new Monster("Necrófago", 13, p7, b7));

        //Creacion El rey de rosado
        BadConsequence b8 = new BadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        Prize p8 = new Prize(3,2);
        monstruos.add(new Monster("El rey de rosado", 11, p8, b8));

        //Creacion Flecher
        BadConsequence b9 = new BadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        Prize p9 = new Prize(1,1);
        monstruos.add(new Monster("Flecher", 2, p9, b9));
        
        //Creación Los hondos
        BadConsequence b10 = new BadConsequence("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto", true);
        Prize p10 = new Prize(2,1);
        monstruos.add(new Monster("Los hondos", 8, p10, b10));
        
        //Creación Semillas Cthulhu
        BadConsequence b11 = new BadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2,0,2);
        Prize p11 = new Prize(2,1);
        monstruos.add(new Monster("Semillas Cthulhu", 4, p11, b11));
        
        //Creación Dameargo
        BadConsequence b12 = new BadConsequence("Te intentas escaquear. Pierdes "
                + "una mano visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
        Prize p12 = new Prize(2,1);
        monstruos.add(new Monster("Dameargo", 1, p12, b12));
        
        //Creación Pollipólipo volante
        BadConsequence b13 = new BadConsequence("Da mucho asquito, pierdes 3 niveles", 3, 0, 0);
        Prize p13 = new Prize(2,1);
        monstruos.add(new Monster("Pollipólipo volante", 3, p13, b13));
        
        //Creación Yskhtihyssg-Goth
        BadConsequence b14 = new BadConsequence("No le hace gracia que pronuncien mal su nombre. Estas muerto", true);
        Prize p14 = new Prize(3,1);
        monstruos.add(new Monster("Yskhtihyssg-Goth",14, p14, b14));
        
        //Creación Familia Feliz
        BadConsequence b15 = new BadConsequence("La familia te atrapa. Est́as muerto.", true);
        Prize p15 = new Prize(3,1);
        monstruos.add(new Monster("Familia feliz", 1, p15, b15));
        
        //Creación Roboggoth
        BadConsequence b16 = new BadConsequence("La quinta directiva primaria te obliga a perder 2 niveles "
                + "y un tesoro 2 manos visible", 2, new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList());
        Prize p16 = new Prize(2,1);
        monstruos.add(new Monster("Roboggoth",8,p16,b16));
        
        //Creación El espía sordo
        BadConsequence b17 = new BadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0,
        new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        Prize p17 = new Prize(1,1);
        monstruos.add(new Monster("El espía sordo",5,p17,b17));
        
        //Creación Tongue
        BadConsequence b18 = new BadConsequence("Menudo susto te llevas. Pierdes"
                + " 2 niveles y 5 tesoros visibles.",2,5,0);
        Prize p18 = new Prize(2,1);
        monstruos.add(new Monster("Tongue",19,p18,b18));
        
        //Creación Bicéfalo
        BadConsequence b19 = new BadConsequence("Te faltan manos para tanta cabeza. "
                + "Pierdes 3 niveles y tus tesoros visibles de las manos.", 3, 
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND,TreasureKind.BOTHHANDS)), 
                new ArrayList());
        Prize p19 = new Prize(2,1);
        monstruos.add(new Monster("Bicéfalo",21, p19, b19));
        
        Prize premio = new Prize(3,7);
        BadConsequence bc = new BadConsequence("ayyy lmao", 5,5,5);
        Monster paco = new Monster("Paco", 8, premio, bc);
        
        String frase = paco.toString();
        System.out.print(frase);
        System.out.print("hola hola");
        
        vector = Masde1nivel(); // Para que funcione, hay que ponerle static a Masde1nivel(), creo que me faltaba;
        System.out.print("lalol");
        System.out.print(vector.toString());

        }
*/        
       
          
   
        
        
    }
   

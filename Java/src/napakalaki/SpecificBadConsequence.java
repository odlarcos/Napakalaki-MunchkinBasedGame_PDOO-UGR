/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class SpecificBadConsequence extends BadConsequence {
    
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    Player player;
    
    public SpecificBadConsequence(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden){
        super(text, levels);
        this.specificVisibleTreasures = tVisible;
        this.specificHiddenTreasures = tHidden;
    }
    
    @Override
    public boolean isEmpty(){
        return (specificVisibleTreasures.isEmpty() && specificHiddenTreasures.isEmpty());
    }
    
    @Override
    public void substractVisibleTreasure(Treasure t){
        specificVisibleTreasures.remove(t.getType());
    }
    
    @Override
    public void substractHiddenTreasure(Treasure t){
        specificHiddenTreasures.remove(t.getType());
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return specificVisibleTreasures;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
        return specificHiddenTreasures;
    }
    
    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        //ARRAYS auxiliares para nuevo BC
        ArrayList<TreasureKind> auxSVisibleT = new ArrayList();
        ArrayList<TreasureKind> auxSHiddenT = new ArrayList();
        
        //BC auxiliar
        BadConsequence aux;

        //Inicializamos array
        ArrayList<TreasureKind> copiaV = new ArrayList();
       
        //recorremos los tesoros del mal rollo y los almacenamos en la copia
        for(Treasure t: v) copiaV.add(t.getType());
        //igual que antes pero con hidden
        ArrayList<TreasureKind> copiaH = new ArrayList();
        for(Treasure t: h) copiaH.add(t.getType());
        
        //Si no estan vacios
        if( !(specificVisibleTreasures.isEmpty() || specificHiddenTreasures.isEmpty()) )
        {
                
            for(TreasureKind t: specificVisibleTreasures)
            {
                if(copiaV.contains(t)) 
                {
                    auxSVisibleT.add(t);
                    copiaV.remove(t);
                }
            }
            for(TreasureKind t: specificHiddenTreasures)
            {
                if(copiaH.contains(t))
                {
                    auxSHiddenT.add(t);
                    copiaH.remove(t);
                }
            }
            
        }
        //BC ajustado con los tesoros que tiene el jugador disponibles
        aux = new SpecificBadConsequence(this.text, this.levels, auxSVisibleT, auxSHiddenT);   
        
        return aux;
    }
}

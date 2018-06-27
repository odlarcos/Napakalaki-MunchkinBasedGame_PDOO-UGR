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
public class NumericBadConsequence extends BadConsequence{
    
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    
    public NumericBadConsequence(String text, int levels, int nVisible, int nHidden){
            super(text,levels);
            this.nVisibleTreasures=nVisible;
            this.nHiddenTreasures=nHidden;
    }
 
    public int getnVisibleTreasures(){
        return nVisibleTreasures;
    }

    public int getnHiddenTreasures(){
        return nHiddenTreasures;
    }

    @Override
    public boolean isEmpty(){    
        return (nVisibleTreasures==0 && nHiddenTreasures==0);      
    }

    @Override
    public void substractVisibleTreasure(Treasure t){
            if(nVisibleTreasures>0)
                nVisibleTreasures--;
    }

    @Override
    public void substractHiddenTreasure(Treasure t){
            if(nHiddenTreasures>0)
                nHiddenTreasures--;
    }

    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){   
        int visibles=0;
        int ocultos=0;

        //Si hay mas de 0 tesoros
            if(nVisibleTreasures > 0){ 
                //si tienes mas q el mal rollo se mantiene
                if(nVisibleTreasures > v.size()){
                    visibles = v.size();
                }
                //si no se cambia a los que tienes
                else{
                    visibles = nVisibleTreasures;
                }
            }
        //Si hay mas de 0 tesoros
            if(nHiddenTreasures > 0){
                //si tienes mas q el mal rollo se mantiene
                if(nHiddenTreasures > h.size()){
                    ocultos = h.size();
                }
                //si no se cambia a los que tienes
                else{
                    ocultos = nHiddenTreasures;
                }
            }

           NumericBadConsequence ResFinal = new NumericBadConsequence("", 0, visibles, ocultos);
           ResFinal.nHiddenTreasures=ocultos;
           ResFinal.nVisibleTreasures=visibles;
           return ResFinal;

        }

    /*public String toString(){
        return "\n Mal Rollo \n       Nombre Mal Rollo:" + this.text + "\n       Niveles:" + Integer.toString(this.levels) 
                    + "\n       Tesoros Visibles:" + Integer.toString(this.nVisibleTreasures) +
                    "\n       Tesoros No Visibles:" + Integer.toHexString(this.nHiddenTreasures);

        }
*/
}

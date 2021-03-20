/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

import java.util.ArrayList;

/**
 *
 * @author jocxa
 */
public class FuersaBruta {
    
    public  FuersaBruta(){}
   
   public void Algoritmo(ArrayList<ListaSimple> mazo, Nodo[] solucion, Nodo[] aleatorio) {
       int contadores[];
       contadores = new int[5];
       for(int i=0; i<5; i++){
		contadores[i]=0;
	}

       ListaSimple tmpa = mazo.get(0);
       ListaSimple tmpb = mazo.get(1);
       ListaSimple tmpc = mazo.get(2);
       ListaSimple tmpd = mazo.get(3);
       ListaSimple tmpe = mazo.get(4);
        
       while(contadores[0]<tmpa.largoLista()){
           Nodo tmpo = tmpa.BuscarPosicion(contadores[0]);
           aleatorio[0]=tmpo;
           contadores[0]+=1;
           
           while (contadores[1]<tmpb.largoLista()){
               tmpo = tmpa.BuscarPosicion(contadores[1]);
               aleatorio[1]=tmpo;
               contadores[1]+=1;
               
               while (contadores[2]<tmpc.largoLista()){
                   tmpo = tmpa.BuscarPosicion(contadores[2]);
                   aleatorio[2]=tmpo;
                   contadores[2]+=1;
                   
                   while (contadores[3]<tmpd.largoLista()){
                       tmpo = tmpa.BuscarPosicion(contadores[3]);
                       aleatorio[3]=tmpo;
                       contadores[3]+=1;
                       
                       while (contadores[4]<tmpe.largoLista()){
                           tmpo = tmpa.BuscarPosicion(contadores[4]);
                           aleatorio[4]=tmpo;
                           contadores[4]+=1;
                           if(comparacionDeArrays(solucion,aleatorio)){
                               return;
                           }
                       }
                       contadores[4]=0;
                   }
                   contadores[3]=0;
                }
               contadores[2]=0;
           }
           contadores[1]=0;
       }
       
//       donde sigue el codigo 
   }
   
   public boolean comparacionDeArrays(Nodo[] solucion, Nodo[] aleatorio){
       int contador=0;
       while(contador <5 ){
           if (!solucion[contador].nombre.equals(aleatorio[contador].nombre)){
               return false;
           } 
   
           contador+=1;
       }
       return true;
   }
}

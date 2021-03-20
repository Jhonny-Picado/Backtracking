/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Backtracking {

    //Constructor de la Clase
    public Backtracking(){
        
    }
    
    /*
        Metodo que asigna las restricciones que utilizara el backtraking        
        Recibe como parametros la cantidad de restricciones, el mazo del juego y el arreglo a rellenar de restricciones
    */
    public void AsignarRestricciones(int cantidad, ArrayList<ListaSimple> mazo, ArrayList<Nodo[]> restricciones){
        
        
        //Aca se termina hasta que se acaben la cantidad de parejas que debe hacer
        while(cantidad>0){
            
            int categoria = (int)(Math.random()*5); //Saca el random de la primera categoria
            int categoria2 = categoria;     
            
            //Este while es para la pareja de cartas sean de diferente categoria
            while(categoria2 == categoria ){    
                categoria2 =(int)(Math.random()*5);
            }
            //System.out.println(categoria+" y "+categoria2);
                       
            Nodo []cartas = new Nodo[2];    //Creo un vector de nodos
            
            ListaSimple tmp = mazo.get(categoria);  //Guardo en tmp la lista de la categoria que salio en el primer random

            cartas[0] = tmp.BuscarPosicion((int)(Math.random()*tmp.largoLista()));  //Busco por posicion en la lista, con un random de 0 al largo de la misma
            
            //Lo mismo que arriba pero con la segunda carta
            tmp = mazo.get(categoria2);
            cartas[1] = tmp.BuscarPosicion((int)(Math.random()*tmp.largoLista()));
            
            restricciones.add(cartas);  //Lo agrego al arreglo de restricciones
         
            cantidad--; //Condicion de parada
        }
        
//        System.out.println("--------------");
//        for(int i=0; i<restricciones.size(); i++){
//            System.out.println(restricciones.get(i)[0].nombre +"    "+ restricciones.get(i)[1].nombre );
//        }
    }    
}



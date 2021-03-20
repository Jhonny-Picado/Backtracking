/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

/**
 *
 * @author Usuario
 */
public class ListaSimple {
    
    
    //Atributos de la lista
    private Nodo primerNodo, ultimoNodo;
    private String categoria;
    
    
    //Constructor de la lista
    public ListaSimple(String nombre){
        this.categoria=nombre;
        this.primerNodo=null;
    }
    
    
    //Metodo que inserta un nuevo nodo al final de la lista
    public void insertar(Nodo nuevo){
        
        if(primerNodo==null){
            this.primerNodo=this.ultimoNodo=nuevo;
        }
        else{
            this.ultimoNodo.siguiente = nuevo;
            this.ultimoNodo= nuevo;
        }   
    }

    //Metodo que imprime la lista en consola
    public void imprimir(){
        
        Nodo tmp = this.primerNodo;
        
        System.out.println(this.categoria +" : ");
        System.out.println("-------------");
        while(tmp!=null){
            System.out.println(tmp.nombre);
            tmp= tmp.siguiente;
        }
    }
    
    public int largoLista(){
        int cant=0;
        Nodo tmp = this.primerNodo;

        while(tmp!=null){
            cant +=1;
            tmp= tmp.siguiente;
        }

        return cant;
    }
    
    //Metodo que busca por posicion en la lista
    public Nodo BuscarPosicion(int index){
        
        int i=0;
        Nodo tmp= this.primerNodo;
        while(tmp!=null){
            
            if(i==index) return tmp;
            
            tmp=tmp.siguiente;
            i++;
        }
        
        //Aca no llega nunca
        return null;
    }
    
}

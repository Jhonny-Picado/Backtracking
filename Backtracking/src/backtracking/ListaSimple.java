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
    
    private Nodo primerNodo, ultimoNodo;
    private String categoria;
    
    public ListaSimple(String nombre){
        this.categoria=nombre;
        this.primerNodo=null;
    }
    
    public void insertar(Nodo nuevo){
        
        if(primerNodo==null){
            this.primerNodo=this.ultimoNodo=nuevo;
        }
        else{
            this.ultimoNodo.siguiente = nuevo;
            this.ultimoNodo= nuevo;
        }   
    }    
}

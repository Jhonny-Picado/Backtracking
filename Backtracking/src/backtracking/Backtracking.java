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
public class Backtracking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        ListaSimple l = new ListaSimple("Sospechosos");
        CargarCartas(l);
        l.imprimir();
        
    }
    
    
    public static void CargarCartas(ListaSimple j){
        j.insertar(new Nodo("Mejor amigo"));
        j.insertar(new Nodo("Novio"));
        j.insertar(new Nodo("Vecino"));
        j.insertar(new Nodo("Mensajero"));
        j.insertar(new Nodo("Extraño"));
        j.insertar(new Nodo("Hermanastro"));
        j.insertar(new Nodo("Compañero de Trabajo"));
       
    }
    
}

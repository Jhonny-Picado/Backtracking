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
        
        l.insertar(new Nodo("Mejor amigo"));
        l.insertar(new Nodo("Novio"));
        l.insertar(new Nodo("Vecino"));
        l.insertar(new Nodo("Mensajero"));
        l.insertar(new Nodo("Extraño"));
        l.insertar(new Nodo("Hermanastro"));
        l.insertar(new Nodo("Compañero de Trabajo"));
        l.imprimir();
    }
    
}

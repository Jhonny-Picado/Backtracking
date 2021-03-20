/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

import java.util.ArrayList;


public class Pruebas {
    
    
    public static void main(String[] args) {
       
       ListaSimple Sospechoso = new ListaSimple("Sospechosos");
       ListaSimple Arma = new ListaSimple("Armas");
       ListaSimple Motivo = new ListaSimple("Motivos");
       ListaSimple Parte = new ListaSimple("Partes");
       ListaSimple Lugar = new ListaSimple("Lugares");
       
       CargarCartas(Sospechoso,Arma,Motivo,Parte,Lugar);
       
       ArrayList<ListaSimple> Mazo = new ArrayList<>();
       Mazo.add(Sospechoso);
       Mazo.add(Arma);
       Mazo.add(Motivo);
       Mazo.add(Parte);
       Mazo.add(Lugar);
       
       ArrayList<Nodo[]> restricciones = new ArrayList<>();
       
       
       
       Backtracking backtracking = new Backtracking();
       backtracking.AsignarRestricciones(5,Mazo,restricciones);

    }
    
    
    //Metodo que carga las cartas del juego
    public static void CargarCartas(ListaSimple a, ListaSimple b, ListaSimple c, ListaSimple d, ListaSimple e){
        
        a.insertar(new Nodo("Mejor amigo"));
        a.insertar(new Nodo("Novio"));
        a.insertar(new Nodo("Vecino"));
        a.insertar(new Nodo("Mensajero"));
        a.insertar(new Nodo("Extraño"));
        a.insertar(new Nodo("Hermanastro"));
        a.insertar(new Nodo("Compañero de Trabajo"));
        
        b.insertar(new Nodo("Pistola"));
        b.insertar(new Nodo("Cuchillo"));
        b.insertar(new Nodo("Machete"));
        b.insertar(new Nodo("Pala"));
        b.insertar(new Nodo("Bate"));
        b.insertar(new Nodo("Botella"));
        b.insertar(new Nodo("Tubo"));
        b.insertar(new Nodo("Cuerda"));
        
        
        c.insertar(new Nodo("Venganza"));
        c.insertar(new Nodo("Celos"));
        c.insertar(new Nodo("Dinero"));
        c.insertar(new Nodo("Accidente"));
        c.insertar(new Nodo("Drogas"));
        c.insertar(new Nodo("Robo"));
        
        d.insertar(new Nodo("Cabeza"));
        d.insertar(new Nodo("Pecho"));
        d.insertar(new Nodo("Abdomen"));
        d.insertar(new Nodo("Espalda"));
        d.insertar(new Nodo("Piernas"));
        d.insertar(new Nodo("Brazos"));
        
        e.insertar(new Nodo("Sala"));
        e.insertar(new Nodo("Comedor"));
        e.insertar(new Nodo("Baño"));
        e.insertar(new Nodo("Terraza"));
        e.insertar(new Nodo("Cuarto"));
        e.insertar(new Nodo("Garage"));
        e.insertar(new Nodo("Patio"));
        e.insertar(new Nodo("Balcón"));
        e.insertar(new Nodo("Cocina"));

    }
}

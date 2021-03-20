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
       backtracking.AsignarRestricciones(5, Mazo, restricciones);
       
       Nodo []solucion=Solucion(Mazo,restricciones);
       Nodo[] aleatorio;
       aleatorio = new Nodo[5];
       
       FuersaBruta prueba = new FuersaBruta();
       prueba.Algoritmo(Mazo, solucion, aleatorio);
       
       
       System.out.println("--------------------Solucion-------------------");
       for(int i=0;i<5;i++){
           System.out.println(solucion[i].nombre);
       }
       
       System.out.println("--------------------aleatorio-------------------");
       for(int i=0;i<5;i++){
           System.out.println(aleatorio[i].nombre);
       }
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
    
    
    public static Nodo[] Solucion(ArrayList<ListaSimple> mazo, ArrayList<Nodo[]> restricciones){
        
        Nodo [] solucion= new Nodo[5];
        
        while (true){
            for(int i=0; i<mazo.size(); i++){
                solucion[i]= mazo.get(i).BuscarPosicion((int)(Math.random()*mazo.get(i).largoLista()));
            }
            if(ValidaRestricciones(solucion,restricciones)){
                break;
            }
        }
        return solucion;
    }
    
    
    //Funcion que valida las restricciones para sacar la solucion sin estas
    public static boolean ValidaRestricciones(Nodo[] solucion, ArrayList<Nodo[]> restricciones){
        
        //Itera sobre las soluciones
        for(int i=0; i<5; i++){
            
            //Aca itera sobre las restricciones
            for(int j=0; j<restricciones.size(); j++){
                
                
                //Si la solucion en posicion 'i' es igual a alguna carta de las restricciones en la primera posicion
                if(solucion[i]==restricciones.get(j)[0]){
                    
                    //Si hay una carta coincidente, entonces revisa de nuevo por las soluciones
                    for(int k=0; k<5; k++){
                        
                        //Si alguna carta de la solucion es igual a la pareja la carta con solucion[i], retorna falso
                        if(solucion[k]==restricciones.get(j)[1]){
                            return false;
                        }
                    }
                } 
            }
        }
        
        //Aca llega cuando las restricciones fueron validadas
        return true;
    }
    
}

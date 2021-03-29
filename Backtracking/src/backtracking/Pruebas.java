/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

import java.util.ArrayList;

public class Pruebas {

    public ArrayList<Nodo[]> posiblesFuerzaBruta;
    
    public Pruebas(){   
    }
    
    public void IniciarAlgoritmos(int cantidadRestricciones) {
        
        this.posiblesFuerzaBruta = new ArrayList<>();
        
        //Se instancia las listas de cartas
        ListaSimple Sospechoso = new ListaSimple("Sospechosos");
        ListaSimple Arma = new ListaSimple("Armas");
        ListaSimple Motivo = new ListaSimple("Motivos");
        ListaSimple Parte = new ListaSimple("Partes");
        ListaSimple Lugar = new ListaSimple("Lugares");

        //Se cargan las cartas en las listas
        CargarCartas(Sospechoso, Arma, Motivo, Parte, Lugar);

        //Se crea un mazo, tipo arraylist de listas, para contener a todas las listas
        ArrayList<ListaSimple> Mazo = new ArrayList<>();
        Mazo.add(Sospechoso);
        Mazo.add(Arma);
        Mazo.add(Motivo);
        Mazo.add(Parte);
        Mazo.add(Lugar);

        //Se crea un arraylist de nodos para las restricciones
        ArrayList<Nodo[]> restricciones = new ArrayList<>();

        
        //Instacia un backtraking
        Backtracking backtracking = new Backtracking();
        backtracking.AsignarRestricciones(cantidadRestricciones, Mazo, restricciones);   //Manda a asignar las restricciones

        Nodo[] solucion = Solucion(Mazo, restricciones);        //Asigna la solucion del ejercicio
        Nodo[] aleatorio = new Nodo[5];

        FuersaBruta prueba = new FuersaBruta();      //Instancia la fuerza bruta
        prueba.Algoritmo(Mazo, solucion, aleatorio, this.posiblesFuerzaBruta); //Prueba el algoritmo de fuerza bruta
        
        System.out.println("--------------------Solucion-------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(solucion[i].nombre);
        }

        System.out.println("--------------------Solucion Encontrada Fuerza Bruta-------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(aleatorio[i].nombre);
        }
        
        ArrayList<String> incorrectasFuerza = new ArrayList<>();
                
        //Setea el mazo, para marcar las cartas que se marcaron como incorrectas, correctas
        for (int i = 0; i < 5; i++) {
            Nodo tmp = Mazo.get(i).primerNodo;

            while (tmp != null) {
                if (tmp.incorrecta) {
                    tmp.incorrecta = false;
                    incorrectasFuerza.add(tmp.nombre);
                }

                tmp = tmp.siguiente;
            }
        }

        //Instancia un nuevo nodo para la prueba del Backtracking
        Nodo[] posibleSolucion = new Nodo[5];
        System.out.println("--------------------Backtracking-------------------");

        backtracking.Algoritmo(Mazo, solucion, posibleSolucion, restricciones, 0);         //Prueba el algoritmo de Backtracking

        //Imprime resultados
        System.out.println("--------------------Solucion-------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(solucion[i].nombre);
        }

        System.out.println("--------------------Solucion Encontrada-------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(aleatorio[i].nombre);
        }

        System.out.println("Cantidad de veces que compara soluciones: " + backtracking.cantidad);
    }

    //Metodo que carga las cartas del juego
    public static void CargarCartas(ListaSimple a, ListaSimple b, ListaSimple c, ListaSimple d, ListaSimple e) {

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

    //Funcion que saca la solucion del problema
    public static Nodo[] Solucion(ArrayList<ListaSimple> mazo, ArrayList<Nodo[]> restricciones) {

        Nodo[] solucion = new Nodo[5];  //Instancia un nuevo arreglo de nodos

        while (true) {

            //itera sobre las 5 categorias de cartas
            for (int i = 0; i < mazo.size(); i++) {

                //Va capturando cada carta, usa el buscar posicion para cada lista de cartas
                solucion[i] = mazo.get(i).BuscarPosicion((int) (Math.random() * mazo.get(i).largoLista()));
            }

            /*System.out.println("--------------------TMP-------------------");
            for (int i = 0; i < 5; i++) {
                System.out.println(solucion[i].nombre);
            }*/
            //Valido las restricciones para que siempre llegue a la solucion
            if (ValidaRestricciones(solucion, restricciones)) {
                break;
            }
        }
        return solucion;    //Se retorna la solucion
    }

    
    //Funcion que valida las restricciones para sacar la solucion sin estas
    public static boolean ValidaRestricciones(Nodo[] solucion, ArrayList<Nodo[]> restricciones) {

        //Itera sobre las soluciones
        for (int i = 0; i < 5; i++) {

            //Aca itera sobre las restricciones
            for (int j = 0; j < restricciones.size(); j++) {

                //Si la solucion en posicion 'i' es igual a alguna carta de las restricciones en la primera posicion
                if (solucion[i] == restricciones.get(j)[0]) {

                    //Si hay una carta coincidente, entonces revisa de nuevo por las soluciones
                    for (int k = 0; k < 5; k++) {

                        //Si alguna carta de la solucion es igual a la pareja la carta con solucion[i], retorna falso
                        if (solucion[k] == restricciones.get(j)[1]) {
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

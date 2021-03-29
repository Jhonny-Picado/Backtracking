/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;

import java.util.ArrayList;

/**
 *
 * @author Jhonny Picado
 */
public class Backtracking {

    //Atributos de la clase
    private boolean flag;
    public int cantidad;

    //Constructor de la Clase
    public Backtracking() {
        this.flag = false;
        this.cantidad = 0;
    }

    /*
        Metodo que asigna las restricciones que utilizara el backtraking        
        Recibe como parametros la cantidad de restricciones, el mazo del juego y el arreglo a rellenar de restricciones
     */
    public void AsignarRestricciones(int cantidad, ArrayList<ListaSimple> mazo, ArrayList<Nodo[]> restricciones) {

        //Aca se termina hasta que se acaben la cantidad de parejas que debe hacer
        while (cantidad > 0) {

            int categoria = (int) (Math.random() * 5); //Saca el random de la primera categoria
            int categoria2 = categoria;

            //Este while es para la pareja de cartas sean de diferente categoria
            while (categoria2 == categoria) {
                categoria2 = (int) (Math.random() * 5);
            }
            //System.out.println(categoria+" y "+categoria2);

            Nodo[] cartas = new Nodo[2];    //Creo un vector de nodos

            ListaSimple tmp = mazo.get(categoria);  //Guardo en tmp la lista de la categoria que salio en el primer random

            cartas[0] = tmp.BuscarPosicion((int) (Math.random() * tmp.largoLista()));  //Busco por posicion en la lista, con un random de 0 al largo de la misma

            //Lo mismo que arriba pero con la segunda carta
            tmp = mazo.get(categoria2);
            cartas[1] = tmp.BuscarPosicion((int) (Math.random() * tmp.largoLista()));

            restricciones.add(cartas);  //Lo agrego al arreglo de restricciones

            cantidad--; //Condicion de parada
        }

        System.out.println("------Restricciones--------");
        for (int i = 0; i < restricciones.size(); i++) {
            System.out.println(restricciones.get(i)[0].nombre + "    " + restricciones.get(i)[1].nombre);
        }
    }

    
    /*  Algoritmo de backtracking que resuelve la combinacion de cartas,
    E: un arraylist de listas simple, la solucion al juego, un vector para almacenar la posible solucion
    y un entero que marca la categoria de la carta
    */
    public void Algoritmo(ArrayList<ListaSimple> mazo, Nodo[] solucion, Nodo[] posibleSolucion, ArrayList<Nodo[]> restricciones, int categoria
                                                                                                    ,ArrayList<Nodo[]> posiblesBackTracking) {

        //Valida si se paso de una hoja del arbol, para que retorne
        if (categoria > 4) {
            return;
        }

        Nodo tmp = mazo.get(categoria).primerNodo;      //Captura el primer nodo de cada lista segun la categoria

        //Se encicla mientras no se termine la lista en la cual esta iterando y que la bandera este apagada
        while (tmp != null && !this.flag ) {
            
            //En este bucle se salta las cartas incorrectas y valida las restricciones
            while (tmp != null && (tmp.incorrecta || validaRestricciones(restricciones, tmp, posibleSolucion, categoria))) {
                tmp = tmp.siguiente;
            }
            
            //Si se salto demasiadas cartas y llego a nulo, entonces que se salga del ciclo principal
            if (tmp == null) break;
            
            //Si la carta sigue siendo válida, entonces la coloco en la posible solucion del juego
            posibleSolucion[categoria] = tmp;

            //Valida si llega a una hoja del arbol (que el backtracking va formando) y si llego a la solucion tambien
            if (categoria == 4 && comparacionDeArrays(solucion, posibleSolucion, posiblesBackTracking)) {
                this.flag = true;       //Como encontro la solucion entonces activa la bandera 
                return;         //Detiene esta llamada recursiva
            } 
            
            //Si paso la carta paso las validaciones anteriores, quiere decir que faltan mas cartas para terminar la posible solucion,
            //entonces hace la llamada recursiva, sumándole uno a la categoria, para pasar a la siguiente
            Algoritmo(mazo, solucion, posibleSolucion, restricciones, categoria + 1, posiblesBackTracking);
            
            tmp = tmp.siguiente;
        }
    }

    //Función auxiliar que valida si la posible solucion es verdadera
    public boolean comparacionDeArrays(Nodo[] solucion, Nodo[] posibleSolucion, ArrayList<Nodo[]> posiblesBacktracking) {

        Nodo[] n = new Nodo[5];  
        
        System.arraycopy(posibleSolucion, 0, n, 0, 5);
        
        posiblesBacktracking.add(n);
        this.cantidad++;
        /*
        System.out.println("--------------------Posible solucion-------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(posibleSolucion[i].nombre);
        }*/
        
        int contador = 0;

        //Ciclo para recorrer los arreglos
        while (contador < 5) {
            if (solucion[contador] != posibleSolucion[contador]) {   //Compara los nodos, son punteros, tons no es necesario comparar strings (los nombres)
                MarcarIncorrecta(posibleSolucion, solucion);      //Si no es correcta, llama a funcion auxiliar donde marca una casilla incorrecta
                return false;
            }
            contador++;
        }
        return true;
    }

    //Funcion que marca una carta incorrecta
    public void MarcarIncorrecta(Nodo[] solucionIncorrecta, Nodo[] solucion) {

        int random = (int) (Math.random() * 5);     //Saca el random de la carta incorrecta

        while (solucionIncorrecta[random] == solucion[random]) {  //Valido que la carta seleccionada con el random, si sea incorrecta
            random = (int) (Math.random() * 5);
        }

        //System.out.println("Aca marco esta incorrecta: " + solucionIncorrecta[random].nombre);
        solucionIncorrecta[random].incorrecta = true;         //Aca marco la bandera incorrecta, como true
    }
    
    
    //Metodo que valida las restricciones del juego
    public boolean validaRestricciones(ArrayList<Nodo[]> restricciones, Nodo tmp, Nodo[] posibleSolucion, int categoria) {

        //Recorre las parejas de restricciones
        for (Nodo[] res : restricciones) {

            //Si la carta temporal, a evaluar esta en la posicion 0 de una pareja de restricciones
            if (tmp == res[0]) {
                return validaAuxiliar(tmp, res[1], posibleSolucion, categoria);
            }

            //Si la carta temporal, a evaluar esta en la posicion 0 de una pareja de restricciones
            if (tmp == res[1]) {
                return validaAuxiliar(tmp, res[0], posibleSolucion, categoria);
            }
        }
        return false;
    }

    
    //Funcio auxiliar que valida las cartas
    public boolean validaAuxiliar(Nodo tmp, Nodo pareja, Nodo[] posibleSolucion, int categoria) {

        //Si hay una carta coincidente, entonces revisa de nuevo por las posibles soluciones
        for (int i = 0; i < categoria; i++) {

            //Si alguna carta de la posible solucion es igual a la pareja la carta con solucion[i], retorna falso
            if (posibleSolucion[i] == pareja) {
                
                /*System.out.println(categoria);
                System.out.println("Valido la restriccion ->" + tmp.nombre + " , " + posibleSolucion[i].nombre);
                for (int j = 0; j < categoria; j++) {
                    System.out.print(posibleSolucion[j].nombre + " , ");
                }
                System.out.println();*/
                return true;
            }
        }
        return false;
    }

}

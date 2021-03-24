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
    
    //Constructor de la clase
    public  FuersaBruta(){}
   
    
    /*
    Algoritmo principal de fuerza bruta para la busqueda de combinaciones de cartas
    E: arreglo de listas simples, arreglo con la solucion, y arreglo temporal con la posible solucion
    */
    public void Algoritmo(ArrayList<ListaSimple> mazo, Nodo[] solucion, Nodo[] aleatorio) {

        int cantidad=0;     //Contador para ver la cantidad de veces que compara alguna posible solucion
        
        //Captura las listas del arreglo, para utilizar independientemente más adelante
        ListaSimple sospechosos = mazo.get(0);
        ListaSimple armas = mazo.get(1);
        ListaSimple motivos = mazo.get(2);
        ListaSimple partes = mazo.get(3);
        ListaSimple lugares = mazo.get(4);

        Nodo tmpSospechoso = sospechosos.primerNodo;    //Primera carta de sospechosos
        while (tmpSospechoso != null) {                 //Se repite el ciclo mientras este referenciando una carta

            //Aca se encicla para saltarse las cartas que esten incorrectas
            while (tmpSospechoso != null && tmpSospechoso.incorrecta) { 
                //System.out.println("Me brinco " +tmpSospechoso.nombre);
                tmpSospechoso = tmpSospechoso.siguiente;        //Se salta cada carta incorrecta
            }
            
            if(tmpSospechoso==null) break;  //Valida que después del ultimo salto el tmp no haya quedado nulo
            
            aleatorio[0] = tmpSospechoso;   
            tmpSospechoso = tmpSospechoso.siguiente;

            //De aca para abajo se sigue la misma teoria que con lo explicado en el primer while anterior
            Nodo tmpArma = armas.primerNodo;
            while (tmpArma!=null) {

                while (tmpArma!=null && tmpArma.incorrecta) {
                    //System.out.println("Me brinco arma: " +tmpArma.nombre);
                    tmpArma = tmpArma.siguiente;
                }
                if(tmpArma==null) break;
                aleatorio[1] = tmpArma;
                tmpArma = tmpArma.siguiente;

                //Cartas de Motivo
                Nodo tmpMotivo = motivos.primerNodo;
                while (tmpMotivo!=null) {
                    
                    while (tmpMotivo!=null && tmpMotivo.incorrecta) {
                        //System.out.println("Me brinco motivo: " +tmpMotivo.nombre);
                        tmpMotivo = tmpMotivo.siguiente;
                    }
                    if(tmpMotivo==null) break;
                    aleatorio[2] = tmpMotivo;
                    tmpMotivo = tmpMotivo.siguiente;
                    
                    //Cartas de Partes del cuerpo
                    Nodo tmpParte = partes.primerNodo;
                    while (tmpParte!=null) {
                        
                        while(tmpParte!= null && tmpParte.incorrecta){
                            //System.out.println("Me brinco parte: " +tmpParte.nombre);
                            tmpParte=tmpParte.siguiente;
                        }
                        if(tmpParte==null) break;
                        aleatorio[3] = tmpParte;
                        tmpParte = tmpParte.siguiente; 
                        
                        //Cartas del lugar donde sucedio el crimen
                        Nodo tmpLugar = lugares.primerNodo;
                        while (tmpLugar != null) {

                            while (tmpLugar!=null &&tmpLugar.incorrecta) {
                                //System.out.println("Me brinco lugar: " +tmpLugar.nombre);
                                tmpLugar = tmpLugar.siguiente;
                            }
                            if(tmpLugar==null) break;
                            aleatorio[4] = tmpLugar;
                            tmpLugar = tmpLugar.siguiente;
                            
                            cantidad++; //Sumo la cantidad de veces que va a compara respuestas
                            
                            //Llama a funcion auxiliar en donde revisa la posible solucion
                            if (comparacionDeArrays(solucion, aleatorio)) {
                                System.out.println("Cantidad de veces que compara soluciones: "+cantidad);
                                return;
                            }
                        }
                        tmpLugar = lugares.primerNodo;      //Se resetean los punteros temporales
                    }
                    tmpParte = partes.primerNodo;
                }
                tmpMotivo = motivos.primerNodo;
            }
            tmpArma= armas.primerNodo;
        }
    }
   
    
    //Función auxiliar que valida si la posible solucion es verdadera
    public boolean comparacionDeArrays(Nodo[] solucion, Nodo[] aleatorio) {
        
        
        System.out.println("--------------------Posible solucion-------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(aleatorio[i].nombre);
        }
        
        int contador = 0;
        
        //Ciclo para recorrer los arreglos
        while (contador < 5) {
            if (solucion[contador]!=aleatorio[contador]){   //Compara los nodos, son punteros, tons no es necesario comparar strings (los nombres)
                MarcarIncorrecta(aleatorio, solucion);      //Si no es correcta, llama a funcion auxiliar donde marca una casilla incorrecta
                return false;
            }
            contador++;
        }
        return true;
    }

    
    //Funcion que marca una carta incorrecta
    public void MarcarIncorrecta(Nodo[] solucionIncorrecta, Nodo[] solucion) {
        
        int random = (int)(Math.random()*5);     //Saca el random de la carta incorrecta

        while(solucionIncorrecta[random] == solucion[random]){  //Valido que la carta seleccionada con el random, si sea incorrecta
            random = (int)(Math.random()*5);
        }
        
        System.out.println("Aca marco esta incorrecta: "+ solucionIncorrecta[random].nombre);
        solucionIncorrecta[random].incorrecta=true;         //Aca marco la bandera incorrecta, como true
    }
    
}

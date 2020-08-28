/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {

    /*
    @param raiz declaracion del atributo tipo Nodo
    */
    
    private Nodo raiz;

    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    /*
    @param  islleno --> para conocer si la raiz esta nula(no tiene ningun dato)
    */
    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    /*
    @param adicionarNodo ---> empieza a recorrer el arbol desde la raiz para adicionar un dato ingresado
    si la raiz es igul a nula, entonces la raiz adiciona un nuevo nodo con el dato ingresado
    */
    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

            /*
            si la raiz no esta nula
            entonces --> SI el dato ingresado es menor al dato que esta en la ubicacion actual(raiz) se va por la izquierda
                        y SI la ubicacionIzquierda es igual a null, agrega el nuevo nodo(con el dato menor ingresado inicialmente)
            
            
           SINO
                adiciona el dato a la izquierda de la raiz
            */
            
        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                /*
                SINO SI --> si el dato es mayor al dato que ya esta en la ubicacion que esta apuntando
                
                
                */
                if (ubicacion.getDerecha() == null) {
                    /*
                    SI---> si el nodo de la derecha es nulo
                    
                    ENTONCES --> agrega un nuevo nodo con el dato ingresado
                    */
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                    /*
                    SINO --> en caso de que exista nodo verifica si es mayor y lo agrega a la derecha del nodo actual
                    */
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
    
    /*
    @param inOrden --> ejecuta inicialmente el metodo isLleno
    despues crea una instancia del ArrayList y lo llama 1
    Ejecuta el meotoo InOrden ingresando lo datos en la raiz
    @return --> por ultimo retorna el ArrayList
    */
     public ArrayList inOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }

    /*
     @param inOrden --> este metodo verifica por medio de un recorrido
     SI el recorrido del arbol es diferente de nulo(osea que tiene datos ingresados)
     
     Verifica los dato de la Izquierda de cada raiz hasta que ya no tenga mas nodos a la izquierda
     regresa a la raiz y agrega el dato en el ArrayList
     verifica el nodo de la derecha(en este caso el nodo se vuelve raiz) y vuelve a verificar el nodo izquierda
     @return --> en caso de no tener dato regresa a la raiz(el que era nodo de la derecha) y lo vuelve a ingresar en el ArrayList
     */
     private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(),l);
        }
    }
    
    /*
    @param llenarArbol --> compuesto por un array con datos separados por comas 
     y consulta el metodo adicionarNodo para agregar los datos al arrayList
    */
     public void llenarArbol(String datos) throws ArbolBinarioException
    {
        String[] arrayDatos= datos.split(",");
        for(String cadena: arrayDatos)
        {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }
        
    }

}

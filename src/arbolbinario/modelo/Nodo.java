/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;

/**
 *
 * @author carloaiza
 */
public class Nodo {
    
    /*
    @author Critian Castañeda Espitia
    @param dato  --> numero que se ingresara en el arbol
    @param izquierda --> para direccionar nodos de menor valor al actual
    @param derecha --> para direccionar nodos de mayor valor al actual
    
    */
    private int dato;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
    
    public boolean isHoja()
    {
        return izquierda== null && derecha == null;
    }
    
    
    public boolean isLleno()
    {
        return izquierda!=null && derecha!=null;
    }
    
    /*
    public void adicionarNodo(int dato) throws ArbolBinarioException
    {
        if(isLleno())
        {
            throw  new ArbolBinarioException("El nodo está lleno");
        }
        
        if(dato< this.dato)
        {
            if(izquierda==null)
            {
                izquierda=new Nodo(dato);
            }
            else
            {
                throw new ArbolBinarioException("La izquierda está llena");
            }
        }
        else if(dato > this.dato)
        {
            if(derecha ==null)
            {
                derecha=new Nodo(dato);
            }
            else
            {
                throw new ArbolBinarioException("La derecha está llena");
            }    
        }
        else
            throw new ArbolBinarioException("El dato ingresado ya existe");
    }
    */
}

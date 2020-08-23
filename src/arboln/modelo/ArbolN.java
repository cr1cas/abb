/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln.modelo;

import arbolbinario.modelo.excepciones.ArbolNException;

/**
 *
 * @author aula205c
 */
public class ArbolN {
    private NodoN raiz;

    public ArbolN() {
    }

    public NodoN getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoN raiz) {
        this.raiz = raiz;
    }
    
    /*
    metodo insertarHijo -->
    SI la raiz es igual a nula, se agrega un nuevo nodo que se convertiria en la raiz
    
    SINO
    SI--> si ya hay un dato ingresado y es igual al padre, agregan un nuevo hijo en el nodo
    
    SINO
    PARA -->
    */  
    public void insertarHijo(NodoN pivote, Visitante hijo, Visitante padre){
        if(raiz==null)
        {
            raiz= new NodoN(hijo);
        }    
        else
        {
            if(pivote.getDato().getIdentificacion().equals(padre.getIdentificacion()))
            {
                pivote.aumentarHijo(new NodoN(hijo));
            }
            else
            {
                for(NodoN hijoPivote: pivote.getHijos())
                {
                    if(hijoPivote.getDato().getIdentificacion().equals(padre.getIdentificacion()))
                    {
                        hijoPivote.aumentarHijo(new NodoN(hijo));
                        break;
                    }
                    else
                    {
                        insertarHijo(hijoPivote, hijo, padre);
                    }    
                }    
            }
        }    
    }
    /*
    recorrerArbolN --> si la raiz es diferente de null
    el atributo --listado-- va agregando de uno en uno los datos
    al final retorna el listado
    */
    public String recorrerArbolN() throws ArbolNException{
        if(raiz!=null)
        {
            String listado="";
            listado += recorrerArbolN(raiz, listado);
            return listado;
        }   
        throw new ArbolNException("El árbol está vacío");
    }
    
    /*
    recorrerArbolN -->
    el atributo --listado-- va agregando los dato y los recorre por medio del ciclo FOR
    al final retorna el listado de todo los datos
    */
    public String recorrerArbolN(NodoN pivote, String listado)
    {
        listado += "\n"+pivote.getDato();
        for(NodoN hijo: pivote.getHijos())
        {
            listado = recorrerArbolN(hijo, listado);
        }   
        return listado;
    }
    
}

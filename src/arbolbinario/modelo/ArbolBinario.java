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
    @author Critian Castañeda Espitia
    @param raiz declaracion del atributo tipo Nodo
    */
    
    private Nodo raiz;
    int cant;
    int altura;
    
    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    /*
    @author Critian Castañeda Espitia
    @param  islleno --> para conocer si la raiz esta nula(no tiene ningun dato)
    */
    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    /*
    @author Critian Castañeda Espitia
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
    
    
    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en preorden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
     */
    
    
    public ArrayList preOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            preOrden(raiz, l);
        }        
        return l;
    }
    
    /**
     * Método recursivo que recorre todo el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado Acumulador para registrar el dato del nodo visitado
     */
    private void preOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            listado.add(temp.getDato());
            preOrden(temp.getIzquierda(), listado);
            preOrden(temp.getDerecha(), listado);
        }
    }
    
    public ArrayList posOrden() throws ArbolBinarioException {
        ArrayList l=new ArrayList();
        posOrden(raiz,l);
        return l;
    }

    private void posOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            posOrden(reco.getIzquierda(),l);
            posOrden(reco.getDerecha(),l);
            //l.add(reco.getDato() + " ");
            l.add(reco.getDato());
        }
    }
    
    
    
    
    /*
    @author Critian Castañeda Espitia
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
     @author Critian Castañeda Espitia
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
    @author Critian Castañeda Espitia
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
     
     
     public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }
     
     private void impNiveles(Nodo reco, int nivel,ArrayList l) {
        if (reco != null) {
            impNiveles(reco.getIzquierda(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDerecha(), nivel + 1, l);
        }
    }

     
     //Nivel ordenado
     String[] niveles;
     
     public int alturaArbol() {
        altura = 0;
        alturaArbol(raiz, 0);
        return altura;
    }

    private void alturaArbol(Nodo pivote, int nivel) {
        if (pivote != null) {
            alturaArbol(pivote.getIzquierda(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            alturaArbol(pivote.getDerecha(), nivel + 1);
        }
    }

    public ArrayList imprimirNivel() {
        niveles = new String[alturaArbol() + 1]; /*En el método original se 
                                                 estaba llamando la variable altura,
                                                 pero se necesitaba llamar el metodo 
                                                para que hiciera el calculo correspondiente*/
        ArrayList l=new ArrayList();
        imprimirNivel(raiz, 0);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
        return l;
    }
      public void imprimirNivel(Nodo pivote, int nivel2) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1);
        }
    }
      
      
      
      //hojas
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }

    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    
    public int padre(int info) {
        if (info == 0 || this.raiz == null) {
            return 0;
        }
        Nodo x = padre(this.raiz, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private Nodo padre(Nodo x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzquierda()!= null && x.getIzquierda().getDato()==(info)) || (x.getDerecha()!= null && x.getDerecha().getDato()==(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzquierda(), info);
        if (y == null) {
            return (padre(x.getDerecha(), info));
        } else {
            return (y);
        }
    }
    
    
    //eliminar hojas - PODAR
     public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    //Balance
    int subizq = 0;
    int subder = 0;

    public String imprimirBalance() {
         subizq = 0;
         subder = 0;

        Balance(this.raiz, true, 0);
        //System.out.println("lado Izquierdo " + subizq + " Lado Derecho " + subder);
        if (subizq - subder == 0) {
            return ("El balance es: 0 ");
        } else if (subizq - subder == -1) {
            return("El balance es -1, derecha");
        } else if (subizq - subder == 1) {
            return("El balance 1, izquierda");

        } else {
            return("No es balanceado.."
                    + "porque es mas grande el lado "
                    + ((subizq > subder) ? "Izquierdo" : "Derecho"));
        }

    }

    public void Balance(Nodo reco, boolean lado, int i) {

        if (reco != null) {

            if (reco.getDerecha()== null && reco.getIzquierda()== null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }

            Balance(reco.getDerecha(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(reco.getIzquierda(), lado, i + 1);
        }

    }
    
    //borrar mayor
    public String borrarMayor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    //Borrar menor
    public String borrarMenor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda()!= null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    //Obtener el numero de ramas
    int numeroRamas = 0;
    public ArrayList<String>ObtenerRamamayor(){
    obtenernumeroRamas(this.raiz, 0);
    return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
    public void obtenernumeroRamas(Nodo pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzquierda(), contador);
            obtenernumeroRamas(pivote.getDerecha(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }

     public ArrayList<String> ObtenerRamamayor(Nodo pivote, int contador, String dato, ArrayList lista){
        if (pivote != null ) {
            dato+=pivote.getDato()+",";
            contador ++;
            lista=ObtenerRamamayor(pivote.getIzquierda(), contador, dato, lista);
            lista=ObtenerRamamayor(pivote.getDerecha(), contador, dato, lista);
            
            if (contador == this.numeroRamas) {
                lista.add(dato);
            }
        }
        return lista;
    }
    
    
}

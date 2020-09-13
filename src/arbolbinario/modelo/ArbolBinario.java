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
    
    /**
     * metodo posOrden de tipo ArrayList que lanza una excepcion
     * @return - retorna un arraylist
     * @throws ArbolBinarioException 
     */
    
    public ArrayList posOrden() throws ArbolBinarioException {
        ArrayList l=new ArrayList();
        posOrden(raiz,l);
        return l;
    }
    /**
     * @author Cristian Castañeda Espitia
     * metodo posOrden verifica primero la existencia de un nodo y recorre de izquierda a derecha hasta
     * indicar los datos
     * @param reco de tipo Nodo
     * @param l de tipo ArrayList
     */
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
     
     
     public int contarNodos()
     {
         return this.contarNodos(raiz);
     }
     
     public int contarNodos(Nodo reco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     /**
      * @author Cristian Castañeda Espitia
      * metodo impNiveles de tipo ArrayList que sirve para almacenar todos
      * los dato en un Array para al final retornarlos
      * @return - retorna un arrayList de los niveles de acuerdo al metodo impNiveles de tipo void
      */
     public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }
     /**
      * @author Cristian Castañeda Espitia
      * el metodo verifica la existencia de algun nodo y empieza a recorrer,
      * agrega el dato de la izquierda y continua con el de la derecha
      * @param reco  de tipo Nodo
      * @param nivel de tipo entero
      * @param l de tipo ArrayList
      */
     private void impNiveles(Nodo reco, int nivel,ArrayList l) {
        if (reco != null) {
            impNiveles(reco.getIzquierda(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDerecha(), nivel + 1, l);
        }
    }

     
     //Nivel ordenado
          
     /**
      * @author Cristian Castañeda Espitia
      * metodo para determinar la altura del arbol sirve para utilizar en otros metodos que necesitan la info
      * este metodo se ayuda de otro con el mismo nombre pero de tipo VOID
      * @return 
      */
     public int alturaArbol() {
        altura = 0;
        alturaArbol(raiz, 0);
        return altura;
    }

     /**
      * @author Cristian Castañeda Espitia
      * @param pivote --pivote de tipo Nodo compara si es diferente de Null para empezar a ejecutar el ciclo IF
      * @param nivel 
      */
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
        ArrayList l=new ArrayList();
        if(raiz != null){
        String[] niveles = new String[raiz.obtenerAlturaNodo() + 1]; 
        
        imprimirNivel(raiz, 0, niveles);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
        }
        return l;
    }
      public void imprimirNivel(Nodo pivote, int nivel2, String[] niveles) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1, niveles);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1, niveles);
        }
    }
      
      
      
      //hojas
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }
    /**
     * @author Cristian Ospina
     * @author Cristian Castañeda
     * 
     * @param r - tipo Nodo -  arroja los valores de las hojas del arbol pintado
     * @param l - los valores que reconoce como hojas los va almacenando en el array
     */
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
        //if (this.esHoja(x.getIzquierda())) {
        if (x.getIzquierda().isHoja()) {
            x.setIzquierda(null);
        }
        //if (this.esHoja(x.getDerecha())) {
        if (x.getDerecha().isHoja()) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    //Balance
    int subizq = 0;
    int subder = 0;

    /**
     * @author Cristian Ospina
     * @author Cristian Castañeda Espitia
     * imprimirBalance compara los nodos de la izquierda y derecha a partir de la raiz y verifica
     * cual lado esta desbalanceado
     * @return - indica cual es el balance despues de todas las comparaciones con el ciclo IF y muestra el resultdo en
     * pantalla
     */
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
    
     //buscar nodo
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));
    }
    
    private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzquierda(), x));
        } else if (compara < 0) {
            return (buscar(r.getDerecha(), x));
        } else {
            return (true);
        }
    }

    
    
    
    
}

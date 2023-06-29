package estructurasAuxiliaresP1;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * NOTA:
 * La estructura se asemeja mucho a una pila, lo que cambia es que 
 * el metodo getValues() devuelve un ArrayList pero 
 * este "desapilamiento" lo hace inverso (va agregando del fondo al exterior)
 */
public class MyList<T> implements Iterable<T>{
  private NodoDoble<T> first;
  private NodoDoble<T> last;
  private int size;

  public MyList(){
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  /**
   * Complejidad: O(1)
   * @return (boolean) 
   */
  public boolean isEmpty(){
    return this.size == 0;
  }

  /**
   * Complejidad: O(1)
   * 
   * Permite ver el valor del proximo elemento en deQueue
   * @return (T) valor del dequeue sin eliminarlo de la fila
   */
  public T viewLast(){
    if(this.isEmpty()){return null;}

    return this.last.getValue();
  }

  /**
   * Complejidad: O(1)
   * 
   * Agrega un nuevo elemento al final de la lista
   * @param nuevoElem (T) elemento a agregar
   */
  public void push(T nuevoElem){
    NodoDoble<T> aux = new NodoDoble<T>(nuevoElem);
    
    if(this.isEmpty()){
      this.first = aux;
      this.last = aux;
    }else{
      aux.setPrev(this.last);
      this.last.setNext(aux);
      this.last = aux;
    }
    size++;
  }

  /**
   * Complejidad: O(1)
   * 
   * Elimina y retorna el ultimo elemento de la lista
   * @return (T) ultimo elemento de la lista
   */
  public T pop(){
    if(this.isEmpty()){return null;}

    T aux = this.last.getValue();
    if(this.size == 1){
      this.first = null;
      this.last = null;
    }else{
      this.last = this.last.getPrev();
      this.last.setNext(null);
    }

    size--;

    return aux;
  }

  /**
   * Complejidad: O(1)
   */
  @Override
  public Iterator<T> iterator() {
    return new IteratorMyLista<>(this.first);
  }

  /**
   * Complejidad: O(n) donde n es la cantidad de elementos de la lista, esto es porque cada elemento 
   * de la lista es agregado al ArrayList que se va a devolver.
   * 
   * Permite obtener un arraylist de los elementos de la fila, 
   * donde el primer elemento es el primero en haber sido ingresado, y el ultimo es el ultimo ingresado.
   * 
   * @return ArrayList<T> values de la lista
   */
  public ArrayList<T> getValues(){
    ArrayList<T> dev = new ArrayList<>();
    Iterator<T> it = this.iterator();
    
    while(it.hasNext()){
      dev.add(it.next());
    }

    return dev;
  }

  /**
   * Complejidad: O(1)
   * 
   * @return (int) cantidad de elementos presentes en la lista
   */
  public int getSize(){
    return this.size;
  }

  /**
   * Complejidad: O(1)
   */
  public void clear(){
    this.first = null;
    this.last = null;
    size = 0;
  }
}

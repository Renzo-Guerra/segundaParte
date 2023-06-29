package estructurasAuxiliaresP1;

import java.util.ArrayList;
import java.util.Iterator;

public class MyQueue<T> implements Iterable<T>{
  private NodoDoble<T> first;
  private NodoDoble<T> last;
  private int size;

  /**
   * Complejidad: O(1)
   */
  public MyQueue(){
    this.clear();
  }

  /**
   * Complejidad: O(1)
   */
  public boolean isEmpty(){
    return (this.size == 0);
  }

  /**
   * Complejidad: O(1)
   * 
   * Agrega un nuevo elemento al final de la lista
   * @param nuevoElem (T) elemento a agregar
   */
  public void enQueue(T nuevoElem){
    NodoDoble<T> aux = new NodoDoble<T>(nuevoElem);
    
    if(this.isEmpty()){
      this.first = aux;
      this.last = aux;
    }else{
      aux.setNext(this.last);
      this.last.setPrev(aux);
      this.last = aux;
    }
    this.size++;
  }


  /**
   * Complejidad: O(1)
   * 
   * Elimina y retorna el primer elemento de la lista
   * @return (T) primer elemento de la lista
   */
  public T deQueue(){
    if(this.isEmpty()){return null;}

    T aux = this.first.getValue();
    if(this.size == 1){
      this.first = null;
      this.last = null;
    }else{
      this.first = this.first.getPrev();
      this.first.getNext().setPrev(null);
      this.first.setNext(null);
    }
    this.size--;

    return aux;
  }

  /**
   * Complejidad: O(1)
   */
  @Override
  public Iterator<T> iterator() {
    return new IteratorFila<>(this.first);
  }

  /**
   * Complejidad: O(n) donde n es la cantidad de elementos de la fila.
   * 
   * Permite obtener un arraylist de los elementos de la fila, 
   * donde el primer elemento es el primero en haber sido ingresado, y el ultimo es el ultimo ingresado.
   * A su vez, vacia la fila para no romper encapsulamiento.
   * @return ArrayList<T> values de la pila, y se vacia.
   */
  public ArrayList<T> getValues(){
    ArrayList<T> dev = new ArrayList<>();
    Iterator<T> it = this.iterator();

    while(it.hasNext()){
      dev.add(it.next());
    }

    this.clear();

    return dev;
  }
  /**
   * Complejidad: O(1)
   *
   * @return (int) cantidad de elementos en la fila 
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
    this.size = 0;
  }
}

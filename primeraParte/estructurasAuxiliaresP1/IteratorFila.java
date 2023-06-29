package estructurasAuxiliaresP1;

import java.util.Iterator;
/**
 * Se utiliza para recorrer filas, empieza por el ultimo elemento e interactua con el metodo getPrev de la clase NodoDoble
 */
public class IteratorFila<T> implements Iterator<T>{
  private NodoDoble<T> nodo;

  public IteratorFila(NodoDoble<T> first){
    this.nodo = first;
  }

  /**
   * Complejidad: O(1)
   */
  @Override
  public boolean hasNext() {
    return this.nodo != null;
  }

  /**
   * Complejidad: O(1)
   */
  @Override
  public T next() {
    T aux = this.nodo.getValue();
    this.nodo = this.nodo.getPrev();

    return aux;
  }
  
}

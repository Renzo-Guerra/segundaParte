package estructurasAuxiliaresP1;

import java.util.Iterator;

// Se utiliza para recorrer MyLista, empieza por el primer elemento e interactua con el metodo getNext de la clase NodoDoble

public class IteratorMyLista<T> implements Iterator<T> {
  private NodoDoble<T> nodo;

  public IteratorMyLista(NodoDoble<T> firstElem){
    this.nodo = firstElem;
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
    this.nodo = this.nodo.getNext();

    return aux;
  }
}

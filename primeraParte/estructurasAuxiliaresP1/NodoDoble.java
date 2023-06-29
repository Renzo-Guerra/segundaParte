package estructurasAuxiliaresP1;

public class NodoDoble<T> {
  private T value;
  private NodoDoble<T> next;
  private NodoDoble<T> prev;
  
  public NodoDoble(T value){
    this.value = value;
    this.next = null;
    this.prev = null;
  }

  /**
   * Complejidad: O(1)
   */
  public void setNext(NodoDoble<T> next) {
    this.next = next;
  }
  
  /**
   * Complejidad: O(1)
   */
  public void setPrev(NodoDoble<T> prev) {
    this.prev = prev;
  }

  /**
   * Complejidad: O(1)
   */
  public T getValue() {
    return value;
  }
  
  /**
   * Complejidad: O(1)
   */
  public NodoDoble<T> getNext() {
    return this.next;
  }

  /**
   * Complejidad: O(1)
   */
  public NodoDoble<T> getPrev() {
    return this.prev;
  }
}

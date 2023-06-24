package EstructurasAuxiliares.unionFind;

public class Nodo<T> {
  private T value;
  private Nodo<T> upper;
  
  public Nodo(){
    this(null);
  }

  public Nodo(T value){
    this.value = value;
    setUpper(null);
  }

  public void setUpper(Nodo<T> upper) {this.upper = upper;}
  public Nodo<T> getUpper() {return this.upper;}
  public T getValue() {return value;}
  
}

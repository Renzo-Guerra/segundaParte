package EstructurasAuxiliares;

public class Arista<T> {
  private T vertice1;
  private T vertice2;
  private T valor;

  public Arista(T vertice1, T vertice2, T valor){
    this.vertice1 = vertice1;
    this.vertice2 = vertice2;
    this.valor = valor;
  }

  public T getVertice1() {return vertice1;}
  public T getVertice2() {return vertice2;}
  public T getValor() {return valor;}
}

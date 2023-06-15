package EstructurasAuxiliares;

public class Arista {
  private Integer vertice1;
  private Integer vertice2;
  private Integer valor;

  public Arista(Integer vertice1, Integer vertice2, Integer valor){
    this.vertice1 = vertice1;
    this.vertice2 = vertice2;
    this.valor = valor;
  }

  public Integer getVertice1() {return vertice1;}
  public Integer getVertice2() {return vertice2;}
  public Integer getValor() {return valor;}

  @Override
  public String toString() {
    return this.getVertice1() + ";" + this.getVertice2() + ";" + this.getValor();
  }
}

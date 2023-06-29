package grafos.ComparatorsArco;

import java.util.function.Predicate;

import grafos.Arco;

public class PredArcoDest implements Predicate<Arco>{
  private Integer verticeDestino;

  public PredArcoDest(Integer verticeDestino){
    this.verticeDestino =verticeDestino;  
  }

  @Override
  public boolean test(Arco t) {
    return (t.getVerticeDestino() == verticeDestino);
  }
  
}

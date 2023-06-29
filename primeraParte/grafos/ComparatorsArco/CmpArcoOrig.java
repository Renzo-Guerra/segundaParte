package grafos.ComparatorsArco;

import java.util.Comparator;

import grafos.Arco;

public class CmpArcoOrig implements Comparator<Arco>{

  @Override
  public int compare(Arco o1, Arco o2) {
    return o1.getVerticeOrigen() - o2.getVerticeOrigen();
  }
  
}

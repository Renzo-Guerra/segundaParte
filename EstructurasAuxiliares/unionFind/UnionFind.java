package EstructurasAuxiliares.unionFind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import EstructurasAuxiliares.Arista;

public class UnionFind {
  private HashMap<Integer, Nodo<Integer>> valores;
  private ArrayList<Arista> caminosViables;
  private Integer longitudTotal;

  public UnionFind(Collection<Integer> elementos){
    valores = new HashMap<>(elementos.size());
    caminosViables = new ArrayList<>();
    this.longitudTotal = 0;
    cargarConjuntos(elementos);
  }

  // Por cada elemento crea un nodo el cual lo contiene.   
  private void cargarConjuntos(Collection<Integer> elementos){
    Iterator<Integer> it = elementos.iterator();
    Integer aux;
    while(it.hasNext()){
      aux = it.next();
      valores.put(aux, new Nodo<>(aux));
    }
  }

  /**
   * Dado un valor Integer, devuelve el valor del ancestro mas elevado.
   * @param value (Integer) del elemento a buscar 
   * @return (Integer) Valor del ancestro mas elevado (sería como el id del conjunto)
   */
  public Nodo<Integer> getJoinId(Integer value){
    Nodo<Integer> currentNodo = this.valores.get(value);
    // Quiere decir que ese elemento nunca existió en la estructura UnionFind.
    if(currentNodo == null){return null;}
    
    return getUpperChain(currentNodo);
  }


  //Dado un nodo, devuelve el nodo "raiz", en caso de que el nodo actual sea la raiz, se devuelve a si mismo. 
  private Nodo<Integer> getUpperChain(Nodo<Integer> currentNodo){
    if(currentNodo.getUpper() == null){
      return currentNodo;
    }else{
      // Se llama recursivamente hasta encontrar al nodo "raiz"
      Nodo<Integer> aux = getUpperChain(currentNodo.getUpper());
      if(aux != null){
        return aux;
      }
    }

    return null;
  }

  public boolean union(Arista arista){
    Nodo<Integer> aux1 = getJoinId(arista.getVertice1());
    Nodo<Integer> aux2 = getJoinId(arista.getVertice2());
    // System.out.println("Arista a ingresar: " + arista); // Permite controlar todos las aristas llamadas (no necesariamente se agregaron a caminosViables)
    if(aux1.equals(aux2)){return false;}
    Nodo<Integer> padreDeAmbos = new Nodo<>();
    aux1.setUpper(padreDeAmbos);
    aux2.setUpper(padreDeAmbos);
    this.longitudTotal += arista.getValor();
    this.caminosViables.add(arista);

    return true;
  }

  public boolean unionComplete(){
    // Se itera por cada nodo "base" (el que contiene un valor)
    Set<Integer> keys = this.valores.keySet();
    if(keys.size() <= 1){return true;}

    Iterator<Integer> it = keys.iterator();
    // Se saca el value de la primera key, en caso de que todos las keys
    // apunten a un nodo el cual tengan todos en comun el mismo nodo, 
    // da a entender que TODOS LOS NODOS ASIGNADOS A CADA KEY pertenecen 
    // al mismo conjunto. 
    Nodo<Integer> aux = getJoinId(it.next());

    while(it.hasNext()){
      if(aux != getJoinId(it.next()))
        return false;
    }

    // En caso de que el while terminara, quiere decir que
    // toodos Tienen el mismo "nodo raiz"
    return true;
  }

  /**
   * Devuelve una copia de los caminos que se deberán construir
   */
  public ArrayList<Arista> getCaminosViables(){
    return new ArrayList<>(this.caminosViables);
  }

  // Solo devuelve la longitud del camino en caso de 
  // que se haya encontrado una solucion
  public Integer getLongitudTotal() {
    return (unionComplete())? this.longitudTotal : null;
  }
}

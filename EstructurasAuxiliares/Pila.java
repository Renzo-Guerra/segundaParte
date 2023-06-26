package EstructurasAuxiliares;

import java.util.ArrayList;
import java.util.Iterator;

public class Pila {
  private ArrayList<Arista> elementos;
  private Integer sumatoria;

  public Pila(ArrayList<Arista> aristas){
    this.elementos = new ArrayList<>(aristas);
    this.sumatoria = 0;
    calcularSumatoria();
  }

  /**
   * Solo se usa cuando al crear la pila, se ingresa una coleccion
   */
  private void calcularSumatoria(){
    for(Arista arista:this.elementos)
      this.sumatoria += arista.getValor(); 
  }

  public Pila(){
    this.elementos = new ArrayList<>();
    this.sumatoria = 0;
  }

  public void push(Arista arista){
    if(arista != null){
      this.elementos.add(arista);
      this.sumatoria += arista.getValor();
    }
  }

  public void pushAll(ArrayList<Arista> aristas){
    for(Arista a: aristas){
      push(a);
    }
  }

  public Arista pop(){
    if(this.elementos.isEmpty()){return null;}
    
    Arista aux = this.elementos.remove((this.elementos.size() - 1));
    this.sumatoria -= aux.getValor();
    
    return aux;
  }

  public Integer getSumatoria(){
    return this.sumatoria;
  }

  public boolean isEmpty() {
    return (this.elementos.size() == 0);
  }

  public String toString() {
    return this.elementos.toString();
  }
  
  public ArrayList<Arista> getElementos(){
    return new ArrayList<>(this.elementos);
  }
  
  public int size() {
    return this.elementos.size();
  }

  public Iterator<Arista> iterator() {
    return this.elementos.iterator();
  }

  public void clear() {
    this.elementos.clear();
    this.sumatoria = 0;
  }

}

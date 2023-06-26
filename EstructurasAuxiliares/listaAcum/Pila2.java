package EstructurasAuxiliares.listaAcum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import EstructurasAuxiliares.Arista;

// Se le implemento la interfaz Collection para que le podamos 
// Pasar una coleccion por el constructor.
public class Pila2 implements Collection<Arista>{
  private ArrayList<Arista> elementos;
  private Integer sumatoria;

  public Pila2(ArrayList<Arista> aristas){
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

  public Pila2(){
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

  @Override
  public boolean isEmpty() {
    return (this.elementos.size() == 0);
  }

  @Override
  public String toString() {
    return this.elementos.toString();
  }
  
  public ArrayList<Arista> getElementos(){
    return new ArrayList<>(this.elementos);
  }
  
  @Override
  public int size() {
    return this.elementos.size();
  }

  @Override
  public Iterator<Arista> iterator() {
    return this.elementos.iterator();
  }

  @Override
  public void clear() {
    this.elementos.clear();
    this.sumatoria = 0;
  }

  @Override
  public boolean contains(Object o) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'contains'");
  }

  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
  }

  @Override
  public <T> T[] toArray(T[] a) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
  }

  @Override
  public boolean add(Arista e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'add'");
  }

  @Override
  public boolean remove(Object o) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
  }

  @Override
  public boolean addAll(Collection<? extends Arista> c) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addAll'");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
  }

}

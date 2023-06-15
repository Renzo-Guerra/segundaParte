package EstructurasAuxiliares;

import java.util.ArrayList;
import java.util.List;

public class ServicioMergeSort {
  private List<Arista> elementos;
  private int size; 
  private List<Arista> helper;

  public ServicioMergeSort(List<Arista> lista){
    this.elementos = lista;
  }

  public void sort(){
    this.size = this.elementos.size();
    this.helper = new ArrayList<>(this.size);
    mergeSort(0, (this.size-1));
  }

  private void mergeSort(int menor, int mayor){
    if(menor < mayor){
      int mitad = ((menor + mayor) / 2);
      
      mergeSort(menor, mitad);
      mergeSort((mitad + 1), mayor);

      merge(menor, mitad, mayor);
    }
  } 

  private void merge(int menor, int mitad, int mayor){
    // Se copian ambas partes al array del helper
    for(int i=menor ; i<=mayor ; i++){
      helper.add(i, this.elementos.get(i));
    }

    int i = menor;
    int j = mitad + 1;
    int k = menor;

    // copiar de manera ordenada al array original los valores de la
    // mitad izquierda o de la derecha

    while (i <= mitad && j <= mayor) {
      if (this.helper.get(i).getValor() <= this.helper.get(j).getValor()) {
        this.elementos.remove(k);
        this.elementos.add(k, this.helper.get(i));
        i++;
      } else {
        this.elementos.remove(k);
        this.elementos.add(k, this.helper.get(j));
        j++;
      }
      k++;
    }

    // si quedaron elementos copiarlos al array original
    while (i <= mitad) {
      this.elementos.remove(k);
      this.elementos.add(k, this.helper.get(i));
      k++;
      i++;
    }
    
    while (j <= mayor) {
      this.elementos.remove(k);
      this.elementos.add(k, this.helper.get(j));
      k++;
      j++;
    }

  }
}

package grafos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * CORRECCIONES HECHAS:
 * 	- Se cambió completamente la estructura "core" de la estructura de datos a utilizar. 
 *    Anteriormente se utilizó un HashMap<Integer, HashSet<Arco<T>>>, pero ahora se utilizará 
 * 		un HashMap<Integer, HashMap<Integer, Arco<T>>>. Si bien es cierto que por cada arco 
 *    se tiene que guardar tanto el verticeOrige, el verticeDestino, y el arco (el cual ya tiene estos 
 *    datos, generando repeticion de data), optamos por comprometer memoria por sobre la performance en BIG O().
 *  
 *  - Se intentó implementar clases para el arco que implementen la interfaz "Comparator<Arco>" 
 *    cosa de que teniendo un conjunto de Arcos, dado un vertice destino, pueda juzgar cuales arcos 
 *    cumplen a esta condicion. SIN EMBARGO, una vez realizadas estas clases, caimos en cuenta de que
 *    el metodo removeAll() que HashSet proporciona acepta un "Predicate", el cual luego de indagar 
 * 		descubrimos que es parecido al Comparator, solo que este devuelve verdadero o falso, a diferencia 
 *    del Comparator el cual tambien se puede utilizar comparar cual de entre 2 objetos es mayor, menor o igual a otro    
 *    (dada una/s condiciones).
 * 		
 * 	-	Por ultimo, vimos que temina siendo mas eficiente realizarlo de la forma en la que entregamos el tp (ya que 
 * 		al intentar realizar un "removeAll(Predicado p)" internamente itera por cada objeto del HashSet para eliminar 
 * 		los que no cumplan la condicion, que sería un O(n) para una tarea que se llamaría en reiteradas ocaciones),
 *    si bien el metodo "obtenerArcos()" que implementamos tiene un costo computacional elevado, es un precio a pagar 
 *    por el bajo costo computacional obtenido en el resto de los metodos.
 */

public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap<Integer, HashMap<Integer, Arco<T>>> vertices;
	private Integer cantArcos;
	
	// Complejidad: O(1)
	public GrafoDirigido(){ 
    this.vertices = new HashMap<>();
		this.cantArcos = 0;
	}

	// Complejidad: O(1)
	@Override
	public void agregarVertice(int verticeId) {
		// En caso de que la key ya exista, reemplaza el value viejo por el nuevo (Instancia un HashMap...)
    this.vertices.put(verticeId, new HashMap<>());
	}

	// Complejidad: O(n-1) 	Aquí hubo un cambio 
	// Siendo n la cantidad de vertices cuando se invocó el metodo, pero 
	// dado que se elimina vertice pasado por parametro, y luego los arcos con 
	// destino a este, la cantidad de vertices es n menos el vertice eliminado.
	@Override
	public void borrarVertice(int verticeId) {
		int cantEliminados;
		// En caso que se logre eliminar el vertice dado, se eliminan por consecuencia los arcos que tenian por origen a "verticeId".
		if(contieneVertice(verticeId)){
			cantEliminados = this.vertices.get(verticeId).size();
			this.cantArcos -= cantEliminados;
			this.vertices.remove(verticeId);
			borrarArcosApuntandoA(verticeId);	// Se eliminan los arcos con destination al vertice eliminado.
		}
	}
  
	// Complejidad O(n)		Aquí hubo un cambio 
	// Siendo n la cantidad de vertices actuales, devuelve una lista con los Arcos eliminados 
	private void borrarArcosApuntandoA(Integer verticeId){
		Iterator<Integer> itOrigenes = this.vertices.keySet().iterator();
		// Se itera por cada vertice, eliminando las keys internas (destination) las cuales hacen referencia  
		// al arco asociado a esa doble combinacion de keys (la primer key es el vertice Origen (el cual es el 
		// que se está iterando), y la segunda es el "verticeId" que se está tratando de eliminar los arcos que 
		// lo tienen a este como destino).
		// En caso de que el remove devuelva un arco (en vez de un null, debido a que al intentar poner el valor 
		// "verticeId" como segunda key y NO exista esa segunda key, devolvera null), se descuenta en 1 la cantidad
		// de arcos.
		while(itOrigenes.hasNext()){
			// No se invocó al metodo "borrarArco(v1, v2) devido a que este NO devuelve el arco o null yua que es un procedimiento"
			if(this.vertices.get(itOrigenes.next()).remove(verticeId) != null) 
				cantArcos--;
		}
	}

	// Complejidad: O(1)
  private boolean existenLosVertices(int verticeId1, int verticeId2){
    return (contieneVertice(verticeId1) && contieneVertice(verticeId2));
  }

	// Complejidad: O(1)
  @Override
  public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
    // Se verifica que existan ambos vertices
		if(existenLosVertices(verticeId1, verticeId2)){
      // Se crea un nuevo arco y se lo agrega, de manera que el vertice1 puede ir al vertice2. 
      // vertice1 --> vertice2
			// Solo se aumenta la cantidad de arcos si NO existia previamente un arco desde V1 a V2
			if(!existeArco(verticeId1, verticeId2))
				cantArcos++;
      this.vertices.get(verticeId1).put(verticeId2, new Arco<T>(verticeId1, verticeId2, etiqueta));
		}
	}


	 // Complejidad: O(1)  Aquí hubo un cambio
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
    if(existenLosVertices(verticeId1, verticeId2)){				// Internamente "existenLosVertices" utiliza 2 veces O(1)
			cantArcos--;
			this.vertices.get(verticeId1).remove(verticeId2); 	// Tanto el get como el remove son O(1).
		}
	}
	
	// Complejidad: O(1)
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	 // Complejidad: O(1) Aquí hubo un cambio
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(existenLosVertices(verticeId1, verticeId2))
			return (this.obtenerArco(verticeId1, verticeId2) != null);
		
		return false;
	}


	// Complejidad: O(1) Aca hubo cambios 
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(existenLosVertices(verticeId1, verticeId2))
			return this.vertices.get(verticeId1).get(verticeId2);

		return null;
	}

	// Complejidad: O(1)
	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	// Complejidad: O(1) Aca hubo cambios
	@Override
	public int cantidadArcos() {
		return this.cantArcos;
	}

	//Complejidad: O(1) Aca hubo cambios
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	// Complejidad: O(1) Aca hubo cambios 
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(contieneVertice(verticeId))
			return this.vertices.get(verticeId).keySet().iterator();

		return null;
	}

	/**
	 * Complejidad: O(m * sumatoria(n)) donde m es la cantidad de keys del HashMap y n 
	 * es la cantidad de elementos dentro de cada HashMap.
	 * 
	 * Actualizado, la complejidad sigue siendo de N*N, ya que al no haber almacenado 
	 * los arcos como tal en un HashSet<Arco<T>> por ejemplo, no podemos simplemente 
	 * llamar a esa estructura e invocar .iterator(), pero esto es asi debido a que 
	 * en el caso de hacerlo así, los demas metodos contarían con un costo computacional 
	 * mas alto del que actualmente tienen, siendo todos de O(1), salvo por este último y por 
	 * borrarVertice(), el cual es O(n).
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Set<Integer> keys = this.vertices.keySet();
		Set<Arco<T>> arcosDevolver = new HashSet<>();
		
		// Complejidad O(sumatoria(n)) donde n es la cantidad de elementos de cada HashSet dentro del HashMap 
		for(Integer keyActual : keys){
			// Complejidad O(n) donde n es la cantidad de elementos en el HashSet que seleccionamos, por el addAll(), 
			// ya que este itera sobre la collection HashSetInterna (El value de la key "keyActual" es la que referencia al HashMap interno)).
			arcosDevolver.addAll(this.vertices.get(keyActual).values());		
		}
			
		return arcosDevolver.iterator();
	}

	// Complejidad: O(1) Aquí hubo cambios
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(contieneVertice(verticeId))
			return this.vertices.get(verticeId).values().iterator();

		return null;
	}

}

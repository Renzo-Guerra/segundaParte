package servicios;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import estructurasAuxiliaresP1.MyQueue;
import grafos.Grafo;

public class ServicioDFS {
	private HashSet<Integer> visited;						// Vertices ya visitados.
	private MyQueue<Integer> visitedOrder;			// Se guardan en el orden en el que fueron visitados.
	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visited = new HashSet<>();
		this.visitedOrder = new MyQueue<>();
	}
	

	public List<Integer> dfsForest() {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		ArrayList<Integer> dev;
		int verticeActual;
		
		while(it.hasNext()){
			verticeActual = it.next();

			if(!visited.contains(verticeActual))		
				this.dfsForestAux(verticeActual);			
		}
		this.visited.clear();						// Se eliminan por si en un futuro se vuelve a invocar al metodo
		
		dev = this.visitedOrder.getValues();	
		
		return dev;
	}

	private void dfsForestAux(int verticeActual){
		Iterator<Integer> itAdy = this.grafo.obtenerAdyacentes(verticeActual);
		int adyacenteActual;
		
		this.visited.add(verticeActual);
		this.visitedOrder.enQueue(verticeActual);
		
		// Ahora se debe comprobar si lo que devuelve el metodo "obtenerAdyacentes()" del grafo no devuelve un null.
		if(itAdy != null){		
			while(itAdy.hasNext()){
				adyacenteActual = itAdy.next();

				if(!visited.contains(adyacenteActual))
					this.dfsForestAux(adyacenteActual);
			}
		}
	}

}
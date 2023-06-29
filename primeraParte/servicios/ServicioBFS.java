package servicios;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import estructurasAuxiliaresP1.MyQueue;
import grafos.Grafo;

public class ServicioBFS {
	private MyQueue<Integer> terminados;		// Se agregan aqui los que ya se agregaron sus adyacentes a la fila del metodo auxiliar
	private Grafo<?> grafo;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.terminados = new MyQueue<>();
	}
	
	public List<Integer> bfsForest() {
		HashSet<Integer> visitados = new HashSet<>();		// Se agregan aqui los que de alguna forma obtuvimos su valor
		ArrayList<Integer> dev;
		Iterator<Integer> it = this.grafo.obtenerVertices();
		int verticeActual;
		
		while(it.hasNext()){
			verticeActual = it.next();
			if(!visitados.contains(verticeActual)){
				this.bfsForestAux(verticeActual, visitados);
			}
		}

		dev = terminados.getValues();			// El metodo automaticamente hace un clear()
		return dev;
	}
	
	private void bfsForestAux(int verticeActual, HashSet<Integer> visitados){
		MyQueue<Integer> fila = new MyQueue<>();		// Fila auxiliar donde almacenaran los numeros hasta que esté vacia
		Iterator<Integer> itAdyacentes;
		int elementoActual;
		int adyacenteActual;

		fila.enQueue(verticeActual);
		visitados.add(verticeActual);

		while(!fila.isEmpty()){
			elementoActual = fila.deQueue();
			
			itAdyacentes = this.grafo.obtenerAdyacentes(elementoActual);
			
			// Ahora se debe comprobar si lo que devuelve el metodo "obtenerAdyacentes()" del grafo no devuelve un null.
			if(itAdyacentes != null){	
				while(itAdyacentes.hasNext()){
					adyacenteActual = itAdyacentes.next();
					if(!visitados.contains(adyacenteActual)){
						visitados.add(adyacenteActual);
						fila.enQueue(adyacenteActual);
					}	
				}
			}

			this.terminados.enQueue(elementoActual);
		}
	}
}
package servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import estructurasAuxiliaresP1.MyList;
import estructurasAuxiliaresP1.MyQueue;
import grafos.Arco;
import grafos.Grafo;

public class ServicioCaminos {
	private MyQueue<ArrayList<Integer>> caminosValidos;		// Lista de caminos validos, la cual cada elemento es un camino valido.
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		caminosValidos = new MyQueue<>();
	}

	/**
	 * Evalua y devuelve todos los posibles caminos que se pueden tomar desde origen 
	 * a destino sin superar un limite establecido.
	 * 	
	 * @return
	 */
	public List<List<Integer>> caminos() {
		// Si el grafo no contiene el vertice destino o el final, es imposible
		if(caminoImposible()){return new ArrayList<>();}

		// Se almacenan los arcos del camino actual, para que no se vuelva a pasar por alli en este trayecto en especifico
		HashSet<Arco<?>> visitados = new HashSet<>();					

		// Se almacenan los vertices de todos los posibles caminos que se van formando
		MyList<Integer> posibleCamino = new MyList<>();				

		this.caminosAux(this.origen, posibleCamino, visitados);

		List<List<Integer>> caminosDev = new ArrayList<>(this.caminosValidos.getValues());		// La Queue automaticamente se vacía
		
		return caminosDev;
	}

	private void caminosAux(int verticeActual, MyList<Integer> posibleCamino, HashSet<Arco<?>> visitados){
		// Obtenemos todas las posibles siguientes vertices.
		Iterator<Integer> itAdyacentes = this.grafo.obtenerAdyacentes(verticeActual);	
		Arco<?> ultimoTrecho;
		int verticeAdyacente;

		posibleCamino.push(verticeActual);

		// Se verifica si es un camino valido
		if(posibleCamino.viewLast() == destino){
			// En caso de que si, se lo agrega a la lista de caminosValidos
			this.caminosValidos.enQueue(new ArrayList<>(posibleCamino.getValues()));
		}

		// Ahora se debe comprobar si lo que devuelve el metodo "obtenerAdyacentes()" del grafo no devuelve un null.
		if(itAdyacentes != null){
			while((itAdyacentes.hasNext()) && (!this.sePuedePodar(posibleCamino))){
				verticeAdyacente = itAdyacentes.next();
				ultimoTrecho = this.grafo.obtenerArco(verticeActual, verticeAdyacente);
				
				if(!visitados.contains(ultimoTrecho)){		
					visitados.add(ultimoTrecho);						
					this.caminosAux(verticeAdyacente, posibleCamino, visitados);
					visitados.remove(ultimoTrecho);					
				}
			}
		}

		posibleCamino.pop();
	}

	
	private boolean sePuedePodar(MyList<Integer> posibleCamino){
		return ((posibleCamino.getSize()-1) >= this.lim);
	}
	
	
	private boolean caminoImposible(){
		return ((!this.grafo.contieneVertice(origen)) || (!this.grafo.contieneVertice(destino)) || (this.lim < 0));
	}
}
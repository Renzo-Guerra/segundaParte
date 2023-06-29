import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import EstructurasAuxiliares.Arista;
import EstructurasAuxiliares.Pila;
import EstructurasAuxiliares.ServicioMergeSort;
import EstructurasAuxiliares.unionFind.UnionFind;

public class CSVReader {
	private String path;
	private ArrayList<Arista> caminos; 
	private HashSet<Integer> estaciones; 
	private int contadorParaGreedy;	
	private int contadorParaBackTracking;

	public CSVReader(String path) {
		this.path = path;
	}
	
	public void read() {
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent();
		/* Cada vez que se llama a la funcion read, se borran y 
		vuelven a cargar los mismos/nuevos caminos */
		this.caminos = new ArrayList<>(lines.size());
		this.estaciones = new HashSet<>();

		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			Integer origen = Integer.parseInt(line[0].trim().substring(1));
			Integer destino = Integer.parseInt(line[1].trim().substring(1));
			Integer etiqueta = Integer.parseInt(line[2].trim());
      
			// Se crean y agregan los caminos al arraylist de caminos, para luego ser ordenados ascendentemente
			this.caminos.add(new Arista(origen, destino, etiqueta));
			// Da igual si estaciones ya tiene uno de los elementos, en caso de ya tenerlo, no hace nada.
			this.estaciones.add(origen);
			this.estaciones.add(destino);
		}
	}

	private ArrayList<String[]> readContent() {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(this.path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}

	// El peor caso sería que cicle a traves de cada camino del txt, seria O(n) siendo n la cantidad de caminos dados.
	public void encontrarSolucionPorGreedy(){
		// Se crea una copia de los caminos, para que ESTOS sean los ordenados ascendentemente
		// Sino al hacer el back, tendria que crear otra instancia del CSVReader...
		ArrayList<Arista> caminosAux = new ArrayList<>(this.caminos);
		ServicioMergeSort servicio = new ServicioMergeSort(caminosAux);
		this.contadorParaGreedy = 1;
		// Se ordenan ascendentemente los caminos (teniendo en cuenta la distancia entre las estaciones)
		servicio.sort();
		// System.out.println(this.caminosAux); // Se muestan los caminos ordenados de ascendentemente (por longitud de caminos)
		UnionFind servicioUnion = new UnionFind(this.estaciones);
		int indiceAct = 0;

		// UnionFind.unionComplete tiene una complejidad O(n) siendo n la cantidad de "estaciones"
		while((servicioUnion.getCantCaminosViables() < (this.estaciones.size() - 1)) && (indiceAct < caminosAux.size())){
			this.contadorParaGreedy++; // Se aumenta en 1 el contador de cantidad de veces que se ejecuto el metodo greedy.
			servicioUnion.union(caminosAux.get(indiceAct++));
		}

		System.out.println("El greedy se ejecutó " + contadorParaGreedy + " veces.");
		// Complejidad de unionComplete es de O(n) siendo n la cantidad de estaciones.
		if(servicioUnion.unionComplete()){
			System.out.println("Caminos a construir: " + servicioUnion.getCaminosViables());
			System.out.println("La suma de los caminos anteriores da un total de: " + servicioUnion.getLongitudTotal() + " km.");
		}else{
			System.out.println("No se logró encontrar una solucion...");
		}
	}

	
	public void encontrarSolucionPorBackTracking(){
		Pila mejorSolucion = new Pila();
		Pila solucionActual = new Pila();
		this.contadorParaBackTracking = 1;
		auxBackMejorSolucionValida(mejorSolucion, solucionActual);
		System.out.println("El backtracking se ejecutó " + contadorParaBackTracking + " veces.");
		if(!mejorSolucion.isEmpty()){
			System.out.println("Caminos a construir: " + mejorSolucion);
			System.out.println("La suma de los caminos anteriores da un total de: " + mejorSolucion.getSumatoria() + " km.");			
		}else{
			System.out.println("No existe una solucion...");
		}
	}

	public void auxBackMejorSolucionValida(Pila mejorSolucion, Pila solucionActual){
		this.contadorParaBackTracking++;
		System.out.println("Iteracion numero: " + this.contadorParaBackTracking);
		// Se aumenta en 1 el contador de cantidad de veces que se ejecuto el metodo backtracking.
		// Quiere decir que ya encontró a la minima cantidad de caminos necesarios 
		// para unir las n estaciones con n-1 caminos
		if(backtrackingSolucionValida(mejorSolucion, solucionActual)){
			// Dentro del if tambien se contempla la longitud de los caminos.
			mejorSolucion.clear();
			mejorSolucion.pushAll(solucionActual.getElementos());
		}else{
			/**
			 * Podas:
			 * 	- Si la solucion actual ya tiene n-1 caminos, quiere decir que ya se controló que esa solucion no es valida, 
			 *    dando a entender que no es necesario que siga quitando elementos de "caminos" y volverse a llamar con el mismo 
			 *    conjunto de n-1 caminos. 
			 *  - El !this.caminos.isEmpty() es para que no se intente remover un indice de algo que no tiene elementos.
			 *  - La ultima validacion descarta varias soluciones las cuales como maximo llegaran a conformar 
			 *    conjuntos de (estaciones-2) caminos.
			 */
			if((solucionActual.size() < (this.estaciones.size() - 1)) && (!this.caminos.isEmpty()) && ((solucionActual.size() + this.caminos.size()) >= (this.estaciones.size() - 1))){
				Arista aux = this.caminos.remove(0);
				// Aca no es necesaria la poda que se utiliza abajo, ya que lo unico que cambia es que eliminamos un valor en caminos,
				// Lo cual no afecta la sumatoria de la solucionActual
				auxBackMejorSolucionValida(mejorSolucion, solucionActual);

				solucionActual.push(aux);
				if((mejorSolucion.getSumatoria() == 0) || (solucionActual.getSumatoria() < mejorSolucion.getSumatoria())){
					auxBackMejorSolucionValida(mejorSolucion, solucionActual);
				}
				aux = solucionActual.pop();
				this.caminos.add(0, aux);
			}
		}
	}

	private boolean backtrackingSolucionValida(Pila mejorSolucion, Pila solucionActual){
		// Primero se evalua siquiera si la cantidad de elementos de la posible solucion es 
		// de tamaño n-1 (siendo n la cantidad de estaciones).
		if(solucionActual.size() != (this.estaciones.size() - 1)){return false;}
		// Antes de evaluar la posibilidad del unionFind, se evalua si la longitud total de los nuevos caminos es menor 
		// que la longitud total de los caminos de la mejor solucion (hasta el momento)
		
		// En caso de que ya haya una posible solucion a devolver en "mejorSolucion", se evalua si la sumatoria de los caminos 
		// a construir de la nueva posible solucion es menor que la actual "mejorSolucion", asi no se hace el unionFind de gusto
		if((!mejorSolucion.isEmpty()) && (solucionActual.getSumatoria() >= mejorSolucion.getSumatoria())){return false;}

		// En caso de que se hayan la cantidad n-1 caminos en la posible solucion, 
		// tocaría evaluar si esos n-1 caminos conforman una solucion valida 
		// (se unen todas las estaciones a traves de estos caminos). 
		UnionFind unionFind = new UnionFind(this.estaciones);
		
		Iterator<Arista> it = solucionActual.iterator();
		while(it.hasNext())
			if(!unionFind.union(it.next())){return false;}

		// En caso de haber superado el bucle, quiere decir que al realizar todas las uniones de conjuntos, 
		// no hubo ni 1 caso donde 2 estaciones pertenecieran al mismo conjunto.
		return true;
	}

	public ArrayList<Arista> getPosiblesCaminos(){
		return new ArrayList<>(this.caminos);
	}

}

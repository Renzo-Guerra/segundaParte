import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import EstructurasAuxiliares.Arista;
import EstructurasAuxiliares.ServicioMergeSort;
import EstructurasAuxiliares.unionFind.UnionFind;

public class CSVReader {
	private String path;
	private ArrayList<Arista> caminos; 
	private HashSet<Integer> estaciones; 
	
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

	public void encontrarSolucionPorGreedy(){
		ServicioMergeSort servicio = new ServicioMergeSort(this.caminos);
		// Se ordenan ascendentemente los caminos (teniendo en cuenta la distancia entre las estaciones)
		servicio.sort();
		// System.out.println(this.caminos); // Se muestan los caminos ordenados de ascendentemente (por longitud de caminos)
		UnionFind servicioUnion = new UnionFind(this.estaciones);
		int indiceAct = 0;
		while((!servicioUnion.unionComplete()) && (indiceAct < this.caminos.size())){
			servicioUnion.union(this.caminos.get(indiceAct++));
		}

		if(servicioUnion.unionComplete()){
			System.out.println("Caminos a construir: ");
			System.out.println(servicioUnion.getCaminosViables());
			System.out.println("La suma de los caminos anteriores da un total de: " + servicioUnion.getLongitudTotal() + " km.");
		}else{
			System.out.println("No se logró encontrar una solucion...");
		}
	}

}

package grafos;

/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {
	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}

	/**
	 * Complejidad: O(1)
	 * 
	 * @return (int) value del verticeOrigen
	 */
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	/**
	 * Complejidad: O(1)
	 * 
	 * @return (int) value del verticeDestino
	 */
	public int getVerticeDestino() {
		return verticeDestino;
	}

	/**
	 * Complejidad: O(1)
	 * 
	 * @return (T) devuelve la etiqueta
	 */
	public T getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Complejidad: O(1)
	 */
	@Override
	public String toString() {
		return "{v_origen: " + getVerticeOrigen() + 
						", v_destino: " + getVerticeDestino() + 
						", etiqueta: " + getEtiqueta() + "}";
	}
}
public class Main {
	public static void main(String[] args) {
    /**
     * Dada una ruta a un archivo el cual contiene registros, los cuales especifican:
     *  - Estacion origen
     *  - Estacion destino
     *  - Distancia entre estas
     */ 
    String path = "./Tuneles.txt";
    // Se crea un objeto CSVReader
		CSVReader reader = new CSVReader(path);
    // El objeto lee el archivo que le pasamos y desmenuza cada registro.
		reader.read();
    
	}

}
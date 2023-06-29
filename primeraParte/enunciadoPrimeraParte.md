# Primera parte

El objetivo de esta primera parte del trabajo práctico especial es implementar la clase **Grafo**, siguiendo
la interfaz propuesta, para luego resolver un par de algoritmos simples sobre el mencionado contenedor.
Implementación

A partir de la definición de una interfaz para la clase **Grafo** (Grafo.java), la declaración de la clase
**GrafoDirigido** y la implementación de la clase **Arco**.

Será necesario:

- Elegir una estructura de implementación capaz de almacenar el conjunto de vértices (valores  enteros) y el conjunto de arcos (tipo de datos parametrizado T)
- Implementar cada uno de los métodos públicos propuestos por la interfaz.
- Detallar la complejidad de cada uno de los métodos implementados de acuerdo a la estructura
elegida.

Se propone el siguiente formato para explicitar la complejidad de cada método:

```java
    /**
    * Complejidad: O(X) donde X es ... debido a que debe
    * "realizar lo siguiente" para verificar si existe un arco.
    */
    @Override
    public void algo(){
      // instrucciones
    }
```

Una vez implementado el contenedor **Grafo** se deberán resolver las tres funciones siguientes:

También respetando la interfaz propuesta en las clases **ServicioBFS**, **ServicioDFS** y **ServicioCaminos**.

Descripción de los servicios:

- BFS Forest: dado un grafo, realiza un recorrido en anchura y retorna un orden posible de descubrimiento para los vértices durante ese recorrido.
- DFS Forest: dado un grafo, realiza un recorrido en profundidad y retorna un orden posible de descubrimiento para los vértices durante ese recorrido.
- Caminos: dado un origen, un destino y un límite “lim” retorna todos los caminos que, partiendo del vértice origen, llega al vértice de destino sin pasar por más de “lim” arcos. 

**Aclaración importante**: en un camino no se puede pasar 2 veces por el mismo arco.




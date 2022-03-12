public class Prim {
	
	// ---------------------------------- MAIN -------------------------------------------
	
	public static void main(String[] args)
	{
		long timeS, timeE, timeR;
		int grafo[][] = new int[][] { { 0, 2, 0, 6, 0 },
									{ 2, 0, 3, 8, 5 },
									{ 0, 3, 0, 0, 7 },
									{ 6, 8, 0, 0, 9 },
									{ 0, 5, 7, 9, 0 } };

		timeS = System.nanoTime();
		prim(grafo);
	    timeE = System.nanoTime();
	    timeR = timeE - timeS;
	    
		System.out.println("Tiempo para Prim: " + timeR);
	}
	
	// ---------------------------------- PRIM -------------------------------------------
	
	// Grafo de 5 vertices
	private static final int V = 5;

	static int minKey(int key[], Boolean conjuntoMST[])
	{
		// Inicia en el valor minimo
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (conjuntoMST[v] == false && key[v] < min) {
				min = key[v];
				min_index = v;
			}

		return min_index;
	}

	static void print(int padre[], int grafo[][])
	{
		System.out.println("Arista \tPeso");
		for (int i = 1; i < V; i++)
			System.out.println(padre[i] + " - " + i + "\t" + grafo[i][padre[i]]);
	}

	// Contruir y mostrar el grafo usando matriz de adyacencia
	static void prim(int grafo[][])
	{
		int padre[] = new int[V];

		// Almacenara los pesos minimos
		int key[] = new int[V];
		Boolean conjuntoMST[] = new Boolean[V];

		// Infinitos por defecto
		for (int i = 0; i < V; i++) {
			key[i] = Integer.MAX_VALUE;
			conjuntoMST[i] = false;
		}

		// primer vertice
		key[0] = 0; 
		padre[0] = -1; // raiz

		for (int count = 0; count < V - 1; count++) {
			// Minima llave del conjunto de vertices
			int u = minKey(key, conjuntoMST);
			conjuntoMST[u] = true;

			for(int v = 0; v < V; v++)
				// Actualizará la clave solo si el gráfico[u][v] es más pequeño que la clave[v]
				if (grafo[u][v] != 0 && conjuntoMST[v] == false && grafo[u][v] < key[v]) {
					padre[v] = u;
					key[v] = grafo[u][v];
				}
		}

		print(padre, grafo);
	}
}

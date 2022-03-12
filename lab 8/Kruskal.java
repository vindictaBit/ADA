public class Kruskal {
	
	// ---------------------------------- MAIN -------------------------------------------
	
	public static void main(String[] args)
	{
		long timeS, timeE, timeR;
		int grafo[][] = {
			{ INF, 2, INF, 6, INF },
			{ 2, INF, 3, 8, 5 },
			{ INF, 3, INF, INF, 7 },
			{ 6, 8, INF, INF, 9 },
			{ INF, 5, 7, 9, INF },
		};
	
		timeS = System.nanoTime();
		kruskal(grafo);
	    timeE = System.nanoTime();
	    timeR = timeE - timeS;
	    
		System.out.println("Tiempo para Kruskal: " + timeR);
	}
	
	// ---------------------------------- KRUSKAL -------------------------------------------
	
	// Grafo de 5 vertices
	static int V = 5;
	static int[] padre = new int[V];
	static int INF = Integer.MAX_VALUE;
	
	// Encontrar vertice i
	static int find(int i)
	{
		while (padre[i] != i)
			i = padre[i];
		return i;
	}
	
	// Hace unión de i y j. Falso si i y j ya están en el mismo conjunto
	static void union1(int i, int j)
	{
		int a = find(i);
		int b = find(j);
		padre[a] = b;
	}
	
	static void kruskal(int costo[][])
	{
		int minCosto = 0; // costoe mínimo del conjunto
		
		for (int i = 0; i < V; i++)
			padre[i] = i;
	
		// uniendo los mínimos 
		int contArista = 0;
		while (contArista < V - 1)
		{
			int min = INF, a = -1, b = -1;
			for (int i = 0; i < V; i++)
			{
				for (int j = 0; j < V; j++)
				{
					if (find(i) != find(j) && costo[i][j] < min)
					{
						min = costo[i][j];
						a = i;
						b = j;
					}
				}
			}
	
			union1(a, b);
			System.out.printf("Arista %d:(%d, %d) costo:%d \n",
				contArista++, a, b, min);
			minCosto += min;
		}
		System.out.printf("\nCosto mínimo= %d \n", minCosto);
	}
}

import java.util.Scanner;
public class AlgoritmoFW {
	
	//---------------------------------------- FLOYD-WARSHALL -------------------------------------------

	// Algoritmo Floyd Warshall O(n^3)
	public static int[][] masCorto(int[][] adj, int[][] camino){
		int n = adj.length;
		// Recorre las filas
		for(int k = 0; k < n; k++) { 
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					// Si la comparación resulta menor, intercambia valores
					if(adj[i][k] + adj[k][j] < adj[i][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
						camino[i][j] = camino[k][j];
					}
				}
			}
		}
		return adj;
	}
	
	//---------------------------------------- MAIN -------------------------------------------------
	
	public static void main(String[] args) {
		long timeS, timeE, timeR;
		// 4 vértices, dimensión de la matriz (tam)
		int tam = 5;
		Scanner in = new Scanner(System.in);
		
		// Llenando la matriz
		int[][] matriz = new int[tam][tam];
		matriz[0][0] = 0;
		matriz[0][1] = 10;
		matriz[0][2] = 2;
		matriz[0][3] = 99;
		matriz[0][4] = 99;
		matriz[1][0] = 99;
		matriz[1][1] = 0;
		matriz[1][2] = 99;
		matriz[1][3] = 99;
		matriz[1][4] = 1;
		matriz[2][0] = 99;
		matriz[2][1] = 1;
		matriz[2][2] = 0;
		matriz[2][3] = 99;
		matriz[2][4] = 7;
		matriz[3][0] = 99;
		matriz[3][1] = 99;
		matriz[3][2] = 99;
		matriz[3][3] = 0;
		matriz[3][4] = 99;
		matriz[4][0] = 99;
		matriz[4][1] = 99;
		matriz[4][2] = 99;
		matriz[4][3] = 3;
		matriz[4][4] = 0;
		
		int[][] caminoCorto;
		int[][] camino = new int[tam][tam];
		
		// matriz de adyacencia
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				if(matriz[i][j] == 99) camino[i][j] = -1;
				else camino[i][j] = i;
			}
		}
		for(int i = 0; i < tam; i++) {
			camino[i][i] = i;
		}
		
		timeS = System.nanoTime();
		caminoCorto = masCorto(matriz, camino);
        timeE = System.nanoTime();
        timeR = timeE - timeS;
		
		// RESULTADO
		System.out.println("   0 1 2 3 4");
		System.out.println("   _________ ");
		for(int i = 0; i < tam; i++) {
			System.out.print(i + " |");
			for(int j = 0; j < tam; j++)
				System.out.print(caminoCorto[i][j] + " ");
			System.out.println();
		}
		
		System.out.println("\nTiempo: " + timeR);
	}

}

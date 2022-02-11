import java.util.Arrays;
import java.util.Stack;

class BiGraf {
    public boolean Comprobar(int[][] grafo) {
        int n = grafo.length;
        int[] color = new int[n];
        Arrays.fill(color,-1);				// Podría ser 0 == azul / 1 == rojo / -1 == sin color

        for(int inicio = 0; inicio < n; inicio++){
            Stack<Integer> pila = new Stack<>();  // Usaremos la pila para el recorrido del grafo
            pila.push(inicio);
            color[inicio] = 0; 					
            
            while (!pila.isEmpty()){
                Integer vertice = pila.pop();
                if (color[vertice] == -1){
                	// Si el color del nodo actual es el mismo que el del nodo adyacente a él, 
                	// significa que no es posible colorear y el grafo de entrada no es un grafo bipartito.
                	// Para comprobar lo anterior, recorremos el grafo
                    for (int adj : grafo[vertice]){ 
                        if (color[adj] == -1){
                            pila.push(adj);
                            color[adj] = color[vertice] ^ 1; 
                        }else if (color[adj] == color[vertice]){ 
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

public class Bipartito {
	public static void main(String[] args) {
		BiGraf grafo = new BiGraf();
		int[][] graf = {{1,3}, {0,2}, {1,3}, {0,2}};
		System.out.println("¿El grafo es bipartito? " + grafo.Comprobar(graf));
	}
}

import java.util.Scanner;
 
public class BellmanFord {
    public long [] resultado; // Almacenará las distancias más cortas
    class arista {
    	public int a; // Inicio
    	public int b; // Final
    	public int value; // Peso de la arista
        
        arista(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }
    }
    
    public boolean caminosCortos(int n, arista[] grafo) {
        resultado = new long[n];
        for(int i = 1; i < n; i++) {
        	// Distancia entre el vértice 0 y otros vértices hasta el infinito (valor máximo del tipo Integer)
            resultado[i] = Integer.MAX_VALUE; 
        }
        
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < grafo.length; j++) {
            	if (resultado[grafo[j].b] > resultado[grafo[j].a] + grafo[j].value)
            		resultado[grafo[j].b] = resultado[grafo[j].a] + grafo[j].value;
            }
        }
        
        // Se determina si hay algún ciclo negativo
        boolean det = true;
        for (int i = 1; i < n; i ++) {
        	if(resultado[grafo[i].b] > resultado[grafo[i].a] + grafo[i].value) {
                det = false;
                break;
            }
        }
        
        return det;
    }
    
    public static void main(String[] args) {
        BellmanFord BF = new BellmanFord();
        Scanner in = new Scanner(System.in);
        System.out.println ("Ingrese el número total de vértices n y el número total de aristas p de un gráfico:");
        int n = in.nextInt();
        int p = in.nextInt();
        
        arista[] grafo = new arista[p];
        System.out.println ("Ingrese los datos del lado específico:");
        for(int i = 0; i < p; i++) {
        	int a = in.nextInt();
            int b = in.nextInt();
            int value = in.nextInt();
            grafo[i] = BF.new arista(a, b, value);
        }
        in.close();
        
        if(BF.caminosCortos(n, grafo))	{
        	for(int i = 0; i < BF.resultado.length; i++) System.out.print(BF.resultado[i]+" ");
        }
        else 
        	System.out.println("Hay un ciclo negativo en el gráfico dado, no es posible la distancia más corta");
    }
 
}
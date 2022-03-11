import java.util.Scanner;
 
public class BellmanFord {
	
	//---------------------------------------- BELLMAN-FORD -------------------------------------------------
	
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
    
    //---------------------------------------- MAIN -------------------------------------------------
    
    public static void main(String[] args) {
    	long timeS, timeE, timeR;
    	boolean isBF;
        BellmanFord BF = new BellmanFord();
        int n = 5; // número total de vértices n
        int p = 6; // número total de aristas p
        
        arista[] grafo = new arista[p];
        grafo[0] = BF.new arista(0,1,10);
        grafo[1] = BF.new arista(0,2,2);
        grafo[2] = BF.new arista(2,1,1);
        grafo[3] = BF.new arista(2,4,7);
        grafo[4] = BF.new arista(1,4,1);
        grafo[5] = BF.new arista(4,3,3);
        
        timeS = System.nanoTime();
        isBF = BF.caminosCortos(n, grafo);
        timeE = System.nanoTime();
        timeR = timeE - timeS;
        
        if(isBF)	{
        	for(int i = 0; i < BF.resultado.length; i++) System.out.print(BF.resultado[i]+" ");
        }
        else 
        	System.out.println("Hay un ciclo negativo en el gráfico dado, no es posible la distancia más corta");
    
        System.out.println("\nTiempo: " + timeR);
    }
}
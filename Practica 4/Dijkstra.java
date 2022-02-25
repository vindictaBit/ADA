import java.util.ArrayList;
import java.util.Arrays;

// -------------------- USANDO ARRAYS (MATRICES) --------------------
public class Dijkstra {
    public static void main(String[] args) {
        Dijkstra grafo = new Dijkstra(5);
        
        // Vertices
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");

        // Aristas
        grafo.insertarArista(0,1,10);
        grafo.insertarArista(0,2,2);
        grafo.insertarArista(2,1,1);
        grafo.insertarArista(2,4,7);
        grafo.insertarArista(1,4,1);
        grafo.insertarArista(4,3,3);

        mostrarArista();
        grafo.dijkStra(0);
    }
    
    // Aqu� est� el n�mero de punto fijo n, el conjunto de v�rtices "Vertice"
    // el conjunto de aristas. Si ha sido visitado, el marcador es "visitado"
    private int n;
    private int cantidadArista;
    private double[] distancia;
    private String[] caminito;
    private ArrayList<String> Vertice;
    private static int[][] aristas;
    private boolean[] visitado;
    
    public Dijkstra(int n){
        this.n = n;
        cantidadArista = 0;
        Vertice = new ArrayList<>(n);
        aristas  = new int[n][n];
        visitado = new boolean[n+1];
        distancia = new double[n];
        
        for (int i = 0; i < n; i++) {
        	// Infinito por defecto cuando no llega al siguiente v�rtice
            distancia[i] = Double.POSITIVE_INFINITY; 
        }

        caminito = new String[n];
        for (int i = 0; i < n ; i++) {
            caminito[i] = "";
        }
    }

    // Imprimir lista de adyacencia
    public static void mostrarArista(){
        for (int[] adya: aristas
        ) {
            System.out.println(Arrays.toString(adya));
        }

    }
    // Obtener el n�mero de v�rtices
    public  int tamanioGrafo(ArrayList<String> Vertice){
        return Vertice.size();
    }
    
    // Agregar v�rtice
    public  void insertarVertice(String s){
        Vertice.add(s);
    }
    
    // Obtener el primer v�rtice adyacente del v�rtice especificado
    public int primerCO(int index){
        for (int i = 0; i < Vertice.size() ; i++) {
            if (aristas[index][i] > 0) return i;
        }
        return n;
    }
    
    // Obtener los v�rtices adyacentes secuenciales del v�rtice especificado
    public int siguientCO(int index,int firstCO){
        for (int i = firstCO+1 ; i < Vertice.size(); i++) {
            if (aristas[index][i] > 0) return i;
        }
        return n;
    }
    
    // Agregar al borde
    public  void insertarArista(int e1,int e2 , int weight){
        aristas[e1][e2] = weight;
        cantidadArista++;
    }
    
    // Obtener el n�mero de aristas
    public int cantidadAristas(){
        return cantidadArista;
    }

    public void dijkStra(int index ){
        int CO; // CO son las coordenadas necesarias para la iteraci�n
        int indexInicio = index; // indexInicio es el v�rtice inicial de cada DIJKSTRA
        distancia[index]=0; // Establece la distancia desde el punto inicial al punto inicial en 0

        // ---------------- L�GICA -------------------
        // - Establece este v�rtice en conocido, no te preocupes por la distancia y la ruta de este punto,
        // porque ha sido dise�ado antes
        // - Encuentra cada v�rtice adyacente de este v�rtice. Para un v�rtice desconocido, 
        // compare la distancia alcanzada a lo largo de este v�rtice con su distancia original, 
        // si es menor que la distancia original, actualice la distancia y actualice la ruta
        // - Despu�s de establecer este v�rtice mediante la funci�n indice 
        
        while (!visitado[indexInicio]){

            // CO es la primera CO que no ha sido visitada
            CO = primerCO(indexInicio);
            while(visitado[CO]){
                CO = siguientCO(indexInicio,CO);
            }

            // Si el v�rtice indexInicio no tiene v�rtices adyacentes que no hayan sido visitado
            // la coordenada del v�rtice se obtiene como n, lo que indica que es el �ltimo 
            // nodo desconocido, y solo necesita establecerse como conocido
            if (CO==n) {
                visitado[indexInicio]=true;
            }
            // Ejecuta para todos los v�rtices adyacentes a trav�s de un bucle
            else {
                while (!visitado[CO] && CO < n) {
                    visitado[indexInicio]=true;
                    double disActual = distancia[indexInicio]+aristas[indexInicio][CO];
                    if (disActual < distancia[CO]) {
                        distancia[CO] = disActual;

                        caminito[CO] = caminito[indexInicio]+" "+Vertice.get(indexInicio);
                    }

                    CO = siguientCO(indexInicio, CO);

                }
            }

            indexInicio = indice(distancia,visitado);
        }
        
        for (int i = 0; i < n ; i++) {
            caminito[i] = caminito[i]+" "+Vertice.get(i);
        }
        
        System.out.println("\nIniciando en el nodo: " + Vertice.get(index) + "\n");
        for (int i = 0; i < n ; i++) {
            System.out.println(Vertice.get(i)+"   "+distancia[i]+"   "+caminito[i]);
        }


    }
    
    public int indice(double[] distancia, boolean[] visitado){
        int j = 0;
        double mindis = Double.POSITIVE_INFINITY; 
        for (int i = 0; i < distancia.length; i++) {
            if (!visitado[i]){
                if(distancia[i] < mindis){
                    mindis=distancia[i];
                    j=i;
                }
            }
        }
        
        // Retorna el v�rtice requerido mediante la matriz de distancia 
        // y la matriz de acceso insertadas
        return j;
    }
}

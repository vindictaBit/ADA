import java.awt.Desktop;
import java.io.*;    
import java.util.*;    
import javax.swing.JOptionPane;
import com.panayotis.gnuplot.JavaPlot;

public class DFS_BFS    
{    
    private int vEscogido;       				// número de vértices del grafo 
    private LinkedList<Integer> adya[];      	// lista de los vértices adyacentes, para DFS y BFS   
    private Queue<Integer> queue;           		// cola para el BFS    
    
    // el grafo no es dirigido  
    DFS_BFS(int v1)    
    {    
        vEscogido = v1;    
        adya = new LinkedList[vEscogido];    
        for (int i = 0; i < v1; i++)    
        {    
            adya[i] = new LinkedList<>();    
        }    
        queue = new LinkedList<Integer>();    
    }    
    
    void insertar(int v1,int v2)    
    {    
        adya[v1].add(v2);         
    }   
    
 // --------- DFS ---------
    void DFS(int v1)
    {
        // Array con los vertices que se visitan
        boolean recorrer[] = new boolean[vEscogido]; 	//4
        // vertices no visitado por defecto (falso)
        DFS(v1, recorrer);								
    }
    
    // DFS sobrecargado 
    void DFS(int v1, boolean recorrer[])
    {
        // marcar al vertice como visitado
        recorrer[v1] = true; 	//
 
        // visitando vertices
        Iterator<Integer> i = adya[v1].listIterator();	
        while (i.hasNext()) {							
            int n = i.next();
            if (!recorrer[n])
                DFS(n, recorrer);
        }
    }
 // --------- DFS ---------
    
 // --------- BFS ---------
    void BFS(int n)    
    {    
        boolean recorrer[] = new boolean[vEscogido];   
        int a = 0;    
        recorrer[n]=true; 
        // n será la raíz                 
        queue.add(n);      
        while (queue.size() != 0)    
        {    
            n = queue.poll();             
            // recorriendo 
            for (int i = 0; i < adya[n].size(); i++)    
            {    
                a = adya[n].get(i); 
                // insertando en la lista los vertices no visitados  
                if (!recorrer[a])        
                {    
                    recorrer[a] = true;    
                    queue.add(a);    
                }    
            }      
        }    
    }    
 // --------- BFS ---------
    
    public static void main(String args[]) throws IOException    
    {    
    	File dfs = null;
		File bfs = null;
		BufferedWriter bw = null;
		BufferedWriter bw2 = null;
		dfs = new File("dfs.txt");
		bfs = new File("bfs.txt");
		dfs.createNewFile();
		bfs.createNewFile();
		bw = new BufferedWriter(new FileWriter(dfs));
		bw2 = new BufferedWriter(new FileWriter(bfs));
    	
    	long timeS, timeE;
    	
    	DFS_BFS DB = new DFS_BFS(4);  
    	// tendremos 4 vértices
    	DB.insertar(0, 1);
        DB.insertar(0, 2);
        DB.insertar(1, 2);
        DB.insertar(2, 0);
        DB.insertar(2, 3);
        DB.insertar(3, 3);
        
        // i será el inicio de los recorridos DFS y BFS
    	for (int i = 0; i < 4; i++) {
    		timeS = System.nanoTime();
    		DB.DFS(i);
    		timeE = System.nanoTime();
    		bw.write((i) + "\t" + (timeE - timeS) + "\n"); 
    		// Imprime vértice escogido y tiempo
    		
    		timeS = System.nanoTime();
    		DB.BFS(i);  
    		timeE = System.nanoTime();
    		bw2.write((i) + "\t" + (timeE - timeS) + "\n"); 
		}
		
    	bw.close();
		bw2.close();
		Desktop.getDesktop().open(dfs);
		Desktop.getDesktop().open(bfs);
		JavaPlot p = new JavaPlot();

        p.addPlot("\"dfs.txt\" with lines");
        p.addPlot("\"bfs.txt\" with lines");
        p.plot();
         
    }    
}    

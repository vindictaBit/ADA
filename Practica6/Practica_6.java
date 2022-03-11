   
import java.util.*;    
import java.util.Collections;

public class Practica_6 {
    //---------------------------------------- MAIN -------------------------------------------------
    public static void main(String[] args)
    {
    	long timeS, timeE, timeR;
    	System.out.println("\n--- Radix Sort ---");
        int arr[] = { 30, 0, 1, 15, 2 };
        timeS = System.nanoTime();
        radixsort(arr);
        timeE = System.nanoTime();
        timeR = timeE - timeS;
        imprimir(arr, timeR);
        
        System.out.println("\n--- Bucket Sort ---");
        float arr2[] = { (float)0.331, (float)0,(float)0.11, (float)0.151,(float)0.245 };
        timeS = System.nanoTime();
        bucketSort(arr2);
        timeE = System.nanoTime();
        timeR = timeE - timeS;
	imprimir(arr2, timeR);

	System.out.println("\n--- Counting Sort ---");
	int[] arr3 = { -30, 0, -1, 15, 2 };
	timeS = System.nanoTime();
        countSort(arr3);
        timeE = System.nanoTime();
        timeR = timeE - timeS;
        imprimir(arr3, timeR);
    }
	
	static void imprimir(int[] arr, long timeR)
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nTiempo: " + timeR);
    }
    static void imprimir(float[] arr, long timeR)
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nTiempo: " + timeR);
    }
	
    //---------------------------------------- RADIX SORT-------------------------------------------------
    
	// obtiene el número máximo para saber el número de dígitos
	static int getMax(int arr[])
    {
    	int n = arr.length;
        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
 
    // ordenando de acuerdo al exponente
    static void countSort(int arr[], int n, int exp)
    {
        int salida[] = new int[n]; 
        int i;
        int conteo[] = new int[10];
        Arrays.fill(conteo, 0);
 
        // almacenar ocurrencias
        for (i = 0; i < n; i++)
            conteo[(arr[i] / exp) % 10]++;
 
        // conteo[i] contendrá el dígito de salida[]
        for (i = 1; i < 10; i++)
            conteo[i] += conteo[i - 1];
        
        for (i = n - 1; i >= 0; i--) {
            salida[conteo[(arr[i] / exp) % 10] - 1] = arr[i];
            conteo[(arr[i] / exp) % 10]--;
        }
 
        // ordenado
        for (i = 0; i < n; i++)
            arr[i] = salida[i];
    }
 
    static void radixsort(int arr[])
    {
    	int n = arr.length;
        int max = getMax(arr);

        // exp es 10^i, i es el número de dígito actual
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
 
  //---------------------------------------- BUCKET SORT-------------------------------------------------
    
    static void bucketSort(float arr2[])
    {
    	int n = arr2.length;
        if (n <= 0)
            return;
 
        @SuppressWarnings("unchecked")
        Vector<Float>[] buck = new Vector[n];
 
        for (int i = 0; i < n; i++) {
            buck[i] = new Vector<Float>();
        }
 
        // distribuir
        for (int i = 0; i < n; i++) {
            float idx = arr2[i] * n;
            buck[(int)idx].add(arr2[i]);
        }
 
        // ordenar
        for (int i = 0; i < n; i++) {
            Collections.sort(buck[i]);
        }
 
        // unir
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buck[i].size(); j++) {
                arr2[index++] = buck[i].get(j);
            }
        }
    }
    
  //---------------------------------------- COUNTING SORT-------------------------------------------------
    
    static void countSort(int[] arr3)
    {
    	int n = arr3.length;
        int max = Arrays.stream(arr3).max().getAsInt();
        int min = Arrays.stream(arr3).min().getAsInt();
        int rango = max - min + 1;
        int conteo[] = new int[rango];
        int salida[] = new int[n];
        
        for (int i = 0; i < n; i++) 
            conteo[arr3[i] - min]++;
  
        for (int i = 1; i < conteo.length; i++) 
            conteo[i] += conteo[i - 1];
  
        for (int i = n - 1; i >= 0; i--) {
            salida[conteo[arr3[i] - min] - 1] = arr3[i];
            conteo[arr3[i] - min]--;
        }
  
        for (int i = 0; i < n; i++) 
            arr3[i] = salida[i];
    }

}

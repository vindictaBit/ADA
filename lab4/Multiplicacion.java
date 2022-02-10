public class Multiplicacion {
 
	// -------------------------- Fuerza Bruta ---------------------------
	public int[][] multiplicarFB(int[][] A, int[][] B){
		int[][] C = new int[A.length][B[0].length];
		
	    // Condición para multiplicar 2 matrices
	    if (A[0].length == B.length) {
	        for (int i = 0; i < A.length; i++) {
	            for (int j = 0; j < B[0].length; j++) {
	                for (int k = 0; k < A[0].length; k++) {
	                    C[i][j] += A[i][k] * B[k][j];
	                }
	            }
	        }
	    }
	    
	    return C;
	}
	
	// -------------------------- Strassen ---------------------------
    public int[][] multiplicarStr(int[][] A, int[][] B)
    {
        // Orden de las matrices
        int n = A.length;
        int[][] ma = new int[n][n];
 
        if (n == 1) // Por si solo hay 1 elemento
            ma[0][0] = A[0][0] * B[0][0];
 
        else {
            // Dividiendo las matrices
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];
 
            // Dividiendo las matrices A y B en 4 mitades cada una
            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);
            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);
 
            // M1:=(A1+A3)×(B1+B2)
            int[][] M1
                = multiplicarStr(add(A11, A22), add(B11, B22));
           
            // M2:=(A2+A4)×(B3+B4)
            int[][] M2 = multiplicarStr(add(A21, A22), B11);
           
            // M3:=(A1−A4)×(B1+A4)
            int[][] M3 = multiplicarStr(A11, sub(B12, B22));
           
            // M4:=A1×(B2−B4)
            int[][] M4 = multiplicarStr(A22, sub(B21, B11));
           
            // M5:=(A3+A4)×(B1)
            int[][] M5 = multiplicarStr(add(A11, A12), B22);
           
            // M6:=(A1+A2)×(B4)
            int[][] M6
                = multiplicarStr(sub(A21, A11), add(B11, B12));
           
            // M7:=A4×(B3−B1)
            int[][] M7
                = multiplicarStr(sub(A12, A22), add(B21, B22));
 
            // P:=M2+M3−M6−M7
            int[][] C11 = add(sub(add(M1, M4), M5), M7);
           
            // Q:=M4+M6
            int[][] C12 = add(M3, M5);
           
            // ma:=M5+M7
            int[][] C21 = add(M2, M4);
           
            // S:=M1−M3−M4−M5
            int[][] C22 = add(sub(add(M1, M3), M2), M6);
 
            // Cohesionar las partes en una sola
            join(C11, ma, 0, 0);
            join(C12, ma, 0, n / 2);
            join(C21, ma, n / 2, 0);
            join(C22, ma, n / 2, n / 2);
        }
 
        return ma;
    }
 
    // Quitar las matrices
    public int[][] sub(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
 
    // Añadir 2 matrices
    public int[][] add(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
 
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
 
        return C;
    }
 
    // Separar matriz en mitades
    public void split(int[][] P, int[][] C, int iB, int jB)
    {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
            	C[i1][j1] = P[i2][j2];
    }
 
    // Cohesionar matrices
    public void join(int[][] C, int[][] P, int iB, int jB)
    {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
            	P[i2][j2] = C[i1][j1];
    }
 
    
    
    // ------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
    	Multiplicacion multiplic = new Multiplicacion();
        int N = 2;
        int[][] A = { { 1, 2},
                      { 4, 3} };
        int[][] B = { { 1, 0},
                      { 1, 2} };
        
        // FUERZA BRUTA
        int[][] C1 = multiplic.multiplicarFB(A, B);
        
        System.out.println("Resultado por Fuerza Bruta:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(C1[i][j] + " ");
            System.out.println();
        }
        System.out.println("");
        
        
        // STRASSEN
        int[][] C2 = multiplic.multiplicarStr(A, B);
 
        System.out.println("Resultado por Strassen:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(C2[i][j] + " ");
            System.out.println();
        }
        System.out.println("");
        
    }
}
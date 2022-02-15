import java.util.Scanner;
public class AlgoritmoEuclides {

	public static int CalcularMCD(int a, int b) {
		if(a<0 || b<0) // definición para el algoritmo de Euclides. Si alguno es negativo, no es posible
			return -1;
		if(b==0) 
			return a;
		else 
			return AlgoritmoEuclides.CalcularMCD(b, a%b);
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Ingrese a: ");
		int a = in.nextInt();
		System.out.print("");
		System.out.print("Ingrese b: ");
		int b = in.nextInt();
		System.out.print("");
		in.close();
		
		// Propiedad: mcm = (a*b) / mcd(a,b)
		System.out.println("MCM(" +a+ ",  " +b+ ") = " + ((a*b) / CalcularMCD(a, b)));
	}
}


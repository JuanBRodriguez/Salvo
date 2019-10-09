package poo;

import java.util.ArrayList;

public class Anagrama {

	public static void main(String[] args) {
		int a= solucion("hola, que buena ola Laomir", "ALO");
		System.out.print("Encontrado en "+ a +" oportunidades");
	}
	
	public static int solucion(String A, String B){
		A=A.toLowerCase();
		B=B.toLowerCase();
		
		int larB = B.length();
		int cantidad=0;
		int ubicacion=0;
		
		String a[] = A.split(" ");
		char aB[] = B.toCharArray();
		
		ArrayList<String> aprobados= new ArrayList<String>();
		
		combinaciones = "";
		String co = generarAnagramas(aB, "", larB);
		String comb[] = co.split(" ");
		        
		for (int i = 0; i < a.length; i++) {
			if (a[i].length()>=larB) {
				int posee =0;
				for (int j = 0; j < aB.length; j++) {
					if (a[i].indexOf(aB[j])> -1) 
						posee++;
				}
				if(posee == larB)
					aprobados.add(a[i]);
			}
		}
		
		for (int i = 0; i < aprobados.size(); i++) {
			for (int j = 0; j < comb.length; j++) {
				System.out.print(aprobados.get(i)+ " item "+comb[j]);
				if(aprobados.get(i).indexOf(comb[j])>-1) {
					System.out.println(" si ");
					cantidad++;
				}else {
					System.out.println(" no ");
				}
			}
		}
		
		return cantidad;
	}
	
    static boolean[] control = new boolean[100];
    static String combinaciones = "";
    
    //Genero todos los anagramas posibles de la palabra dada y los guardo en un string separados por espacios
    public static String generarAnagramas(char[] elementos, String actual, int cantidad) { 
    	
        if(cantidad==0) {// Detecta secuencia generada
            System.out.println(actual);
            combinaciones+= actual+" ";
        }
        else {
            for(int i=0; i<elementos.length; i++) {
                if(control[i]==true) continue;
                control[i]=true;
                generarAnagramas(elementos, actual+elementos[i],cantidad-1);
                control[i]=false;
            }
        }
        return combinaciones;
    }
	
}

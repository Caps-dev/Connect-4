public class Juego {

	public int [][] generarMatriz(int f, int c){

		int [][] matrizInt = new int [f][c];
		int contador = 0;
		return matrizInt;
	}

	public int clamp(int valor, int superior){
  		int resultado = 0;
  		if (valor < 0){
   			resultado = 0;
   		} else if (valor > superior){
   			resultado = superior;
   		}

   		return resultado;
   	}


	public void imprimir (int [][] matriz){
		for (int f = 0; f < matriz.length; f++){// Itera por todas las filas => Cambia la columna hacia abajo
			for (int c = 0; c < matriz[f].length; c++){// Iterando por las columnas => imprime una fila completa
				System.out.print(matriz[f][c] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public int agregarFicha(int [][] matriz,int cUsuario, int id){
		// intenta agregar una ficha.
		// Si no habia espacio devuelve un status -1
		int status = -1; //exito
		for (int f = matriz.length-1; f >= 0; f--){ // itera por filas
			if (matriz[f][cUsuario] ==0){ //si no hay una ficha
				matriz[f][cUsuario] = id;
				f= -1;
				status = 1; //exito
			}
			if(f==0 && status==-1){
				System.out.println("No se puede agregar ficha en la columna deseada");
			}
		}
		return status;
	}



}
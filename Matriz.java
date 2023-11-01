public class Matriz {

	private int columna = 0; // por si quisieramos definirlo como negativo despues o algo
	private int fila = 0;
	private int vacio = 0; // por si quisieramos definirlo como negativo despues o algo
	private int[][] matriz;

	public Matriz(int nfilaParamentro, int ncolumnaParametro) { // constructor por omision

		this.matriz = new int[nfilaParamentro][ncolumnaParametro];

	}

	public int getnfila() {
		return fila;
	}

	public int getncolumnas() {
		return columna;
	}

	public void imprimir() {
		for (int f = 0; f < matriz.length; f++) {// Itera por todas las filas => Cambia la columna hacia abajo
			for (int c = 0; c < matriz[f].length; c++) {// Iterando por las columnas => imprime una fila completa
				System.out.print(matriz[f][c] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public int agregarFicha(int cUsuario, int id) {
		// intenta agregar una ficha.
		// Si no habia espacio devuelve un status -1
		int status = -1; // falla
		for (int f = matriz.length - 1; f >= 0; f--) { // itera por filas
			if (matriz[f][cUsuario] == vacio) { // si no hay una ficha
				matriz[f][cUsuario] = id;

				this.fila = f;
				this.columna = cUsuario;
				f = -1;
				status = 1; // exito
			}
			if (f == 0 && status == -1) {
				System.out.println("No se puede agregar ficha en la columna deseada");
			}
		}
		return status;
	}

	public int hayEspacio() { // retorna -1 si hay espacio para fichas; 9 si no hay espacio
		int espaciosVacios = 0;

		for (int i = 0; i < matriz[0].length; i++) {

			if (matriz[0][i] == 0) {
				espaciosVacios++;
			}

		}

		// System.out.println(espaciosVacios);

		int estado = (espaciosVacios > 0) ? -1 : 9; // si hay espacios vacios hay espacio

		return estado; // se da un codigo particular para caso donde no hay espacio
	}

	public int verificarVertical(int id) {

		int status = -1; // estado por defecto
		int suma = 0; // condicion para ganar

		for (int c = matriz[0].length - 1; c >= 0; c--) {// Iterando por las columnas
			suma = 0; // resetea el contador

			for (int f = matriz.length - 1; f >= 0; f--) { // itera por filas

				if (matriz[f][c] == id) {
					suma += id;

				} else {
					suma = 0; // resetea el contador
				}

				if (suma >= id * 4) {
					status = 1;
					f = -1;
					c = -1;
					System.out.println("gana verticalmente");
				}
				// System.out.println("F:" + f + "\tC:" + c);
			}
		}
		return status;
	}

	// public for dev purposes
	public int verificarHorizontal(int id) { // todo: decir las coordenadas con las que gano
		// System.out.println("Verificando Horizontalmente");
		int status = -1; // estado por defecto
		int suma = 0; // condicion para ganar

		for (int f = matriz.length - 1; f >= 0; f--) {
			// System.out.println("Iteracion de F:" + f);

			for (int c = matriz[0].length - 1; c >= 0; c--) {

				if (matriz[f][c] == id) {
					suma += id;
				} else {
					suma = 0; // resetea el contador
				}

				if (suma >= id * 4) {
					status = 2;
					c = -1;
					f = -1;
					System.out.println("gana horizontalmente");

				}
				// System.out.println("F:" + f + "\tC:" + c);

			}
		}
		return status;
	}

	public int verificarDiagonal(int id, int fila, int columna, int dF, int dC, int cantidad) {
	    int status = 0;
	    int suma = 1;
	    int resultado = 1;
	    

	    if (fila < matriz.length && columna < matriz[0].length && fila >= 0 && columna >= 0) {
	        if (id == matriz[fila][columna]) {
	            resultado += verificarDiagonal(id, fila + dF, columna + dC, dF, dC, cantidad);
	        }
	    } else{
	    	resultado = 1;
	    }
	    return resultado;
	}



	public int verificarDiagonales(int id, int fila, int columna){

		int status = -1;

	    int arribaDerecha = verificarDiagonal(id, fila, columna, -1, 1,4) -1 ; //hacia arriba y la derecha
	    int abajoIzquierda = verificarDiagonal(id, fila, columna, 1, -1,4) -1  ; //hacia arriba y la derecha
	    int diagonalInversa = arribaDerecha+ abajoIzquierda-1 ;

	    /// int EJEMPLO = verificarDiagonal(id, fila, columna, dF, dC,SUMA) ; //ejemplo
	    int arribaIzquierda = verificarDiagonal(id, fila, columna, -1, -1,4) -1 ; //hacia arriba y la derecha
	    int abajoDerecha = verificarDiagonal(id, fila, columna, 1, 1,4) -1 ; //hacia arriba y la derecha
	    int diagonal = arribaIzquierda + abajoDerecha -1;
	   /*
	    System.out.println("---------------------------------");
	    System.out.println("coordenadas " + fila + " , " + columna);
	    System.out.println("Valor de la entrada " + matriz[fila][columna]);
	    System.out.println("SUMA arribaDerecha" + arribaDerecha);
	    System.out.println("SUMA abajoIzquierda" + abajoIzquierda);
	    System.out.println("SUMA TOTAL DIAGONAL INVERSA " + diagonalInversa);
	    System.out.println("---------------------------------");

		System.out.println("---------------------------------");
	    System.out.println("SUMA arribaIzquierda" + arribaIzquierda);
	    System.out.println("SUMA abajoDerecha" + abajoDerecha);
	    System.out.println("SUMA TOTAL DIAGONAL regular " + diagonalInversa);
	    System.out.println("---------------------------------");

	*/
	    if (diagonalInversa>=4 || diagonal >=4){
	    	status = 4;
	    }

		return status;



	}



	public int verificarEstado(int id) {
		int estado = -1;


		estado = verificarVertical(id);
		if (estado == -1) {
			estado = verificarHorizontal(id);
		} 

		if (estado == -1) {
			System.out.println("verificando diagonal------------------------------------");

			estado = verificarDiagonales(id,fila, columna);
		}

		if (estado == -1){
			estado = hayEspacio();

		}

		return estado;
	}

}
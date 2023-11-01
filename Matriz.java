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

	public int verificarVertical(int id) { // todo: decir las coordenadas con las que gano
		// System.out.println("Verificando Verticalmente"); // dev purposes
		int status = -1; // estado por defecto
		int suma = 0; // condicion para ganar

		for (int c = matriz[0].length - 1; c >= 0; c--) {// Iterando por las columnas
			// System.out.println("Iteracion de C:" + c);

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

		for (int f = matriz.length - 1; f >= 0; f--) {// Iterando por las filas
			// System.out.println("Iteracion de F:" + f);

			for (int c = matriz[0].length - 1; c >= 0; c--) { // itera por filas

				if (matriz[f][c] == id) {
					suma += id;
				} else {
					suma = 0; // resetea el contador
				}

				if (suma >= id * 4) {
					status = 1;
					c = -1;
					f = -1;
				}
				// System.out.println("F:" + f + "\tC:" + c);

			}
		}
		return status;
	}

	public int verificarDiagonal(int id, int fila, int columna, int dF, int dC,
			int cantidad, int contador) {
		// implementacion recursiva
		int status = 0;
		if (contador >= cantidad) {
			status = 1;
		} else {
			int nF = fila + dF; //nueva fila
			int nC = columna + dC; // nueva columna
			if (nF >= 0 && nC >= 0 && nF < matriz.length &&	nC < matriz[nF].length) {
				if (id == matriz[nF][nC]) {
					status = verificarDiagonal(id, nF, nC, dF, dC, cantidad, contador + 1);
				}
			}
		}
		if (contador == cantidad) {
			status = 1;
		}

		return status;
	}

	public int verificarEstado(int id) {
		int estado = -1;

		// Verificar vertical y horizontal
		estado = verificarVertical(id);
		if (estado == -1) {
			estado = verificarHorizontal(id);
		}

		// Verificar diagonales (izquierda y derecha)
		if (estado == -1) {
			int diagonalIzquierdaAbajo = verificarDiagonal(id, fila, columna, 1, -1, 4, 1);
			int diagonalDerechaAbajo = verificarDiagonal(id, fila, columna, 1, 1, 4, 1);
			int diagonalDerechaArriba = verificarDiagonal(id, fila, columna, -1, 1, 4, 1);
			int diagonalIzquierdaArriba = verificarDiagonal(id, fila, columna, -1, -1, 4, 1);

			if (diagonalIzquierdaAbajo == 1 || diagonalDerechaAbajo == 1 || diagonalDerechaArriba == 1
					|| diagonalIzquierdaArriba == 1) {
				estado = 1; // El jugador ha ganado en alguna de las diagonales
			} else {
				estado = -1; // El jugador no ha ganado en ninguna diagonal
			}
		}

		return estado;
	}

}
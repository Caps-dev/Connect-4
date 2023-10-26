public class Matriz {

	private int columnas = 0; // por si quisieramos definirlo como negativo despues o algo
	private int filas = 0;
	private int vacio = 0; // por si quisieramos definirlo como negativo despues o algo
	private int[][] matriz;

	public Matriz(int nfilaParamentro, int ncolumnaParametro) { // constructor por omision

		columnas = ncolumnaParametro;
		filas = nfilaParamentro;
        this.matriz = new int[filas][columnas];

	}

	public int getnfila(){
		return filas;
	}

	public int getncolumnas(){
		return columnas;
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
				f = -1;
				status = 1; // exito
			}
			if (f == 0 && status == -1) {
				System.out.println("No se puede agregar ficha en la columna deseada");
			}
		}
		return status;
	}

	public int verificarVertical( int id) { // todo: decir las coordenadas con las que gano
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
	public int verificarHorizontal( int id) { // todo: decir las coordenadas con las que gano
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

	public int verificarEstado(int id) {
		int estado = -1;
		estado = verificarVertical(id);
		if (estado == -1) {
			estado = verificarHorizontal(id);
		}

		return estado;

	}


}
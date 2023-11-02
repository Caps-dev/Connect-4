//! \class Clase Matriz del juego 4 en linea
/*!
  Clase encargada de representar el tablero de juego donde se insertan las fichas
  Es la implementacion interna donde se realizan verificaciones sobre la logica que complementa con la interfaz para una mejor experiencia
*/
public class Matriz {

	private int columna = 0; /*!< columna de la matriz. Permite identificar donde se ha ingresado la ficha */
	private int fila = 0;  /*!< fila de la matriz. Permite identificar donde se ha ingresado la ficha */
	private int vacio = 0; /*!< Espacio vacio en la matriz. Se usa un atributo para permitir modificaciones futuras */
	private int[][] matriz; /*!< Arreglo de dos dimensiones (matriz) que representa el tablero donde se ingresan las fichas */

	/*! \brief Constructor sobrecargado
	 *
	 *Se utiliza para instanciar el objeto y asignarle el numero de filas y columnas a la matriz.
	\param nfilaParamentro número entero para indicar el numero de filas.
    \param ncolumnaParametro número entero para indicar el numero de columnas.
	 */
	public Matriz(int nfilaParamentro, int ncolumnaParametro) {

		this.matriz = new int[nfilaParamentro][ncolumnaParametro];

	}

  // Metodos de retorno

    /*! \brief Retorna el valor actual de la fila
     *
     \return int fila actual de la matriz
     */
	public int getnfila() {
		return fila;
	}

    /*! \brief Retorna el valor actual de la columna
     *
     \return int columna actual de la matriz
     */
	public int getncolumnas() {
		return columna;
	}

	// Metodos
	// Acciones

	/*! \brief Imprime el contenido de la matriz / tablero
	 *
	 * Imprime en consola el contenido de la matriz. 
	 * Metodo usado para el desarrollo y testeo del juego. 
	 * El usuario no interactua con este metodo.
	 */
	public void imprimir() {
		for (int f = 0; f < matriz.length; f++) {// Itera por todas las filas => Cambia la columna hacia abajo
			for (int c = 0; c < matriz[f].length; c++) {// Iterando por las columnas => imprime una fila completa
				System.out.print(matriz[f][c] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	/*! \brief Agrega una ficha al tablero.
	 *
	 * El usuario escoge la columna, mediante una interfaz y se agrega una ficha al tablero. \n
	 * La ficha se representa mediante el ID del jugador. \n
	 * Si hay espacios disponibles se agrega la ficha encima de la ultima ficha colocada. \n
	 * Si no hay espacios disponibles para agregar la ficha se retorna un -1 \n
	 * Si habia espacios disponibles y se inserta la ficha se retorna un 1. \n
	 * (el valor de retorno se utiliza en la interfaz para asignar el mensaje correcto al usuario)\n
	 \param int Columna escogida por el usuario
	 \param int Identificador del usuario
	 \return int entero que indica si se pudo agregar la ficha. (1 exito, -1 fracaso)
	 */
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
			//if (f == 0 && status == -1) { // dev purposes
				//System.out.println("No se puede agregar ficha en la columna deseada");
				// dev purposes
			//}
		}
		return status;
	}

	/*! \brief Verifica si hay espacios disponibles para agregar una ficha.
	 *
	 * Itera por la primera fila del tablero / matriz, cuenta cuantos espacios vacios hay. \n
	 * Si hay espacios disponibles se retorna -1. \n
	 * Si no hay espacios disponibles se retorna 9 \n
	 \return int entero que indica si hay espacios vacios. (-1 hay espacio, 9 no hay espacios vacios)
	 */
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

	// Metodos para verificar si se ha ganado

	/*! \brief Verifica si hay 4 o más fichas de un jugador de manera seguida vertical.
	 *
	 \param id identificador del jugador.
 	 \return int entero que indica si gana verticalmente (1 gana, -1 no ha ganado)

	 */
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
					//System.out.println("gana verticalmente");
				}
				// System.out.println("F:" + f + "\tC:" + c);
			}
		}
		return status;
	}

	/*! \brief Verifica si hay 4 o más fichas de un jugador de manera seguida horizontal.
	 *
	 \param int id identificador del jugador.
	 \return int entero que indica si gana horizontalmente (2 gana, -1 no ha ganado)

	 */
	public int verificarHorizontal(int id) { // todo: decir las coordenadas con las que gano
		System.out.println("Verificando Horizontalmente");
		int status = -1; // estado por defecto
		int suma = 0; // condicion para ganar

		for (int f = matriz.length - 1; f >= 0; f--) {
			// System.out.println("Iteracion de F:" + f);
			suma = 0; // resetea el contador al cambiar de fila

			for (int c = matriz[0].length - 1; c >= 0; c--) {
			 	System.out.println("Iteracion de c:" + c);
			 	System.out.println("Coordenadas" + f + " , " + c);
			 	System.out.println("Suma " + suma);

				if (matriz[f][c] == id) {
					suma += id;
				} else {
					suma = 0; // resetea el contador
				}

				if (suma >= id * 4) {
					status = 2;
					c = -1;
					f = -1;
					
				}
				// System.out.println("F:" + f + "\tC:" + c);

			}
		}
		return status;
	}





	/*! \brief Verifica cuantas fichas de un jugador de manera seguida diagonal hay, dada una direccion.
	 * 
	 * Implementacion recursiva para verificar diagonalmente segun las coordenadas de la ultima ficha jugada. 
	 * Mediante los argumentos de direccion (dF y dC) se puede mover diagonalmente en las 4 direcciones. \n
	 * Se compara la ficha insertada contra las de la diagonal verificando que tengan el mismo id. Para cuando encuentra una fila con un id diferente.
	 * Se suma 1 cada vez que esto se cumple. /n
	 * La suma final esta sobreestimada de manera consistente en 1, lo cual se considera en metodos que utilicen a este metodo.

	 *
	 \param int id identificador del jugador.
	 \param int fila jugada.
	 \param int columna jugada.
	 \param int dF direccion de la Fila.
	 \param int dC direccion de la Columna
	 \param int cantidad de iteraciones

  	 \return int entero que indica la suma de fichas consecutivas iguales.


	 */
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


	/*! \brief Verifica si hay 4 o más fichas de un jugador de manera seguida en las 2 diagonales
	 * 
	 * Llama al metodo verificarDiagonal y se le dan las direcciones para las 4 diagonales. \n
	 * Suma las dos partes de cada diagonal (arriba y abajo) y verifica si es igual o mayor a 4.

	 *
	 \param int id identificador del jugador.
	 \param int fila jugada.
	 \param int columna jugada.

	 \return int entero que indica si gana diagonalmente (4 gana, -1 no ha ganado)

	 */
	public int verificarDiagonales(int id, int fila, int columna){

		int status = -1;

	    int arribaDerecha = verificarDiagonal(id, fila, columna, -1, 1,4) -1 ; //hacia arriba y la derecha
	    int abajoIzquierda = verificarDiagonal(id, fila, columna, 1, -1,4) -1  ; //hacia arriba y la derecha
	    int diagonalInversa = arribaDerecha+ abajoIzquierda-1 ;

	    /// int EJEMPLO = verificarDiagonal(id, fila, columna, dF, dC,SUMA) ; //ejemplo
	    int arribaIzquierda = verificarDiagonal(id, fila, columna, -1, -1,4) -1 ; //hacia arriba y la derecha
	    int abajoDerecha = verificarDiagonal(id, fila, columna, 1, 1,4) -1 ; //hacia arriba y la derecha
	    int diagonal = arribaIzquierda + abajoDerecha -1;
	   /* // dev purposes
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


	/*! \brief Verifica el estado de un jugador y determina si ya gano o no.
	 * 
	 * Llama a los metodo verificarVertical, verificarHorizontal y verificarDiagonal para determinar si un jugador ha ganado o no. 
	 *
	 \param int id identificador del jugador.

	 \return int entero que indica el estado (-1: no ha ganado, 9: empate, otros enteros: ganó)

	 */
	public int verificarEstado(int id) {
		int estado = -1;


		estado = verificarVertical(id);
		if (estado == -1) {
			estado = verificarHorizontal(id);
		} 

		if (estado == -1) {
			estado = verificarDiagonales(id,fila, columna);
		}

		if (estado == -1){
			estado = hayEspacio();

		}

		return estado;
	}

}
//! \class Clase Juego de 4 en linea
/*!
  Clase encargada de representar el estado y reglas del juego.
  Proporciona métodos relacionados a los turnos y verificacion sobre si el juego se encuentra activo o, si más bien, ha terminado por un gane o un empate.
*/
public class Juego {

	// Atributos
	
	private int cantidadDeTurnos = 0; /*!< Cantidad de turnos que lleva la partida */
	private int esActivo = 1; /*!< Estado actual del juego. 1 cuando el juego esta activo, -1 cuando el juego no es activo*/

    // Metodos de asignación

    /*! \brief Asigna un estado al juego
     *
     \param int representacion del estado del juego. 1 indica que se encuentra activo
     */
	public void setEsActivo(int esActivo) {
		this.esActivo = esActivo;
	}

    /*! \brief Asigna la cantidad de turnos al juego
     *
     \param int cantidad de turnos que lleva la partida. Inicia en 0
     */
	public void setCantidadDeTurnos(int cantidadDeTurnos) {
		this.cantidadDeTurnos = cantidadDeTurnos;
	}

    // Metodos de retorno
    /*! \brief Retorna el estado del juego
     *
     \return int representacion numerica del estado del juego.
     */
	public int getEsActivo() {
		return esActivo;
	}

    /*! \brief Retorna el estado del juego
     *
     \return int cantidad de turnos que lleva el juego.
     */
	public int getCantidadDeTurnos() {
		return cantidadDeTurnos;
	}

	// Metodos
	// Acciones

	/*! \brief Agrega un turno al juego.
     *
     \return int cantidad de turnos del juego
     */
	public int SumarTurno() {
		cantidadDeTurnos++;
		return cantidadDeTurnos;
	}

	/*! \brief Verifica el ganador del  juego
	 *
	 Mediante la verificacion del estado de cada jugador se retorna un entero que representa el estado del juego de manera que: \n
	 -1: Ningun jugador ha ganado \n
	 9: Empate \n
	 2: Uno de los dos jugadores ha ganado \n

	 En caso de haber un ganador o un empate se cambia el atributo 'esActivo' a -1

	 \param Matriz matriz que representa el tablero de juego
     \param int id del jugador 1
     \param int id del jugador 2

	 \return int representacion numerica del estado del juego
	 */
	public int verificarGanador(Matriz matriz, int idJ1, int idJ2) {
		int estadoJuego = -1;
		int estadoJ1 = matriz.verificarEstado(idJ1);
		int estadoJ2 = matriz.verificarEstado(idJ2);
		//System.out.println("Estado Jugador 1:" + estadoJ1);
		//System.out.println("Estado Jugador 2:" + estadoJ2);


		if (estadoJ1== 9) {
			System.out.println("Empate");
			estadoJuego = 9;
			esActivo = -1;
		} else if (estadoJ1!= -1) {
			System.out.println("Gana Jugador " + idJ1);
			estadoJuego = 2;
			esActivo = -1;
		} else if (estadoJ2 != -1) {
			estadoJuego = 2;
			esActivo = -1;
			System.out.println("Gana Jugador " + idJ2);
		} else {
			estadoJuego = -1;
		}

		return estadoJuego;

	}


}
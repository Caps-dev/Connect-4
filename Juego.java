public class Juego {
	// quiero dejar a Juego a cargo solamente de los turnos y de mantener el loop
	// del juego

	private int vacio = 0; // por si quisieramos definirlo como negativo despues o algo
	private int cantidadDeTurnos = 0;
	private int esActivo = 1;

	public int getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(int esActivo) {
		this.esActivo = esActivo;
	}

	public int SumarTurno() {
		cantidadDeTurnos++;
		return cantidadDeTurnos;
	}
	public void setCantidadDeTurnos(int cantidadDeTurnos) {
		this.cantidadDeTurnos = cantidadDeTurnos;
	}

	public int getCantidadDeTurnos() {
		return cantidadDeTurnos;
	}

/*

	public int clamp(int valor, int superior) {
		int resultado = 0;
		if (valor < 0) {
			resultado = 0;
		} else if (valor > superior) {
			resultado = superior;
		}

		return resultado;
	}
	*/

	public int verificarGanador(Matriz matriz, int idJ1, int idJ2) {
		int estado = -1;
		int estadoJ1 = matriz.verificarEstado(idJ1);
		int estadoJ2 = matriz.verificarEstado(idJ2);
		System.out.println("Estado Jugador 1:" + estadoJ1);
		System.out.println("Estado Jugador 2:" + estadoJ2);


		if (estadoJ1== 9) {
			System.out.println("Empate");
			estado = 9;
			esActivo = -1;
		} else if (estadoJ1!= -1) {
			System.out.println("Gana Jugador " + idJ1);
			estado = 2;
			esActivo = -1;
		} else if (estadoJ2 != -1) {
			estado = 2;
			esActivo = -1;
			System.out.println("Gana Jugador " + idJ2);
		} else {
			estado = -1;
		}

		return estado;

	}


}
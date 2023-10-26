public class Juego {
	// quiero dejar a Juego a cargo solamente de los turnos y de mantener el loop del juego

	private int vacio = 0; // por si quisieramos definirlo como negativo despues o algo
	private int cantidadDeTurnos = 0;
	private int esActivo = 1;

	public int getEsActivo(){
		return esActivo;
	}
	public void setEsActivo(int esActivo){
		this.esActivo = esActivo;
	}

	public int SumarTurno() {
		cantidadDeTurnos++;
		return cantidadDeTurnos;
	}

	public void setCantidadDeTurnos(int cantidadDeTurnos) {
		this.cantidadDeTurnos = cantidadDeTurnos;
	}

	public int clamp(int valor, int superior) {
		int resultado = 0;
		if (valor < 0) {
			resultado = 0;
		} else if (valor > superior) {
			resultado = superior;
		}

		return resultado;
	}

	public int verificarGanador(Matriz matriz, int idJ1, int idJ2) {
		int estado = -1;
	
		if(matriz.verificarEstado(idJ1)==1){
			System.out.println("Gana Jugador "+idJ1);
			estado = 1;
			esActivo = -1;
		}  else if(matriz.verificarEstado(idJ2) == 1){
			estado = 2;
			esActivo = -1;
			System.out.println("Gana Jugador "+idJ2);
		} else{
			estado= -1;
		}
		

		return estado;

	}



	public int getCantidadDeTurnos() {
		return cantidadDeTurnos;
	}

}

public class Main {
    public static void main(String args[]) {

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Matriz tablero = new Matriz(9, 9); // esta es la unica forma que encontre de calmar los errores
        // Juego juego = new Juego();
        /*
         * 
         * 
         * Matriz matriz = new Matriz(1,4);
         * matriz.imprimir();
         * matriz.agregarFicha(0,14);
         * matriz.agregarFicha(1,1);
         * matriz.agregarFicha(2,1);
         * matriz.agregarFicha(3,14);
         * 
         * //System.out.println(matriz.hayEspacio());
         * juego.verificarGanador(matriz,1,14);
         */
        Interfaz i = new Interfaz(jugador1, jugador2);

    }
}
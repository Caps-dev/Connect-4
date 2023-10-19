public class Main {
    public static void main(String args[]) {

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        jugador1.color = 255 * 256 * 256;

        Interfaz i = new Interfaz(jugador1, jugador2);

    }
}
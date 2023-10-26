import javax.swing.*; // no deberiamos llamar a toda la libreria
// import javax.swing.plaf.ColorChooserUI; // no hemos visto esto
import java.awt.Color;

public class Main {
    public static void main(String args[]) {

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        // Juego juego = new Juego();
        // Matriz matriz = new Matriz(5,5);
        // matriz.imprimir();
        // matriz.agregarFicha(0,14);
        // matriz.agregarFicha(1,14);
        // matriz.agregarFicha(2,14);

        // //matriz.imprimir();
        // System.out.println(juego.getEsActivo());
        // System.out.println(juego.getEsActivo());

        // while(juego.getEsActivo()==1){
        // System.out.println("Inicio de ciclo");
        // juego.verificarGanador(matriz,1,14); // el estado cambia hasta verificar el
        // tablero
        // matriz.agregarFicha(3,14); // lo meto aqui como demostracion de que el ciclo
        // corre dos veces
        // matriz.agregarFicha(4,14);
        // }

        Interfaz i = new Interfaz(jugador1, jugador2);

    }
}
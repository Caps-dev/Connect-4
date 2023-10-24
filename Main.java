import javax.swing.*;
import javax.swing.plaf.ColorChooserUI;
import java.awt.Color;

public class Main {
    public static void main(String args[]) {

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        // Juego juego = new Juego();

        // int [][] prueba = juego.generarMatriz(5,5);
        // int a = 0;
        // juego.imprimir(prueba);
        // a = juego.agregarFicha(prueba,0,1);
        // a = juego.agregarFicha(prueba,1,1);
        // a = juego.agregarFicha(prueba,2,1);
        // a = juego.agregarFicha(prueba,3,1);
        // a = juego.agregarFicha(prueba,4,1);

        // a = juego.agregarFicha(prueba,2,13);
        // a = juego.agregarFicha(prueba,2,13);
        // a = juego.agregarFicha(prueba,2,13);
        // a = juego.agregarFicha(prueba,2,13);

        // juego.imprimir(prueba);
        // //a = juego.agregarFicha(prueba,1,1);

        // //juego.verificarVertical(prueba,1);
        // //juego.verificarHorizontal(prueba,1);
        // juego.verificarEstado(prueba,1);
        // juego.verificarEstado(prueba,13);

        // juego.verificarVertical(prueba,13);

        // juego.agregarFicha(prueba,1,10);
        // juego.agregarFicha(prueba,1,10);

        Interfaz i = new Interfaz(jugador1, jugador2);

    }
}
public class Main {
    public static void main(String args[]) {

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        jugador1.color = 255 * 256 * 256;

        Juego juego = new Juego();

        int [][] prueba = juego.generarMatriz(5,5);
        int a = 0;
        juego.imprimir(prueba);
        a = juego.agregarFicha(prueba,1,10);
        a = juego.agregarFicha(prueba,1,10);
        a = juego.agregarFicha(prueba,1,10);
        a = juego.agregarFicha(prueba,1,10);
        a = juego.agregarFicha(prueba,1,10);
        juego.imprimir(prueba);
        a = juego.agregarFicha(prueba,1,10);
        //juego.agregarFicha(prueba,1,10);
        //juego.agregarFicha(prueba,1,10);

        juego.imprimir(prueba);
        System.out.println(a);



        //Interfaz i = new Interfaz(jugador1, jugador2);

    }
}
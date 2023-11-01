import java.io.*;

public class Ranking {
    private BufferedWriter escritor;
    private BufferedReader lector;
    private boolean estaAbierto;
    private String[] nombres = new String[10];
    private int[] puntos = new int[10];
    private static final String RUTA = "ranking.txt";
    private int numJugadores = 0;

    public Ranking() {
        try {
            lector = new BufferedReader(new FileReader(RUTA));
            escritor = new BufferedWriter(new FileWriter(RUTA, true));
            estaAbierto = true;
            cargarRanking();
        } catch (IOException e) {
            estaAbierto = false;
            System.err.println("Ocurrió un error al leer o escribir el archivo " + RUTA + ": " + e);
        }
    }

    public void cerrar() {
        try {
            lector.close();
            escritor.close();
            estaAbierto = false;
        } catch (IOException e) {
            System.err.println("Ocurrió un error al cerrar el archivo: " + e);
        }
    }

    public void agregarJugadorAlRanking(String nombre, int puntos) {
        if (numJugadores < 10) {
            nombres[numJugadores] = nombre;
            this.puntos[numJugadores] = puntos;
            numJugadores++;
            actualizarArchivo();
        }
    }

    private void cargarRanking() {
        if (estaAbierto) {
            try {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String nombre = partes[0];
                        int puntos = Integer.parseInt(partes[1]);
                        if (numJugadores < 10) {
                            nombres[numJugadores] = nombre;
                            this.puntos[numJugadores] = puntos;
                            numJugadores++;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Ocurrió un error al leer el archivo " + RUTA + ": " + e);
            }
        }
    }

    private void actualizarArchivo() {
        if (estaAbierto) {
            try {
                escritor = new BufferedWriter(new FileWriter(RUTA, false));
                ordenarRanking();
                for (int i = 0; i < numJugadores; i++) {
                    escritor.write(nombres[i] + ":" + puntos[i]);
                    escritor.newLine();
                }
                escritor.flush();
            } catch (IOException e) {
                System.err.println("Ocurrió un error al escribir en el archivo " + RUTA + ": " + e);
            }
        }
    }

    public String[] obtenerNombresRanking() {
        String[] nombresRanking = new String[numJugadores];
        System.arraycopy(nombres, 0, nombresRanking, 0, numJugadores);
        return nombresRanking;
    }

    public int[] obtenerPuntosRanking() {
        int[] puntosRanking = new int[numJugadores];
        System.arraycopy(puntos, 0, puntosRanking, 0, numJugadores);
        return puntosRanking;
    }

    public void ordenarRanking() {
        for (int i = 0; i < numJugadores; i++) {
            for (int j = i + 1; j < numJugadores; j++) {
                if (puntos[j] < puntos[i]) {
                    String nombreTemp = nombres[i];
                    int puntosTemp = puntos[i];
                    nombres[i] = nombres[j];
                    puntos[i] = puntos[j];
                    nombres[j] = nombreTemp;
                    puntos[j] = puntosTemp;
                }
            }
        }
    }

    public void actualizarRanking(String nombre, int puntos) {
        agregarJugadorAlRanking(nombre, puntos);
    }
}

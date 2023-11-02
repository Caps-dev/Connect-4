import java.io.*;
//! \class Clase Ranking del juego 4 en linea
/*!
  
  Clase encargada de guardar, acomodar y leer el ranking del juego
  Es la implementacion del ranking donde puede leer el archivo acomodarlo y guardar en nombre y la cantidad de turnos
*/

public class Ranking {
    private BufferedWriter escritor; /*!< escritor del archivo, permite escribir el archivo */
    private BufferedReader lector; /*!< lector permite leer el archivo y sacar la informacion*/
    private boolean estaAbierto; /*!< es un boolean que permite saber si el archivo esta correctamente abierto*/
    private String[] nombres = new String[10]; /*!< guarda los nombre de la lista de juego para despues acomodarlos*/
    private int[] puntos = new int[10]; /*!< guarda los "puntos" de cada jugador en el ranking para despues acomodarlos*/
    private static final String RUTA = "ranking.txt"; /*!< Ruta del archivo donde se va a guardar todo*/
    private int numJugadores = 0; /*!< cantidad de jugadores que hay en el ranking*/

    /*! \brief Constructor 
     *
     * Se utiliza para abrir el arhivo y comprobar si se abrio corectamente
     */

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

    /*! \brief Cerrar el archivo cuando ya no se esta utilizando
     *  cierra el archivo al, se utiliza hasta el final
     */

    public void cerrar() {
        try {
            lector.close();
            escritor.close();
            estaAbierto = false;
        } catch (IOException e) {
            System.err.println("Ocurrió un error al cerrar el archivo: " + e);
        }
    }

    /*! \brief Agrega un jugador nuevo al ranking 
     *  \param nombre del jugador a añadir
     *  \param cantidad de turnos utilizados para ganar
     *
     */

    public void agregarJugadorAlRanking(String nombre, int puntos) {
        if (numJugadores < 10) {
            nombres[numJugadores] = nombre;
            this.puntos[numJugadores] = puntos;
            numJugadores++;
            actualizarArchivo();
        }
    }


    /*! \brief El lector del ranking guardado añadiendolos a la lista correspondiente
     *
     */
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
    /*! \brief Reescribe el ranking despues de ordenarlo
     *
     */
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


    /*! \brief ordena el ranking de menor a mayor
     *
     */
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


     /*! \brief actualiza el ranking agregando al jugador
      * \param nombre del nuevo jugador
      * \param puntos del nuevo jugador
     *
     */
    

    public void actualizarRanking(String nombre, int puntos) {
        agregarJugadorAlRanking(nombre, puntos);
    }
     /*! \brief muestra el contenido del ranking
      *  \return contenido del archivo ranking 
     *
     */
    

    public String obtenerContenidoRanking() {
        String contenidos;
        if (estaAbierto) {
            StringBuilder contenido = new StringBuilder();
            for (int i = 0; i < numJugadores; i++) {
                contenido.append(nombres[i]).append(":").append(puntos[i]);
                contenido.append(System.lineSeparator());
            }
            contenidos = contenido.toString();
        } else {
            contenidos =  "No se pudo abrir el archivo de ranking.";
        }
        return contenidos;
    }
}

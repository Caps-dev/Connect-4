//! \class Clase Jugador de 4 en linea
/*!
  Clase encargada de representar un jugador.
  Puede almacenar, retornar y modificar el nombre del jugador, junto a su ID que le representa en el tablero internamente.
  Tambien puede almacenar el color escogido para la ficha.
*/
public class Jugador {
    String nombre; /*!< Numero que tendrá el jugador */
    int color; /*!< Color que tendrán las fichas del jugador */
    int id; /*!< Identificador interno de las fichas del jugador en el tablero */

    // Metodos de asignación
    /*! \brief Asigna un nombre al jugador
     *
     \param String Nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*! \brief Asigna un color al jugador
     *
     \param int representacion numerica del color de las fichas del jugador
     */
    public void setColor(int color) {
        this.color = color;
    }

    /*! \brief Asigna un id al jugador
     *
     \param int representacion numerica del jugador en el tablero.
     */
    public void setId(int id) {
        this.id = id;
    }


    // Metodos de retorno
    /*! \brief Retorna el valor del ID del jugador
     *
     \return int ID del jugador
     */
    public int getId() {
        return id;
    }

    /*! \brief Retorna el valor del ID del jugador
     *
     \return int ID del jugador
     */
    public String getNombre() {
        return nombre;
    }
}

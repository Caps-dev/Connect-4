
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.awt.event.*; //no hemos visto el uso de toda la libreria
import javax.swing.*;

public class Interfaz extends JFrame implements ActionListener { // intentemos usar la sintaxis que hemos visto
    Juego juego = new Juego();
    public static int ANCHO = 400;
    public static int ALTO = 500;
    private BufferedImage imagen;
    private int[][] dibujo = null;
    private int[] posicionesCirculos;
    Ranking ranking = new Ranking();
    // creo que esto podriamos cambiarlo a como lo vimos en clase
    JButton bMenu = new JButton("Jugar Cuatro en raya");
    JButton bRanking = new JButton("Ver ranking");
    JLabel lMenu = new JLabel("Menu");
    // hasta aca
    Jugador jugador1;
    Jugador jugador2;
    String tableroEscogido;
    // creo que hacen falta unos comments aqui
    int indicePosicionB1;
    int indicePosicionB2;
    int indicePosicionB3;
    int indicePosicionB4;
    int indicePosicionB5;
    int indicePosicionB6;
    int indicePosicionB7;
    int indicePosicionB8;
    int indicePosicionB9;
    Jugador actualJugador;
    int colorActual;
    int turno = 1; // valor inicial
    int id = 13;
    String nombreJugadorActual;

    public Interfaz(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;

        // cambiar esto por un menu corriente
        bMenu.setBounds(100, 170, 200, 40);
        bRanking.setBounds(100, 230, 200, 40);
        lMenu.setBounds(175, 10, 200, 40);
        bMenu.addActionListener(this);
        bRanking.addActionListener(this);
        add(lMenu);
        add(bMenu);
        add(bRanking);

        System.out.println(tableroEscogido);

        jugador1.setId(13);
        jugador2.setId(1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bMenu) {
            setVisible(false);
            String[] tableros = { "6x7", "7x8", "8x9" };

            tableroEscogido = String
                    .valueOf(JOptionPane.showInputDialog(null, "seleccione un tamanio de tablero", "Tamanio",
                            JOptionPane.QUESTION_MESSAGE, null, tableros, tableros[0]));

            if (tableroEscogido == "8x9") {
                ventanaJuego(920, 840, 9, 8);
            } else if (tableroEscogido == "7x8") {
                ventanaJuego(820, 760, 8, 7);
            } else { // poniendo el tablero 6x7 como default
                ventanaJuego(720, 680, 7, 6);
            }

        }
        if (e.getSource() == bRanking) {
            JOptionPane.showMessageDialog(null,
                    ranking.obtenerContenidoRanking(),
                    "Ranking",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void ventanaJuego(int w, int h, int x, int y) {
        juego.setCantidadDeTurnos(0);
        jugador1.setNombre(pedirNombre("jugador 1")); // valores default por si no hay escogencia
        jugador1.setColor(pedirColor(-16776961)); // solo busque el codigo de color del rojo
        jugador2.setNombre(pedirNombre("jugador 2")); // ibid
        jugador2.setColor(pedirColor(-65536)); // solo busque el codigo de color del azul
        imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        nombreJugadorActual = jugador1.getNombre();

        Matriz tablero = new Matriz(y, x);
        int posicion = 200;
        posicionesCirculos = new int[y];
        for (int i = 0; i < y; i++) {
            posicionesCirculos[i] = posicion;
            posicion += 81;
        }
        juego.setCantidadDeTurnos(0);
        turno = 1; // el error esta aqui
        indicePosicionB1 = posicionesCirculos.length - 1;
        indicePosicionB2 = posicionesCirculos.length - 1;
        indicePosicionB3 = posicionesCirculos.length - 1;
        indicePosicionB4 = posicionesCirculos.length - 1;
        indicePosicionB5 = posicionesCirculos.length - 1;
        indicePosicionB6 = posicionesCirculos.length - 1;
        indicePosicionB7 = posicionesCirculos.length - 1;
        indicePosicionB8 = posicionesCirculos.length - 1;
        indicePosicionB9 = posicionesCirculos.length - 1;

        dibujo = new int[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                dibujo[i][j] = 255 * 65536 + 255 * 256 + 255;
            }
        }

        for (int i = 0; i <= w - 20; i += (w / x) - 3) {
            dibujarLinea(i, i + 20, 150, h);
        }
        for (int i = 150; i <= h - 20; i += 81) {
            dibujarLinea(0, w, i, i + 20);
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                imagen.setRGB(i, j, dibujo[i][j]);
            } // for
        } // for

        JFrame frame = new JFrame("Juego");
        JLayeredPane layeredPane = new JLayeredPane();

        // Configura la imagen en una capa inferior
        JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
        imagenLabel.setBounds(0, 0, w, h);
        layeredPane.add(imagenLabel, JLayeredPane.DEFAULT_LAYER);
        // Crea un panel para los componentes
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, w, 60);
        JLabel turnosLabel = new JLabel("turno: " + juego.getCantidadDeTurnos(), JLabel.CENTER);

        // Agrega el JLabel al panel
        JLabel l1 = new JLabel(jugador1.getNombre(), JLabel.CENTER);
        JLabel l2 = new JLabel(jugador2.getNombre(), JLabel.CENTER);

        colorActual = jugador1.color;
        JLabel jugadorActual = new JLabel("Es turno de " + nombreJugadorActual, JLabel.CENTER);
        turnosLabel.setForeground(Color.BLACK);
        turnosLabel.setBounds(w / 2 - 80, 10, 150, 30);
        l1.setForeground(Color.getColor("j1", jugador1.color));
        l1.setBounds((int) 0, 0, 60, 30);
        l2.setForeground(Color.getColor("j2", jugador2.color));
        l2.setBounds(w - 100, 0, 60, 30);
        jugadorActual.setForeground(Color.getColor("ja", colorActual));
        jugadorActual.setBounds(w / 2 - 80, 0, 150, 30);
        panel.add(l2);
        panel.add(l1);
        panel.add(jugadorActual);
        panel.add(turnosLabel);

        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");

        b1.setBounds(29, 100, 60, 40);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 60, 0, turnosLabel, panel, layeredPane, imagenLabel,
                        indicePosicionB1);
                indicePosicionB1--;
            }
        });

        // Botón 2 (b2)
        b2.setBounds(129, 100, 60, 40);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 160, 1, turnosLabel, panel, layeredPane,
                        imagenLabel, indicePosicionB2);
                indicePosicionB2--;
            }
        });

        // Botón 3 (b3)
        b3.setBounds(229, 100, 60, 40);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 260, 2, turnosLabel, panel, layeredPane,
                        imagenLabel, indicePosicionB3);
                indicePosicionB3--;
            }
        });

        // Botón 4 (b4)
        b4.setBounds(329, 100, 60, 40);

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 360, 3, turnosLabel, panel, layeredPane,
                        imagenLabel, indicePosicionB4);
                indicePosicionB4--;

            }
        });
        b5.setBounds(429, 100, 60, 40);

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 460, 4, turnosLabel, panel, layeredPane,
                        imagenLabel, indicePosicionB5);
                indicePosicionB5--;

            }
        });
        b6.setBounds(529, 100, 60, 40);

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 560, 5, turnosLabel, panel, layeredPane,
                        imagenLabel, indicePosicionB6);
                indicePosicionB6--;

            }
        });

        b7.setBounds(629, 100, 60, 40);

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionarBoton(jugadorActual, frame, tablero, w, h, 660, 6, turnosLabel, panel, layeredPane,
                        imagenLabel, indicePosicionB7);
                indicePosicionB7--;

            }
        });

        if (w >= 820) {
            JButton b8 = new JButton("8");
            b8.setBounds(729, 100, 60, 40);

            b8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accionarBoton(jugadorActual, frame, tablero, w, h, 760, 7, turnosLabel, panel, layeredPane,
                            imagenLabel, indicePosicionB8);
                    indicePosicionB8--;

                }
            });

            frame.add(b8);

        }
        // habia bug aqui, its fixed now
        if (w >= 920) {

            JButton b9 = new JButton("9");
            b9.setBounds(829, 100, 60, 40);

            b9.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accionarBoton(jugadorActual, frame, tablero, w, h, 860, 8, turnosLabel, panel, layeredPane,
                            imagenLabel, indicePosicionB9);
                    indicePosicionB9--;

                }
            });
            frame.add(b9);

        }
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(b6);
        frame.add(b7);

        // Agrega el panel a una capa superior
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.revalidate();

    }

    private void dibujarLinea(int desdeX, int hastaX, int desdeY, int hastaY) {

        for (int i = desdeX; i < hastaX; i++) {
            for (int j = desdeY; j < hastaY; j++) {
                dibujo[i][j] = 1;
            }
        }
    }

    private void cambioJugador() {

        if (turno == 1) {
            colorActual = jugador2.color;
            id = 1;
            turno = 2;
            nombreJugadorActual = jugador2.nombre;
        } else {
            colorActual = jugador1.color;
            id = 13;
            turno = 1;
            nombreJugadorActual = jugador1.nombre;
        }

    }

    public String pedirNombre(String nombreDefault) {
        String nombre = JOptionPane.showInputDialog("digite su nombre");
        if (nombre == null || nombre.equals("")) {
            nombre = nombreDefault;
        }
        return nombre;
    }

    private int juegoBoton(int w, int h, Matriz tablero, int x, int columna, int indice, int color, int id) {
        juego.verificarGanador(tablero, jugador1.getId(), jugador2.getId());
        int status = tablero.agregarFicha(columna, id);

        if (status == -1) {
            // System.out.println("No puede agregar mas fichas");
            JOptionPane.showMessageDialog(null, "No puede agregar más ficha en esta columna", "Hey!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            tablero.imprimir();
            juego.SumarTurno();
            System.out.println("-------------");
            // Color c1 = new Color(158, 251, 179);
            if (indice < posicionesCirculos.length && indice >= 0) {
                dibujarCirculo(x, posicionesCirculos[indice], 20, color, true);
            }
            cambioJugador();
        }
        return juego.verificarGanador(tablero, jugador1.getId(), jugador2.getId());
    }

    private void dibujarCirculo(int x, int y, int radio, int color, boolean rellenos) {
        double x1, y1;
        for (double angulo = 0; angulo < 360; angulo += 1) {
            x1 = radio * Math.cos(angulo * Math.PI / 180);
            y1 = radio * Math.sin(angulo * Math.PI / 180);

            int posXFinal = (int) (x + x1);
            int posYFinal = (int) (y + y1);
            if (x + x1 < dibujo.length &&
                    y + y1 < dibujo[0].length &&
                    x + x1 >= 0 && y + y1 >= 0) {
                if (rellenos) {
                    dibujarLinea2(x, y, posXFinal, posYFinal, color);
                } else {
                    dibujo[posXFinal][posYFinal] = color;
                }

            }
        }
    }

    private void dibujarLinea2(int fI, int cI, int fF, int cF, int color) {
        int fInicialCopia = fI < fF ? fI : fF;
        fF = fI > fF ? fI : fF;
        fI = fInicialCopia;

        int cICopia = cI < cF ? cI : cF;
        cF = cI > cF ? cI : cF;
        cI = cICopia;

        for (int f = fI; f < fF; f++) {
            for (int c = cI; c < cF; c++) {
                dibujo[f][c] = color;
            }
        }

    }

    private void recargarCuadros(JFrame frame, JPanel panel, JLayeredPane layeredPane, JLabel imagenLabel, int w,
            int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                imagen.setRGB(i, j, dibujo[i][j]);
            } // for
        }
        imagenLabel.repaint();
        layeredPane.repaint();
        panel.repaint();
        frame.repaint();
    }

    private int pedirColor(int colorDefault) {
        Color color;
        int colorRGB = 0;
        JColorChooser Selectorcolor = new JColorChooser();
        try {
            color = Selectorcolor.showDialog(null, "Seleccione un Color", Color.BLUE);
            colorRGB = color.getRGB();
        } catch (Exception e) {
            colorRGB = colorDefault;
        }

        return colorRGB;
    }

    private void accionarBoton(JLabel jugadorActual, JFrame frame, Matriz tablero, int w, int h, int x, int columna,
            JLabel turnosLabel, JPanel panel, JLayeredPane layeredPane, JLabel imagenLabel, int indice) {
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon("images/celebracion.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        int gano = 0;
        gano = juegoBoton(w, h, tablero, x, columna, indice, colorActual, id);
        recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);
        if (gano == 2) {
            JOptionPane.showMessageDialog(null,
                    "El ganador fue: " + ((id != jugador1.getId()) ? jugador1.getNombre() : jugador2.getNombre()),
                    "Hey!",
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
            ranking.actualizarRanking((id != jugador1.getId()) ? jugador1.getNombre() : jugador2.getNombre(),
                    juego.getCantidadDeTurnos());
            ranking.ordenarRanking();
            frame.setVisible(false);
            setVisible(true);
            turno = 1; // nuevo
            return;
        }
        if (gano == 9) {
            JOptionPane.showMessageDialog(null,
                    "Hubo un empate al turno : " + juego.getCantidadDeTurnos(),
                    "Hey!",
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
            frame.setVisible(false);
            setVisible(true);
            turno = 1; // nuevo
            return;

        }

        jugadorActual.setForeground(Color.getColor("ja", colorActual));
        jugadorActual.setText("Turno de " + nombreJugadorActual);
        turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
        recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

    }
}
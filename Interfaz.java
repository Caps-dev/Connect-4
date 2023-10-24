
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Interfaz extends JFrame implements ActionListener {
    Juego juego = new Juego();
    public static int ANCHO = 400;
    public static int ALTO = 500;
    private BufferedImage imagen;
    private int[][] dibujo = null;
    private int[] posicionesCirculos;
    JButton bMenu = new JButton("Juegar Cuatro en raya");
    JLabel lMenu = new JLabel("Prueba de menu");
    Jugador jugador1;
    Jugador jugador2;
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
    int turno = 1;
    int id = 13;
    String nombreJugadorActual;

    public Interfaz(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        bMenu.setBounds(100, 170, 200, 40);
        lMenu.setBounds(150, 10, 200, 40);
        bMenu.addActionListener(this);
        add(lMenu);
        add(bMenu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bMenu) {
            setVisible(false);
            // ventanaJuego(720, 680, 7, 6);
            ventanaJuego(820, 760, 8, 7);
            // ventanaJuego(920, 840, 9, 8);
        }
    }

    public void ventanaJuego(int w, int h, int x, int y) {
        juego.setCantidadDeTurnos(0);
        jugador1.setNombre(pedirNombre());
        jugador1.setColor(pedirColor());
        jugador2.setNombre(pedirNombre());
        jugador2.setColor(pedirColor());
        imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        nombreJugadorActual = jugador1.getNombre();

        int[][] tablero = new int[y][x];
        int posicion = 200;
        posicionesCirculos = new int[y];
        for (int i = 0; i < y; i++) {
            posicionesCirculos[i] = posicion;
            posicion += 81;
        }
        juego.setCantidadDeTurnos(0);
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

                accionBoton(w, h, tablero, 60, y, 0, indicePosicionB1, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB1--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

            }
        });

        // Botón 2 (b2)
        b2.setBounds(129, 100, 60, 40);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(w, h, tablero, 160, y, 1, indicePosicionB2, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB2--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);
            }
        });

        // Botón 3 (b3)
        b3.setBounds(229, 100, 60, 40);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(w, h, tablero, 260, y, 2, indicePosicionB3, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB3--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);
            }
        });

        // Botón 4 (b4)
        b4.setBounds(329, 100, 60, 40);

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(w, h, tablero, 360, y, 3, indicePosicionB4, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB4--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

            }
        });
        b5.setBounds(429, 100, 60, 40);

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(w, h, tablero, 460, y, 4, indicePosicionB5, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB5--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

            }
        });
        b6.setBounds(529, 100, 60, 40);

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(w, h, tablero, 560, y, 5, indicePosicionB6, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB6--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

            }
        });

        b7.setBounds(629, 100, 60, 40);

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(w, h, tablero, 660, y, 6, indicePosicionB7, colorActual, id);
                jugadorActual.setForeground(Color.getColor("ja", colorActual));
                jugadorActual.setText("Turno de " + nombreJugadorActual);
                turnosLabel.setText("Turno: " + juego.getCantidadDeTurnos());
                indicePosicionB7--;
                recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

            }
        });

        if (w >= 820) {
            JButton b8 = new JButton("8");
            b8.setBounds(729, 100, 60, 40);

            b8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accionBoton(w, h, tablero, 760, y, 7, indicePosicionB8, colorActual, id);

                    indicePosicionB8--;
                    recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

                }
            });

            frame.add(b8);

        }
        if (w >= 920) {
            JButton b9 = new JButton("9");
            b9.setBounds(829, 100, 60, 40);

            b9.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accionBoton(w, h, tablero, 860, y, 8, indicePosicionB9, colorActual, id);
                    indicePosicionB9--;
                    recargarCuadros(frame, panel, layeredPane, imagenLabel, w, h);

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

    private void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Nueva línea para cada fila
        }
    }

    public String pedirNombre() {
        String nombre = JOptionPane.showInputDialog("digite su nombre");
        return nombre;
    }

    private void accionBoton(int w, int h, int[][] tablero, int x, int y, int columna, int indice, int color, int id) {
        juego.verificarEstado(tablero, id);
        int cantidad = 0;
        for (int i = tablero.length - 1; i >= 0; i--) {
            if (tablero[i][columna] == 0) {
                tablero[i][columna] = id;
                break;
            } else {
                cantidad++;
            }
        }
        if (cantidad >= y) {
            // System.out.println("No puede agregar mas fichas");
            JOptionPane.showMessageDialog(null, "No puede agregar más ficha en esta columna", "Hey!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            imprimirMatriz(tablero);
            juego.SumarTurno();
            System.out.println("-------------");
            // Color c1 = new Color(158, 251, 179);
            if (indice < posicionesCirculos.length && indice >= 0) {
                dibujarCirculo(x, posicionesCirculos[indice], 20, color, true);
            }
            cambioJugador();
        }
        juego.verificarEstado(tablero, id);

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

    private int pedirColor() {
        Color color;
        JColorChooser Selectorcolor = new JColorChooser();
        color = Selectorcolor.showDialog(null, "Seleccione un Color", Color.BLUE);
        return color.getRGB();
    }
}
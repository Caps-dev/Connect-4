import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Interfaz extends JFrame implements ActionListener {
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
            // ventanaJuego(720, 680, 7, 6, jugador1, jugador2);
            ventanaJuego(820, 760, 8, 7);
            // ventanaJuego(920, 840, 9, 8, jugador1, jugador2);
        }
    }

    public void ventanaJuego(int w, int h, int x, int y) {
        jugador1.setNombre(pedirNombre());
        jugador2.setNombre(pedirNombre());
        imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int[][] tablero = new int[y][x];
        int posicion = 200;
        posicionesCirculos = new int[y];
        for (int i = 0; i < y; i++) {
            posicionesCirculos[i] = posicion;
            posicion += 81;
        }
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

        for (int i = 0; i <= w - 20; i += (w / x) - 3) {
            dibujarLinea(i, i + 20, 150, h);
        }
        for (int i = 150; i <= h - 20; i += 81) {
            dibujarLinea(0, w, i, i + 20);
        }
        // dibujarCirculo(60, 200, 20, Color.RED);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                switch (dibujo[i][j]) {
                    case 0:
                        imagen.setRGB(i, j, Color.WHITE.getRGB());
                        break;
                    case 1:
                        imagen.setRGB(i, j, Color.BLACK.getRGB());
                        break;
                    case 2:
                        imagen.setRGB(i, j, Color.RED.getRGB());
                        break;
                    case 3:
                        imagen.setRGB(i, j, Color.BLUE.getRGB());
                        break;
                    case 4:
                        imagen.setRGB(i, j, Color.YELLOW.getRGB());
                        break;
                    default:
                        imagen.setRGB(i, j, Color.GREEN.getRGB());
                } // swithc
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

        // Agrega el JLabel al panel
        JLabel l1 = new JLabel(jugador1.getNombre(), JLabel.CENTER);
        JLabel l2 = new JLabel(jugador2.getNombre(), JLabel.CENTER);
        l1.setForeground(Color.RED);
        l1.setBounds((int) 0, 0, 60, 30);
        l2.setForeground(Color.BLUE);
        l2.setBounds(w - 100, 0, 60, 30);
        panel.add(l2);
        panel.add(l1);
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

                accionBoton(tablero, 60, y, 0, frame, indicePosicionB1);
                indicePosicionB1--;

            }
        });

        // Botón 2 (b2)
        b2.setBounds(129, 100, 60, 40);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(tablero, 160, y, 1, frame, indicePosicionB2);
                indicePosicionB2--;
            }
        });

        // Botón 3 (b3)
        b3.setBounds(229, 100, 60, 40);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(tablero, 260, y, 2, frame, indicePosicionB3);
                indicePosicionB3--;
            }
        });

        // Botón 4 (b4)
        b4.setBounds(329, 100, 60, 40);

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(tablero, 360, y, 3, frame, indicePosicionB4);
                indicePosicionB4--;
            }
        });
        b5.setBounds(429, 100, 60, 40);

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(tablero, 460, y, 4, frame, indicePosicionB5);
                indicePosicionB5--;
            }
        });
        b6.setBounds(529, 100, 60, 40);

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(tablero, 560, y, 5, frame, indicePosicionB6);
                indicePosicionB6--;
            }
        });

        b7.setBounds(629, 100, 60, 40);

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBoton(tablero, 660, y, 6, frame, indicePosicionB7);
                indicePosicionB7--;
            }
        });

        if (w >= 820) {
            JButton b8 = new JButton("8");
            b8.setBounds(729, 100, 60, 40);

            b8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accionBoton(tablero, 760, y, 7, frame, indicePosicionB8);
                    indicePosicionB8--;
                }
            });

            frame.add(b8);

        }
        if (w >= 920) {
            JButton b9 = new JButton("9");
            b9.setBounds(829, 100, 60, 40);

            b9.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accionBoton(tablero, 860, y, 8, frame, indicePosicionB9);
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

    public void dibujarLinea(int desdeX, int hastaX, int desdeY, int hastaY) {

        for (int i = desdeX; i < hastaX; i++) {
            for (int j = desdeY; j < hastaY; j++) {
                dibujo[i][j] = 1;
            }
        }
    }

    public void dibujarCirculo(int x, int y, int radio, Color color) {
        if (imagen != null) {
            Graphics g = imagen.getGraphics();
            g.setColor(color);
            g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
            repaint();
        }
    }

    public void imprimirMatriz(int[][] matriz) {
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

    private void accionBoton(int[][] tablero, int x, int y, int columna, JFrame frame, int indice) {
        for (int i = y - 1; i >= 0; i--) {
            if (tablero[i][columna] == 0) {
                tablero[i][columna] = 1;
                break;
            }
        }

        imprimirMatriz(tablero);
        System.out.println("-------------");
        Color c1 = new Color(158, 251, 179);
        if (indice < posicionesCirculos.length) {
            dibujarCirculo(x, posicionesCirculos[indice], 20, c1);

        }

        frame.repaint();
    }
}
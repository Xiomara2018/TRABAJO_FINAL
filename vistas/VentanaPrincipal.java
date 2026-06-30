package vistas;

import controladores.GestorBiblioteca;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaPrincipal extends JFrame {
    
    private GestorBiblioteca gestor;

    public VentanaPrincipal() {
        gestor = new GestorBiblioteca();
        setTitle("QuickLibrary - Sistema de Gestión");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN QUICKLIBRARY", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0)); 
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(7, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btn1 = new JButton("1. Registrar libro");
        JButton btn2 = new JButton("2. Mostrar libros");
        JButton btn3 = new JButton("3. Buscar libro por código");
        JButton btn4 = new JButton("4. Buscar libros por categoría");
        JButton btn5 = new JButton("5. Modificar libro");
        JButton btn6 = new JButton("6. Eliminar libro");
        JButton btn7 = new JButton("7. Registrar solicitud de préstamo");
        JButton btn8 = new JButton("8. Mostrar cola de solicitudes");
        JButton btn9 = new JButton("9. Atender siguiente solicitud");
        JButton btn10 = new JButton("10. Registrar devolución");
        JButton btn11 = new JButton("11. Mostrar reporte");
        JButton btn12 = new JButton("12. Salir");
        JButton btn13 = new JButton("13. Cargar desde CSV");
        JButton btn14 = new JButton(""); // Relleno invisible para mantener la cuadrícula
        btn14.setVisible(false);

        panelBotones.add(btn1);
        panelBotones.add(btn7);
        panelBotones.add(btn2);
        panelBotones.add(btn8);
        panelBotones.add(btn3);
        panelBotones.add(btn9);
        panelBotones.add(btn4);
        panelBotones.add(btn10);
        panelBotones.add(btn5);
        panelBotones.add(btn11);
        panelBotones.add(btn6);
        panelBotones.add(btn12);
        panelBotones.add(btn13);
        panelBotones.add(btn14);

        add(panelBotones, BorderLayout.CENTER);



        btn1.addActionListener(e -> {
            DialogoRegistrarLibro dialogo = new DialogoRegistrarLibro(this, gestor);
            dialogo.setVisible(true);
        });
        
        btn2.addActionListener(e -> {
            DialogoMostrarLibros dialogo = new DialogoMostrarLibros(this, gestor);
            dialogo.setVisible(true);
        });

        btn3.addActionListener(e -> {
            DialogoBuscarLibro dialogo = new DialogoBuscarLibro(this, gestor);
            dialogo.setVisible(true);
        });

     btn4.addActionListener(e -> {
        String categoria = JOptionPane.showInputDialog(this, 
        "Ingrese la categoría que desea buscar:", 
        "Buscar por Categoría", 
        JOptionPane.QUESTION_MESSAGE);

        if (categoria != null && !categoria.trim().isEmpty()) {
            try {
                java.util.List<modelos.Libro> encontrados = gestor.buscarPorCategoria(categoria.trim());
                    
                if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                 "No se encontraron libros en la categoría: " + categoria, 
                 "Sin resultados", 
                JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder textoResultados = new StringBuilder();
                    textoResultados.append("Libros encontrados en la categoría '").append(categoria).append("':\n\n");
                        
                    for (modelos.Libro libro : encontrados) {
                    textoResultados.append("- [").append(libro.getCodigo()).append("] ")
                    .append(libro.getTitulo()).append(" (")
                    .append(libro.getEstado().toString()).append(")\n");
                        }
                        
                    JTextArea areaTexto = new JTextArea(textoResultados.toString());
                    areaTexto.setEditable(false);
                    JScrollPane scroll = new JScrollPane(areaTexto);
                    scroll.setPreferredSize(new java.awt.Dimension(350, 200));
                        
                    JOptionPane.showMessageDialog(this, scroll, "Resultados de Búsqueda", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (estructuras.ExceptionIsEmpty ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btn5.addActionListener(e -> {
            DialogoModificarLibro dialogo = new DialogoModificarLibro(this, gestor);
            dialogo.setVisible(true);
        });

        btn6.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, 
                "Ingrese el código del libro a eliminar:", 
                "Eliminar Libro", 
                JOptionPane.WARNING_MESSAGE);
                        
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int codigo = Integer.parseInt(input.trim());
                    gestor.eliminarLibro(codigo);
                    JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (estructuras.ItemNotfound ex) {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún libro con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (estructuras.ExceptionIsEmpty ex) {
                    JOptionPane.showMessageDialog(this, "Error interno con los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn7.addActionListener(e -> {
            DialogoRegistrarSolicitud dialogo = new DialogoRegistrarSolicitud(this, gestor);
            dialogo.setVisible(true);
        });
        
        btn8.addActionListener(e -> {
            String textoCola = gestor.getColaSolicitudes().mostrar();
            JOptionPane.showMessageDialog(this, textoCola, "Cola de Espera", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btn9.addActionListener(e -> {
            String resultado = gestor.procesarPrestamo();
            JOptionPane.showMessageDialog(this, resultado, "Atención de Solicitud", JOptionPane.INFORMATION_MESSAGE);
        });

        btn10.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, 
                "Ingrese el código del libro que desea devolver:", 
                "Registrar Devolución", 
                JOptionPane.QUESTION_MESSAGE);
                                        
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int codigo = Integer.parseInt(input.trim());
                    String mensajeDevolucion = gestor.procesarDevolucion(codigo);
                    JOptionPane.showMessageDialog(this, mensajeDevolucion, "Resultado de Devolución", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn11.addActionListener(e -> {
            String textoReporte = gestor.mostrarReporteBasico();
            JOptionPane.showMessageDialog(this, textoReporte, "Reporte General", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btn12.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        btn13.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar archivo CSV de Libros");
            // Filtro para que el usuario solo vea archivos .csv
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));
            
            int seleccion = fileChooser.showOpenDialog(this);
            
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                String resultado = gestor.cargarLibrosDesdeCSV(ruta);
                
                JOptionPane.showMessageDialog(this, resultado, "Resultado de Carga Masiva", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
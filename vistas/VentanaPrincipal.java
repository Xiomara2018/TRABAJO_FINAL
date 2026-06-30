package vistas;

import controladores.GestorBiblioteca;
import javax.swing.*;
import java.awt.*;
public class VentanaPrincipal extends JFrame
 {private GestorBiblioteca gestor;

public VentanaPrincipal() {
    gestor = new GestorBiblioteca();
    setTitle("QuickLibrary - Sistema de Gestión");
    setSize(600, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 10));

    JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN QUICKLIBRARY", SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0)); // Márgenes
    add(lblTitulo, BorderLayout.NORTH);

    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new GridLayout(6, 2, 10, 10));
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


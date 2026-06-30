package vistas;

import controladores.GestorBiblioteca;
import estructuras.ExceptionIsEmpty;
import javax.swing.*;
import java.awt.*;

public class DialogoRegistrarSolicitud extends JDialog {

    private JTextField txtCodigoEstudiante;
    private JTextField txtNombreEstudiante;
    private JTextField txtCodigoLibro;
    private JTextField txtFecha;

    public DialogoRegistrarSolicitud(JFrame ventanaPadre, GestorBiblioteca gestor) {
        super(ventanaPadre, "Registrar Solicitud de Préstamo", true);
        setSize(400, 300);
        setLocationRelativeTo(ventanaPadre);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtCodigoEstudiante = new JTextField();
        txtNombreEstudiante = new JTextField();
        txtCodigoLibro = new JTextField();
        txtFecha = new JTextField(); 

        panelFormulario.add(new JLabel("Código del Estudiante:"));
        panelFormulario.add(txtCodigoEstudiante);
        
        panelFormulario.add(new JLabel("Nombre del Estudiante:"));
        panelFormulario.add(txtNombreEstudiante);
        
        panelFormulario.add(new JLabel("Código del Libro:"));
        panelFormulario.add(txtCodigoLibro);
        
        panelFormulario.add(new JLabel("Fecha (ej. 15/10/2026):"));
        panelFormulario.add(txtFecha);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnRegistrar = new JButton("Poner en Cola");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> dispose());

        btnRegistrar.addActionListener(e -> {
            try {

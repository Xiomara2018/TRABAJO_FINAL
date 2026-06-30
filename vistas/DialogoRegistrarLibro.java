package vistas;
import controladores.GestorBiblioteca;
import estructuras.ExceptionIsEmpty;
import estructuras.ItemDuplicated;
import javax.swing.*;
import java.awt.*;

public class DialogoRegistrarLibro extends JDialog {
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCategoria;
    private JTextField txtAnio;
    public DialogoRegistrarLibro(JFrame ventanaPadre, GestorBiblioteca gestor) {
        super(ventanaPadre, "Registrar Nuevo Libro", true);
        setSize(350, 300);
        setLocationRelativeTo(ventanaPadre); // Centra este cuadro sobre la ventana principal
        setLayout(new BorderLayout(10, 10));
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        txtCodigo = new JTextField();
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtCategoria = new JTextField();
        txtAnio = new JTextField();
        panelFormulario.add(new JLabel("Código (Número):"));
        panelFormulario.add(txtCodigo);
        
        panelFormulario.add(new JLabel("Título:"));
        panelFormulario.add(txtTitulo);
        
        panelFormulario.add(new JLabel("Autor:"));
        panelFormulario.add(txtAutor);
        
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(txtCategoria);
        
        panelFormulario.add(new JLabel("Año de Publicación:"));
        panelFormulario.add(txtAnio);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar Libro");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);
}

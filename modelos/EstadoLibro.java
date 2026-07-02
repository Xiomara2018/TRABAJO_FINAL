package modelos;

public class EstadoLibro {

    public static final EstadoLibro DISPONIBLE = new EstadoLibro("Disponible");
    public static final EstadoLibro PRESTADO = new EstadoLibro("Prestado");

    private final String estado;

    // El constructor es privado para que nadie pueda hacer un 'new EstadoLibro("Robado")'
    // Solo existen los dos estados definidos arriba.
    private EstadoLibro(String estado) {
        this.estado = estado;
    }

    // Este método devuelve el texto para cuando lo necesites en tu interfaz gráfica
    @Override
    public String toString() {
        return this.estado;
    }

    // Compara si dos estados son exactamente el mismo objeto
    public static boolean esIgual(EstadoLibro estado1, EstadoLibro estado2) {
        if (estado1 == null || estado2 == null) {
            return false;
        }
        return estado1 == estado2;
    }
}

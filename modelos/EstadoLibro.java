package modelos;

public class EstadoLibro {

    public static final String DISPONIBLE = "Disponible";
    public static final String PRESTADO = "Prestado";

    // Verifica si el estado es válido
    public static boolean esValido(String estado) {
        return normalizar(estado) != null;
    }

    // Compara dos estados mayúsculas o espacios
    public static boolean esIgual(String estado1, String estado2) {
        String e1 = normalizar(estado1);
        String e2 = normalizar(estado2);

        if (e1 == null || e2 == null) {
            return false;
        }

        return e1.equals(e2);
    }

    // Devuelve con el formato correcto
    public static String normalizar(String estado) {
        if (estado == null) {
            return null;
        }

        estado = estado.trim();

        if (estado.equalsIgnoreCase(DISPONIBLE)) {
            return DISPONIBLE;
        }

        if (estado.equalsIgnoreCase(PRESTADO)) {
            return PRESTADO;
        }

        return null;
    }

    private EstadoLibro() {
    }
}

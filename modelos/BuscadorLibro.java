package ...;

import java.util.ArrayList;
import java.util.List;
import modelos.libros;

public class BuscadorLibros {

    public List<Libro> buscarPorTitulo(List<Libro> listaLibros, String tituloBuscado) {
        List<Libro> resultado = new ArrayList<>();

        if (listaLibros == null) {
            return resultado;
        }
        if (tituloBuscado == null) {
            return resultado;
        }
        if (tituloBuscado.trim().isEmpty()) {
            return resultado;
        }

        tituloBuscado = tituloBuscado.trim().toLowerCase();

        for (Libro libro : listaLibros) {
            if (libro.getTitulo().toLowerCase().contains(tituloBuscado)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

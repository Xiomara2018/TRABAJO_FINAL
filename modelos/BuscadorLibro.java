package ...;

import java.util.ArrayList;
import java.util.List;
import modelos.Libro;

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

        String textoBuscado = tituloBuscado.trim().toLowerCase();

        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libroActual = listaLibros.get(i);
            String tituloActual = libroActual.getTitulo().toLowerCase();

            if (tituloActual.contains(textoBuscado)) {
                resultado.add(libroActual);
            }
        }

        return resultado;
    }

    public List<Libro> buscarPorAutor(List<Libro> listaLibros, String autorBuscado) {
        List<Libro> resultado = new ArrayList<>();

        if (listaLibros == null) {
            return resultado;
        }

        if (autorBuscado == null) {
            return resultado;
        }

        if (autorBuscado.trim().isEmpty()) {
            return resultado;
        }

        String textoBuscado = autorBuscado.trim().toLowerCase();

        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libroActual = listaLibros.get(i);
            String autorActual = libroActual.getAutor().toLowerCase();

            if (autorActual.contains(textoBuscado)) {
                resultado.add(libroActual);
            }
        }

        return resultado;
    }

    public List<Libro> buscarPorCategoria(List<Libro> listaLibros, String categoriaBuscada) {
        List<Libro> resultado = new ArrayList<>();

        if (listaLibros == null) {
            return resultado;
        }

        if (categoriaBuscada == null) {
            return resultado;
        }

        if (categoriaBuscada.trim().isEmpty()) {
            return resultado;
        }

        String textoBuscado = categoriaBuscada.trim().toLowerCase();

        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libroActual = listaLibros.get(i);
            String categoriaActual = libroActual.getCategoria().toLowerCase();

            if (categoriaActual.equals(textoBuscado)) {
                resultado.add(libroActual);
            }
        }

        return resultado;
    }
}

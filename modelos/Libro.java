package modelos;

import estructuras.ExceptionIsEmpty;

public class Libro implements Comparable<Libro> {

    private int codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private String estado;

    public Libro(int codigo, String titulo, String autor, String categoria, int anio, String estado)
            throws ExceptionIsEmpty {

        if (titulo == null) {
            throw new ExceptionIsEmpty("El titulo no puede estar vacio");
        }
        if (titulo.trim().isEmpty()) {
            throw new ExceptionIsEmpty("El titulo no puede estar vacio");
        }
        if (autor == null) {
            throw new ExceptionIsEmpty("El autor no puede estar vacio");
        }
        if (autor.trim().isEmpty()) {
            throw new ExceptionIsEmpty("El autor no puede estar vacio");
        }
        if (categoria == null) {
            throw new ExceptionIsEmpty("La categoria no puede estar vacia");
        }
        if (categoria.trim().isEmpty()) {
            throw new ExceptionIsEmpty("La categoria no puede estar vacia");
        }
        if (estado == null) {
            throw new ExceptionIsEmpty("El estado no puede estar vacio");
        }
        if (estado.trim().isEmpty()) {
            throw new ExceptionIsEmpty("El estado no puede estar vacio");
        }
        
        if (!EstadoLibro.esValido(estado)) {
            throw new ExceptionIsEmpty("El estado debe ser 'Disponible' o 'Prestado'");
        }

        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnio() {
        return anio;
    }

    public String getEstado() {
        return estado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setEstado(String estado) {
        // no quede en un valor invalido despues de crear el libro.
        if (EstadoLibro.esValido(estado)) {
            this.estado = estado;
        }
    }

    @Override
    public int compareTo(Libro otro) {
        return this.codigo - otro.codigo;
    }

    @Override
    public String toString() {
        return codigo + " - " + titulo + " - " + autor + " - " + categoria + " - " + anio + " - " + estado;
    }
}

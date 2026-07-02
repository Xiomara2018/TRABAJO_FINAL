package modelos;

import estructuras.ExceptionIsEmpty;

public class Libro implements Comparable<Libro> {

    private int codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private EstadoLibro estado;

    public Libro(int codigo, String titulo, String autor, String categoria, int anio, EstadoLibro estado)
            throws ExceptionIsEmpty {

        if (titulo == null) {
            throw new ExceptionIsEmpty("El titulo no puede estar vacio");
        }

        if (autor == null) {
            throw new ExceptionIsEmpty("El autor no puede estar vacio");
        }

        if (categoria == null) {
            throw new ExceptionIsEmpty("La categoria no puede estar vacia");
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

    public EstadoLibro getEstado() {
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

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
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

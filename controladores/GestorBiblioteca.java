package controladores;

import estructuras.Cola;
import estructuras.AVLTree;
import estructuras.ItemNotfound;
import estructuras.ItemDuplicated;
import estructuras.ExceptionIsEmpty;
import modelos.Libro;
import modelos.Solicitud;
import modelos.EstadoLibro;
import modelos.BuscadorLibros;
import java.util.ArrayList;
import java.util.List;


public class GestorBiblioteca {

    private Cola<Solicitud> colaSolicitudes;
    private AVLTree<Libro> arbolLibros;
    private BuscadorLibros buscador;

    public GestorBiblioteca() {
        this.colaSolicitudes = new Cola<>();
        this.arbolLibros = new AVLTree<>();
        this.buscador = new BuscadorLibros();
    }

    public Cola<Solicitud> getColaSolicitudes() {
        return colaSolicitudes;
    }

    public AVLTree<Libro> getArbolLibros() {
        return arbolLibros;
    }
    public void registrarLibro(int codigo, String titulo, String autor, String categoria, int anio, String estado)
        throws ExceptionIsEmpty, ItemDuplicated {
            Libro libro = new Libro(codigo, titulo, autor, categoria, anio, estado);
        arbolLibros.insert(libro);
    }  
    
    public List<Libro> mostrarTodosLosLibros() {
        return arbolLibros.obtenerListaLibros();
    }

    public Libro buscarPorCodigo(int codigo) throws ItemNotfound, ExceptionIsEmpty {
        Libro libroTemporal = new Libro(codigo, "Temp", "Temp", "Temp", 0, EstadoLibro.DISPONIBLE);
        return arbolLibros.search(libroTemporal);
    }
    public void modificarLibro(int codigo, String nuevoTitulo, String nuevoAutor, String nuevaCategoria, int nuevoAnio)
            throws ItemNotfound, ExceptionIsEmpty {
        Libro libro = buscarPorCodigo(codigo);
        if (nuevoTitulo != null && !nuevoTitulo.trim().isEmpty()) {
            libro.setTitulo(nuevoTitulo);
        }
        if (nuevoAutor != null && !nuevoAutor.trim().isEmpty()) {
            libro.setAutor(nuevoAutor);
        }
        if (nuevaCategoria != null && !nuevaCategoria.trim().isEmpty()) {
            libro.setCategoria(nuevaCategoria);
        }
        if (nuevoAnio > 0) {
            libro.setAnio(nuevoAnio);
        }
        public void eliminarLibro(int codigo) throws ItemNotfound, ExceptionIsEmpty {
            Libro libro = buscarPorCodigo(codigo);
            arbolLibros.delete(libro);
    }
        public List<Libro> mostrarLibrosDisponibles() {
        List<Libro> resultado = new ArrayList<>();
        for (Libro libro : mostrarTodosLosLibros()) {
            if (libro.getEstado().equals(EstadoLibro.DISPONIBLE)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }
    public List<Libro> mostrarLibrosPrestados() {
        List<Libro> resultado = new ArrayList<>();
        for (Libro libro : mostrarTodosLosLibros()) {
            if (libro.getEstado().equals(EstadoLibro.PRESTADO)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }
    public List<Libro> buscarPorTitulo(String titulo) {
        return buscador.buscarPorTitulo(mostrarTodosLosLibros(), titulo);
    }
    public List<Libro> buscarPorAutor(String autor) {
        return buscador.buscarPorAutor(mostrarTodosLosLibros(), autor);
    }
    public List<Libro> buscarPorCategoria(String categoria) {
        return buscador.buscarPorCategoria(mostrarTodosLosLibros(), categoria);
    }



    public String procesarPrestamo() {
        if (colaSolicitudes.IsEmpty()) {
            return "No hay solicitudes pendientes en la cola.";
        }

        Solicitud solicitudActual = null; 
        
        try {
            solicitudActual = colaSolicitudes.dequeue();
            
            int idLibro = Integer.parseInt(solicitudActual.getcode_libro());
            Libro libroBuscado = new Libro(idLibro, "Temp", "Temp", "Temp", 0, EstadoLibro.DISPONIBLE);
            Libro libroEncontrado = arbolLibros.search(libroBuscado);

            if (libroEncontrado.getEstado().equals(EstadoLibro.DISPONIBLE)) {
                libroEncontrado.setEstado(EstadoLibro.PRESTADO);
                return "Préstamo exitoso: El libro '" + libroEncontrado.getTitulo() + "' ha sido prestado a " + solicitudActual.getname_est();
            } else {
                return "El libro existe, pero actualmente no está disponible.";
            }

        } catch (NumberFormatException e) {
            return "Error: El código de libro en la solicitud tiene un formato incorrecto.";
        } catch (ExceptionIsEmpty e) {
            return "Error al procesar: " + e.getMessage();
        } catch (ItemNotfound e) {
            return "Error: El libro con código " + solicitudActual.getcode_libro() + " no está registrado.";
        }
    }

    public String procesarDevolucion(int codigoLibro) {
        try {
            Libro libroBuscado = new Libro(codigoLibro, "Temp", "Temp", "Temp", 0, EstadoLibro.PRESTADO);
            Libro libroEncontrado = arbolLibros.search(libroBuscado);

            if (libroEncontrado.getEstado().equals(EstadoLibro.PRESTADO)) {
                libroEncontrado.setEstado(EstadoLibro.DISPONIBLE);
                return "Devolución exitosa. El libro '" + libroEncontrado.getTitulo() + "' ahora está disponible.";
            } else {
                return "El libro ya se encuentra disponible. No se puede devolver.";
            }

        } catch (ExceptionIsEmpty e) {
            return "Error interno creando referencia de libro: " + e.getMessage();
        } catch (ItemNotfound e) {
            return "Error: No se encontró ningún libro con el código " + codigoLibro;
        }
    }

    public String mostrarReporteBasico() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n=== REPORTE BÁSICO DE BIBLIOTECA ===\n");
        reporte.append("Solicitudes pendientes en cola: ").append(colaSolicitudes.size()).append("\n");

        List<Libro> todosLosLibros = arbolLibros.obtenerListaLibros(); 
        int totales = 0, disponibles = 0, prestados = 0;
        
        if (todosLosLibros != null) {
            totales = todosLosLibros.size();
            for (Libro libro : todosLosLibros) {
                if (libro.getEstado().equals(EstadoLibro.DISPONIBLE)) {
                    disponibles++;
                } else {
                    prestados++; 
                }
            }
        }
        
        reporte.append("Total de libros registrados: ").append(totales).append("\n");
        reporte.append("Libros disponibles: ").append(disponibles).append("\n");
        reporte.append("Libros prestados: ").append(prestados).append("\n");
        reporte.append("====================================\n");

        return reporte.toString();
    }
}

package controladores;

import estructuras.AVLTree;
import estructuras.Cola;
import estructuras.ExceptionIsEmpty;
import estructuras.ItemDuplicated;
import estructuras.ItemNotfound;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import modelos.BuscadorLibros;
import modelos.EstadoLibro;
import modelos.Libro;
import modelos.Solicitud;

public class GestorBiblioteca {

    private final Cola<Solicitud> colaSolicitudes;
    private final AVLTree<Libro> arbolLibros;
    private final BuscadorLibros buscador;

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

    private void validarCodigo(int codigo) throws ExceptionIsEmpty {
        if (codigo <= 0) {
            throw new ExceptionIsEmpty("El código debe ser un número entero mayor a cero.");
        }
    }

    private void validarAnio(int anio) throws ExceptionIsEmpty {

        if (anio <= 0 || anio > 2026) {
            throw new ExceptionIsEmpty("El año debe ser un valor válido (mayor a 0 y no en el futuro).");
        }
    }

    private void validarTexto(String texto, String nombreCampo) throws ExceptionIsEmpty {
        if (texto == null || texto.trim().isEmpty()) {
            throw new ExceptionIsEmpty("El campo '" + nombreCampo + "' no puede estar vacío.");
        }
    }

    public void registrarLibro(int codigo, String titulo, String autor, String categoria, int anio, String estado)
            throws ExceptionIsEmpty, ItemDuplicated {
        
        validarCodigo(codigo);
        validarTexto(titulo, "Título");
        validarTexto(autor, "Autor");
        validarTexto(categoria, "Categoría");
        validarAnio(anio);

        Libro libro = new Libro(codigo, titulo, autor, categoria, anio, EstadoLibro.DISPONIBLE);
        arbolLibros.insert(libro);

        actualizarCSVCompleto();
    }  
    
    public List<Libro> mostrarTodosLosLibros() {
        return arbolLibros.obtenerListaLibros();
    }

    public Libro buscarPorCodigo(int codigo) throws ItemNotfound, ExceptionIsEmpty {
        validarCodigo(codigo); // Validación añadida
        Libro libroTemporal = new Libro(codigo, "Temp", "Temp", "Temp", 0, EstadoLibro.DISPONIBLE);
        return arbolLibros.search(libroTemporal);
    }

    public void modificarLibro(int codigo, String nuevoTitulo, String nuevoAutor, String nuevaCategoria, int nuevoAnio)
            throws ItemNotfound, ExceptionIsEmpty {
        
        validarCodigo(codigo);
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
            validarAnio(nuevoAnio); // Validamos solo si intentan actualizarlo
            libro.setAnio(nuevoAnio);
        }
    }

    public void eliminarLibro(int codigo) throws ItemNotfound, ExceptionIsEmpty {
        validarCodigo(codigo);
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

    public List<Libro> buscarPorTitulo(String titulo) throws ExceptionIsEmpty {
        validarTexto(titulo, "Buscador Título");
        return buscador.buscarPorTitulo(mostrarTodosLosLibros(), titulo);
    }

    public List<Libro> buscarPorAutor(String autor) throws ExceptionIsEmpty {
        validarTexto(autor, "Buscador Autor");
        return buscador.buscarPorAutor(mostrarTodosLosLibros(), autor);
    }

    public List<Libro> buscarPorCategoria(String categoria) throws ExceptionIsEmpty {
        validarTexto(categoria, "Buscador Categoría");
        return buscador.buscarPorCategoria(mostrarTodosLosLibros(), categoria);
    }

    public void registrarSolicitud(String codigoEstudiante, String nombreEstudiante, String codigoLibro, String fecha) throws ExceptionIsEmpty {
        validarTexto(codigoEstudiante, "Código Estudiante");
        validarTexto(nombreEstudiante, "Nombre Estudiante");
        validarTexto(codigoLibro, "Código de Libro");
        validarTexto(fecha, "Fecha");

        try {
            int codigoNum = Integer.parseInt(codigoLibro);
            validarCodigo(codigoNum);
        } catch (NumberFormatException e) {
            throw new ExceptionIsEmpty("El código del libro en la solicitud debe ser un número entero válido.");
        }

        Solicitud solicitud = new Solicitud(codigoEstudiante, nombreEstudiante, codigoLibro, fecha);
        colaSolicitudes.enqueue(solicitud);
    }

    public String mostrarColaSolicitudes() {
        return colaSolicitudes.mostrar();
    }

    public Solicitud consultarSiguienteSolicitud() throws ExceptionIsEmpty {
        if (colaSolicitudes.IsEmpty()) {
            return null;
        }
        return colaSolicitudes.peek();
    }

    public String procesarPrestamo() {
        if (colaSolicitudes.IsEmpty()) {
            return "No hay solicitudes pendientes en la cola.";
        }
        
        try {
            Solicitud solicitudActual = colaSolicitudes.dequeue();
            
            int idLibro = Integer.parseInt(solicitudActual.getcode_libro());
            Libro libroBuscado = new Libro(idLibro, "Temp", "Temp", "Temp", 0, EstadoLibro.DISPONIBLE);
            Libro libroEncontrado = arbolLibros.search(libroBuscado);

            if (libroEncontrado.getEstado().equals(EstadoLibro.DISPONIBLE)) {
                libroEncontrado.setEstado(EstadoLibro.PRESTADO);
                return "Préstamo exitoso: El libro '" + libroEncontrado.getTitulo() + "' ha sido prestado a " + solicitudActual.getname_est();
            } else {
                return "Error: El libro existe, pero actualmente se encuentra prestado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El código de libro en la solicitud tiene un formato incorrecto."; 
        } catch (ExceptionIsEmpty e) {
            return "Error al procesar los datos: " + e.getMessage();
        } catch (ItemNotfound e) {
            return "Error: El libro solicitado no se encuentra registrado en el sistema.";
        }
    }

    public String procesarDevolucion(int codigoLibro) {
        try {
            validarCodigo(codigoLibro);
            
            Libro libroBuscado = new Libro(codigoLibro, "Temp", "Temp", "Temp", 0, EstadoLibro.PRESTADO);
            Libro libroEncontrado = arbolLibros.search(libroBuscado);

            if (libroEncontrado.getEstado().equals(EstadoLibro.PRESTADO)) {
                libroEncontrado.setEstado(EstadoLibro.DISPONIBLE);
                return "Devolución exitosa. El libro '" + libroEncontrado.getTitulo() + "' ahora está disponible.";
            } else {
                return "Aviso: El libro ya se encontraba disponible. No se requiere devolución.";
            }

        } catch (ExceptionIsEmpty e) {
            return "Error de validación: " + e.getMessage();
        } catch (ItemNotfound e) {
            return "Error: No se encontró ningún libro registrado con el código " + codigoLibro;
        }
    }

    public String mostrarReporteBasico() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("=== REPORTE BÁSICO DE BIBLIOTECA ===\n");
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
        reporte.append("====================================");
        
        return reporte.toString();
    }

    public String cargarLibrosDesdeCSV(String rutaArchivo) {
        int agregados = 0;
        int errores = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                // Saltar líneas vacías
                if (linea.trim().isEmpty()) continue;
                
                // Asumimos que el CSV está separado por comas o punto y coma
                String[] datos = linea.split("[,;]"); 
                
                if (datos.length >= 5) {
                    try {
                        int codigo = Integer.parseInt(datos[0].trim());
                        String titulo = datos[1].trim();
                        String autor = datos[2].trim();
                        String categoria = datos[3].trim();
                        int anio = Integer.parseInt(datos[4].trim());
                        
                        // Si el CSV trae el estado (6ta columna), lo leemos. Si no, es DISPONIBLE por defecto
                        EstadoLibro estadoObj = EstadoLibro.DISPONIBLE;
                        if (datos.length >= 6 && datos[5].trim().equalsIgnoreCase("Prestado")) {
                            estadoObj = EstadoLibro.PRESTADO;
                        }

                        // Reutilizamos las validaciones de seguridad
                        validarCodigo(codigo);
                        validarTexto(titulo, "Título");
                        validarTexto(autor, "Autor");
                        validarTexto(categoria, "Categoría");
                        validarAnio(anio);

                        // Insertamos en el árbol directamente
                        Libro libro = new Libro(codigo, titulo, autor, categoria, anio, estadoObj);
                        arbolLibros.insert(libro);
                        agregados++;
                        
                    } catch (Exception e) {
                        // Si un libro está duplicado o tiene letras en el código, se cuenta como error y se salta
                        errores++;
                    }
                } else {
                    errores++;
                }
            }
            return "Carga completada.\nLibros agregados exitosamente: " + agregados + "\nFilas con error u omitidas: " + errores;
            
        } catch (IOException e) {
            return "Error crítico al intentar leer el archivo: " + e.getMessage();
        }
    }

    private void actualizarCSVCompleto() {
        // Al usar 'false' en FileWriter, le decimos que sobrescriba el archivo con los datos más recientes
        try (PrintWriter pw = new PrintWriter(new FileWriter("libros.csv", false))) {
            
            List<Libro> todosLosLibros = arbolLibros.obtenerListaLibros();
            
            if (todosLosLibros != null) {
                for (Libro libro : todosLosLibros) {
                    pw.println(libro.getCodigo() + "," + libro.getTitulo() + "," + libro.getAutor() + "," + libro.getCategoria() + "," + libro.getAnio() + "," + libro.getEstado().toString());
                }
            }
        } catch (IOException e) {
            System.err.println("Error crítico al intentar guardar en el archivo CSV: " + e.getMessage());
        }
    }
}
package estructuras;

import estructuras.Cola;
import estructuras.LinkedBST;
import estructuras.ItemNotfound;
import estructuras.ExceptionIsEmpty;
import modelos.Libro;
import modelos.Solicitud;
import modelos.EstadoLibro;

public class GestorBiblioteca {

    private Cola<Solicitud> colaSolicitudes;
    private LinkedBST<Libro> arbolLibros;

    public GestorBiblioteca() {
        this.colaSolicitudes = new Cola<>();
        this.arbolLibros = new LinkedBST<>();
    }

    public Cola<Solicitud> getColaSolicitudes() {
        return colaSolicitudes;
    }

    public LinkedBST<Libro> getArbolLibros() {
        return arbolLibros;
    }

    public void procesarPrestamo() {
        if (colaSolicitudes.IsEmpty()) {
            System.out.println("No hay solicitudes pendientes en la cola.");
            return;
        }

        Solicitud solicitudActual = colaSolicitudes.dequeue();
        
        try {
            int idLibro = Integer.parseInt(solicitudActual.getcode_libro());
            Libro libroBuscado = new Libro(idLibro, "Temp", "Temp", "Temp", 0, EstadoLibro.DISPONIBLE);
            Libro libroEncontrado = arbolLibros.search(libroBuscado);

            if (libroEncontrado.getEstado() == EstadoLibro.DISPONIBLE) {
                libroEncontrado.setEstado(EstadoLibro.PRESTADO);
                System.out.println("Préstamo exitoso: El libro '" + libroEncontrado.getTitulo() + 
                                   "' ha sido prestado a " + solicitudActual.getname_eString());
            } else {
                System.out.println("El libro existe, pero actualmente no está disponible.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: El código de libro en la solicitud tiene un formato incorrecto.");
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error al procesar los datos del libro: " + e.getMessage());
        } catch (ItemNotfound e) {
            System.out.println("Error: El libro con código " + solicitudActual.getcode_libro() + " no está registrado en el sistema.");
        }
    }

    public void procesarDevolucion(int codigoLibro) {
        try {
            Libro libroBuscado = new Libro(codigoLibro, "Temp", "Temp", "Temp", 0, EstadoLibro.PRESTADO);
            Libro libroEncontrado = arbolLibros.search(libroBuscado);

            if (libroEncontrado.getEstado() == EstadoLibro.PRESTADO) {
                libroEncontrado.setEstado(EstadoLibro.DISPONIBLE);
                System.out.println("Devolución exitosa. El libro '" + libroEncontrado.getTitulo() + "' ahora está disponible.");
            } else {
                System.out.println("El libro ya se encuentra disponible. No se puede devolver.");
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error interno creando referencia de libro: " + e.getMessage());
        } catch (ItemNotfound e) {
            System.out.println("Error: No se encontró ningún libro con el código " + codigoLibro);
        }
    }
}

package modelos;

public class Libro {
    private String codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private String anio;
    private String estado;

    public Libro(String codigo, String titulo, String autor, String categoria, String anio, String estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.estado = estado;
    }

    public void setcodigo(String code){this.codigo = code;}
    public String getcode(){return this.codigo;}

    public void settitulo(String titulo){this.titulo = titulo;}
    public String getitulo(){return this.titulo;}

    public void setautor(String autor){this.autor = autor;}
    public String getautor(){return this.autor;}

    public void setcatgia(String categoria){this.categoria = categoria;}
    public String getcatgia(){return this.categoria;}

    public void setanio(String anio){this.anio = anio;}
    public String getanio(){return this.anio;}
    

}


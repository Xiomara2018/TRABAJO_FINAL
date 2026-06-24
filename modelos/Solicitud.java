package modelos;

public class Solicitud {
    private String codigo_est;
    private String name_est;
    private String code_libro;
    private String fecha_soli;

    public Solicitud(String codigo_est, String name_est, String code_libro, String fecha_soli){
        this.codigo_est = codigo_est;
        this.name_est = name_est;
        this.code_libro = code_libro;
        this.fecha_soli = fecha_soli;
    }

    public void setcodigo_est(String codigo_est){this.codigo_est = codigo_est;}
    public String getcodigo_est(){return this.codigo_est;}

    public void setname_est(String name_est){this.name_est = name_est;}
    public String getname_eString(){return this.name_est;}

    public void setcode_libro(String code_libro){this.code_libro = code_libro;}
    public String getcode_libro(){return this.code_libro;}

    public void setfecha_soli(String fecha_soli){this.fecha_soli = fecha_soli;}
    public String getfecha_soli(){return this.fecha_soli;}

    
}

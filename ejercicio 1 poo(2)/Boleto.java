//import 
import java.text.SimpleDateFormat;
import java.util.Date;

public class Boleto {
    private String numero;
    private Date fecha;
    private Teatro teatro;

    public Boleto(String numero, Date fecha, Teatro teatro, int correlativo) {
        this.numero = numero;
        this.fecha = fecha;
        this.numero = generarNumeroBoleto(fecha, correlativo);
    }

     private String generarNumeroBoleto(Date fecha, int correlativo) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        return sdf.format(fecha) + correlativo;
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public Teatro getTeatro() {
        return teatro;
    }

    public void setTeatro(Teatro teatro) {
        this.teatro = teatro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroBoleto() {
        return numero;
    }

    public void setNumeroBoleto(String numeroBoleto) {
        this.numero = numeroBoleto;
    }

    public Date getFechaBoleto() {
        return fecha;
    }

    //get localidad
    public Teatro getTeatroBoleto() {
        return teatro;
    }

    Object getLocalidad() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}

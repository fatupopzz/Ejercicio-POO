//import 
import java.text.SimpleDateFormat;
import java.util.Date;

public class Boleto {
    private String numero;
    private Date fechaCompra;
    private Localidad localidad;
    private String nombreComprador;
    private String emailComprador;

    public Boleto(Localidad localidad, Date fechaCompra, String nombreComprador, String emailComprador, int correlativo) {
        this.localidad = localidad;
        this.fechaCompra = fechaCompra;
        this.nombreComprador = nombreComprador;
        this.emailComprador = emailComprador;
        this.numero = generarNumeroBoleto(fechaCompra, correlativo);
    }

    private String generarNumeroBoleto(Date fecha, int correlativo) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        return sdf.format(fecha) + correlativo;
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

}
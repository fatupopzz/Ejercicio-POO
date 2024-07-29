import java.util.ArrayList;

public class comprador {
    private String nombre;
    private String email;
    private ArrayList<Boleto> boletosComprados;

    public comprador(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.boletosComprados = new ArrayList<>();
    }

    public boolean comprarBoleto(Boleto boleto) {
        return this.boletosComprados.add(boleto);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    // Obtener la cantidad de boletos comprados
    public int getCantidadBoletosComprados() {
        return boletosComprados.size();
    }

    // Listar los boletos comprados
    public ArrayList<Boleto> getBoletosComprados() {
        return new ArrayList<>(boletosComprados);
    }
}
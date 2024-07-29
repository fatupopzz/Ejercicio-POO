/*
 * Clase Localidad
 */


public class Localidad {
    private String nombre;
    private int capacidad;
    private int precio;
    private int boletosVendidos;
    private int disponibilidad;

    public Localidad(String nombre, int capacidad, int precio, int disponibilidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.boletosVendidos = 0;
        this.disponibilidad = disponibilidad;
    }

    public boolean venderBoletos(int cantidad) {
        if (boletosVendidos + cantidad <= capacidad) {
            boletosVendidos += cantidad;
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDisponibilidad() {
        return capacidad - boletosVendidos;
    }

    public int getPrecio() {
        return precio;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public int getCapacidad() {
        return capacidad;
    }

    
    
    
}

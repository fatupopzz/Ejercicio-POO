import java.util.ArrayList;
import java.util.Date;

public class Teatro {
	private String nombre;
	private int capacidadTotal;
	private Date fechaEvento;
	private ArrayList<Localidad> localidades;
	private ArrayList<Boleto> boletosVendidos;

	public Teatro(String nombre, int capacidadTotal, Date fechaEvento) {
		this.nombre = nombre;
		this.capacidadTotal = capacidadTotal;
		this.fechaEvento = fechaEvento;
		this.localidades = new ArrayList<>();
		this.boletosVendidos = new ArrayList<>();
		inicializarLocalidades();
	}

	private void inicializarLocalidades() {
		localidades.add(new Localidad("Balcon 2", 100, 0, 0));
		localidades.add(new Localidad("Platea", 100, 0, 0));
		localidades.add(new Localidad("VIP", 100, 0, 0));
	}

	public boolean venderBoleto(String nombreLocalidad, int cantidad) {
        for (Localidad localidad : localidades) {
            if (localidad.getNombre().equals(nombreLocalidad) && localidad.getDisponibilidad() >= cantidad) {
                if (localidad.venderBoletos(cantidad)) {
                    for (int i = 0; i < cantidad; i++) {
                        Boleto boleto = new Boleto(generarNumeroBoleto(), fechaEvento, this, boletosVendidos.size() + 1);
                        boletosVendidos.add(boleto);
                    }
                    return true;
                }
            }
        }
        return false;
	}

	public int consultarDisponibilidad(String nombreLocalidad) {
		for (Localidad localidad : localidades) {
			if (localidad.getNombre().equals(nombreLocalidad)) {
				return localidad.getDisponibilidad();
			}
		}
		return 0;
	}

	public String generarReporte() {
		StringBuilder reporte = new StringBuilder();
		reporte.append("Reporte de Boletos Vendidos:\n");
		for (Boleto boleto : boletosVendidos) {
			reporte.append("Boleto: ").append(boleto.getNumero()).append(", Localidad: ").append(boleto.getTeatro().getNombre()).append("\n");
		}
		return reporte.toString();
	}

	public String generarNumeroBoleto() {
		return "B" + (boletosVendidos.size() + 1);
	}

	public String getNombre() {
		return nombre;
	}

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public ArrayList<Localidad> getLocalidades() {
        return localidades;
    }

    //agregar boleto vendido
    public void agregarBoletoVendido(Boleto boleto) {
        boletosVendidos.add(boleto);
    }

    //obtener boletos vendidos
    public ArrayList<Boleto> getBoletosVendidos() {
        return boletosVendidos;
    }

	//obtener total boletos disponibles
	public int getTotalBoletosDisponibles() {
		int total = 0;
		for (Localidad localidad : localidades) {
			total += localidad.getDisponibilidad();
		}
		return total;
	}

	//obtener total dinero generado
	public double getTotalDineroGenerado() {
		double total = 0;
		for (Boleto boleto : boletosVendidos) {
			total += ((Localidad) boleto.getLocalidad()).getPrecio();
		}
		return total;
	}
	//agregar localidad
	public void agregarLocalidad(Localidad localidad) {
		localidades.add(localidad);
	}
   
}

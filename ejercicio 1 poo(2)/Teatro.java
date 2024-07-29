import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public double getTotalDineroGenerado() {
        double total = 0;
        for (Boleto boleto : boletosVendidos) {
            total += boleto.getLocalidad().getPrecio();
        }
        return total;
    }

    public void agregarLocalidad(Localidad localidad) {
        localidades.add(localidad);
    }

    public String mostrarMensaje(String mensaje) {
        return mensaje;
    }

    public void venderBoleto(Localidad localidad, String nombreComprador, String emailComprador) {
		if (boletosVendidos.size() < capacidadTotal) {
			Boleto boleto = new Boleto(localidad, new Date(), nombreComprador, emailComprador, boletosVendidos.size() + 1);
			boletosVendidos.add(boleto);
		} else {
			System.out.println("No hay más boletos disponibles.");
		}
	}

    public void guardarBoletosVendidos(String archivo) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            for (Boleto boleto : boletosVendidos) {
                writer.write(boleto.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaEvento = sdf.parse("25/12/2023");
            Teatro teatro = new Teatro("Teatro Nacional", 300, fechaEvento);

			teatro.venderBoleto(teatro.localidades.get(0), "Juan Perez", "juan.perez@example.com");
            teatro.venderBoleto(teatro.localidades.get(1), "Maria Lopez", "maria.lopez@example.com");
            teatro.venderBoleto(teatro.localidades.get(2), "Carlos Sanchez", "carlos.sanchez@example.com");

            System.out.println("Total dinero generado: " + teatro.getTotalDineroGenerado());

            teatro.guardarBoletosVendidos("boletos_vendidos.txt");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
	//get localidades
	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}

	//get agregar boleto vendido
	public void agregarBoletoVendido(Boleto boleto) {
		boletosVendidos.add(boleto);
	}

	//generar numero boleto
	public String generarNumeroBoleto()
	{
		return "";
	}

	//get boletos vendidos
	public ArrayList<Boleto> getBoletosVendidos() {
		return boletosVendidos;
	}

	//vender boleto
	public boolean venderBoleto(String localidadCompra, int cantidadBoletos) {
		return true;
	}

	//get total boletos disponibles
	public int getTotalBoletosDisponibles() {
		return 0;
	}

	//get localidad
	public Localidad getLocalidad(String localidadCompra) {
		return null;
	}


}

//imports
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {
    public static void Main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Configurar elementos iniciales
            System.out.print("Ingrese el nombre del teatro: ");
            String nombreTeatro = scanner.nextLine();
            Teatro teatro = new Teatro(nombreTeatro, 0, null);
            
            System.out.print("Ingrese la fecha de la venta (YYYY-MM-DD): ");
            String fechaVenta = scanner.nextLine();
            
            System.out.print("Ingrese el nombre de la localidad: ");
            String nombreLocalidad = scanner.nextLine();
            System.out.print("Ingrese la capacidad total de la localidad: ");
            int capacidadTotal = scanner.nextInt();
            System.out.print("Ingrese el precio del boleto para la localidad: ");
            double precioBoleto = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea
            
            Localidad localidad = new Localidad(nombreLocalidad, capacidadTotal, 0, (int)precioBoleto);
            teatro.agregarLocalidad(localidad);
            
            comprador comprador = null;
            boolean continuar = true;
            
            while (continuar) {
                System.out.print(mostrarMenu());
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el nombre del comprador: ");
                        String nombreComprador = scanner.nextLine();
                        System.out.print("Ingrese el correo del comprador: ");
                        String emailComprador = scanner.nextLine();
                        comprador = new comprador(nombreComprador, emailComprador);
                        mostrarMensaje("Comprador registrado exitosamente.");
                    }
                    case 2 -> {
                        if (comprador == null) {
                            mostrarMensaje("Primero debe agregar un comprador.");
                            break;
                        }
                        System.out.print("Ingrese la localidad: ");
                        String localidadCompra = scanner.nextLine();
                        System.out.print("Ingrese la cantidad de boletos (máximo 6): ");
                        int cantidadBoletos = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        if (cantidadBoletos > 6) {
                            mostrarMensaje("No puede comprar más de 6 boletos.");
                            break;
                        }
                        if (teatro.venderBoleto(localidadCompra, cantidadBoletos)) {
                            for (int i = 0; i < cantidadBoletos; i++) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date fechaVentaDate = dateFormat.parse(fechaVenta);
                                    Boleto boleto = new Boleto(teatro.generarNumeroBoleto(), fechaVentaDate, teatro, teatro.getBoletosVendidos().size() + 1);
                                    comprador.comprarBoleto(boleto);
                                    teatro.agregarBoletoVendido(boleto);
                                } catch (ParseException e) {
                                    mostrarMensaje("Error al parsear la fecha de venta.");
                                }
                            }
                            mostrarMensaje("Boletos comprados exitosamente.");
                        } else {
                            mostrarMensaje("No se pudieron comprar los boletos.");
                        }
                    }
                    case 3 -> {
                        mostrarMensaje("Disponibilidad total:");
                        for (Localidad loc : teatro.getLocalidades()) {
                            mostrarMensaje(loc.getNombre() + ": " + loc.getCapacidad() + " boletos disponibles");
                        }
                        mostrarMensaje("Total boletos disponibles: " + teatro.getTotalBoletosDisponibles());
                    }
                    case 4 -> {
                        System.out.print("Ingrese la localidad para consultar disponibilidad: ");
                        String localidadConsulta = scanner.nextLine();
                        for (Localidad loc : teatro.getLocalidades()) {
                            if (loc.getNombre().equals(localidadConsulta)) {
                                mostrarMensaje(localidadConsulta + ": " + loc.getDisponibilidad() + " boletos disponibles");
                                break;
                            }
                        }
                    }
                    case 5 -> {
                        mostrarMensaje("Reporte de caja:");
                        mostrarMensaje("Total dinero gastado: $" + teatro.getTotalDineroGenerado());
                    }
                    case 6 -> {
                        mostrarMensaje("Saliendo...");
                        continuar = false;
                    }
                    default -> mostrarMensaje("Opción no válida.");
                }
            }
        }
    }

    private static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Menú:\n");
        menu.append("1. Agregar nuevo comprador\n");
        menu.append("2. Comprar boletos\n");
        menu.append("3. Consultar disponibilidad total\n");
        menu.append("4. Consultar disponibilidad individual\n");
        menu.append("5. Reporte de caja\n");
        menu.append("6. Salir\n");
        menu.append("Seleccione una opción: ");
        return menu.toString();
    }

    private static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
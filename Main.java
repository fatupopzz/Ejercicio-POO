import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Teatro teatro = new Teatro("Teatro Nacional", 300, new Date());
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1 -> {
                    // Opción para agregar localidades
                    System.out.print("Ingrese el nombre de la localidad: ");
                    String nombreLocalidad = scanner.nextLine();
                    System.out.print("Ingrese la capacidad de la localidad: ");
                    int capacidadLocalidad = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.print("Ingrese el precio de la localidad: ");
                    double precioLocalidad = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la nueva línea

                }
                case 2 -> {
                    System.out.print("Ingrese la localidad para comprar boletos: ");
                    String localidadCompra = scanner.nextLine();
                    System.out.print("Ingrese la cantidad de boletos a comprar: ");
                    int cantidadBoletos = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.print("Ingrese la fecha de venta (yyyy-MM-dd): ");
                    String fechaVenta = scanner.nextLine();

                    if (teatro.venderBoleto(localidadCompra, cantidadBoletos)) {
                        for (int i = 0; i < cantidadBoletos; i++) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date fechaVentaDate = dateFormat.parse(fechaVenta);
                                Boleto boleto = new Boleto(teatro.getLocalidad(localidadCompra), fechaVentaDate, "Comprador " + i, "comprador" + i + "@gmail.com", teatro.getBoletosVendidos().size() + 1);
                                // comprador.comprarBoleto(boleto); // Asumiendo que hay un objeto comprador
                                teatro.agregarBoletoVendido(boleto);
                            } catch (ParseException e) {
                                mostrarMensaje("Error al parsear la fecha de venta.");
                            }
                        }
                        try {
                            teatro.guardarBoletosVendidos("boletos_vendidos.csv");
                            mostrarMensaje("Boletos vendidos guardados en boletos_vendidos.csv");
                        } catch (IOException e) {
                            mostrarMensaje("Error al guardar los boletos vendidos: " + e.getMessage());
                        }
                        mostrarMensaje("Boletos comprados exitosamente.");
                    } else {
                        mostrarMensaje("No se pudieron vender los boletos.");
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
                    try {
                        teatro.guardarBoletosVendidos("boletos_vendidos.csv");
                        mostrarMensaje("Boletos vendidos guardados en boletos_vendidos.csv");
                    } catch (IOException e) {
                        mostrarMensaje("Error al guardar los boletos vendidos: " + e.getMessage());
                    }
                }
                case 7 -> {
                    mostrarMensaje("Saliendo...");
                    continuar = false;
                }
                default -> mostrarMensaje("Opción no válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Agregar localidades");
        System.out.println("2. Vender boletos");
        System.out.println("3. Consultar disponibilidad total");
        System.out.println("4. Consultar disponibilidad por localidad");
        System.out.println("5. Reporte de caja");
        System.out.println("6. Guardar boletos vendidos en CSV");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
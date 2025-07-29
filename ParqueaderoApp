import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ParqueaderoApp {

    // Clase base
    static abstract class Vehiculo {
        protected String placa, marca, modelo;
        protected LocalTime horaEntrada;

        public Vehiculo(String placa, String marca, String modelo, LocalTime horaEntrada) {
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
            this.horaEntrada = horaEntrada;
        }

        public LocalTime getHoraEntrada() {
            return horaEntrada;
        }

        public String getPlaca() {
            return placa;
        }

        public abstract double getTarifaPorHora();

        @Override
        public String toString() {
            return placa + " - " + marca + " " + modelo + " (Entrada: " + horaEntrada + ")";
        }
    }

    static class Automovil extends Vehiculo {
        private String tipoCombustible;

        public Automovil(String placa, String marca, String modelo, LocalTime horaEntrada, String tipoCombustible) {
            super(placa, marca, modelo, horaEntrada);
            this.tipoCombustible = tipoCombustible;
        }

        @Override
        public double getTarifaPorHora() {
            return 2000; // pesos por hora
        }
    }

    static class Motocicleta extends Vehiculo {
        private int cilindraje;

        public Motocicleta(String placa, String marca, String modelo, LocalTime horaEntrada, int cilindraje) {
            super(placa, marca, modelo, horaEntrada);
            this.cilindraje = cilindraje;
        }

        @Override
        public double getTarifaPorHora() {
            return 1000;
        }
    }

    static class Camion extends Vehiculo {
        private double capacidadCarga;

        public Camion(String placa, String marca, String modelo, LocalTime horaEntrada, double capacidadCarga) {
            super(placa, marca, modelo, horaEntrada);
            this.capacidadCarga = capacidadCarga;
        }

        @Override
        public double getTarifaPorHora() {
            return 3000;
        }
    }

    // Clase parqueadero
    static class Parqueadero {
        private List<Vehiculo> vehiculos = new ArrayList<>();
        private int capacidad;

        public Parqueadero(int capacidad) {
            this.capacidad = capacidad;
        }

        public boolean registrarEntrada(Vehiculo v) {
            if (vehiculos.size() < capacidad) {
                vehiculos.add(v);
                return true;
            }
            return false;
        }

        public double registrarSalida(String placa, LocalTime horaSalida) {
            Vehiculo v = buscarVehiculo(placa);
            if (v == null) return 0;

            long minutos = Duration.between(v.getHoraEntrada(), horaSalida).toMinutes();
            double horas = Math.ceil(minutos / 60.0);
            double costo = horas * v.getTarifaPorHora();

            System.out.println("Hora entrada: " + v.getHoraEntrada());
            System.out.println("Hora salida : " + horaSalida);
            System.out.println("Tiempo total: " + minutos + " minutos");

            vehiculos.remove(v);

            String costoFormateado = String.format("$%,.0f COP", costo);
            System.out.println("Costo a pagar: " + costoFormateado);
            return costo;
        }

        public void consultarEstado() {
            if (vehiculos.isEmpty()) {
                System.out.println("No hay vehículos en el parqueadero.");
                return;
            }
            System.out.println("Vehículos actualmente en el parqueadero:");
            for (Vehiculo v : vehiculos) {
                System.out.println(" - " + v);
            }
        }

        public boolean estaLleno() {
            return vehiculos.size() >= capacidad;
        }

        private Vehiculo buscarVehiculo(String placa) {
            for (Vehiculo v : vehiculos) {
                if (v.getPlaca().equalsIgnoreCase(placa)) {
                    return v;
                }
            }
            return null;
        }
    }

    // Clase principal con menú
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            System.out.println("\n--- Menu Parqueadero ---");
            System.out.println("1. Registrar vehiculo");
            System.out.println("2. Registrar salida");
            System.out.println("3. Ver estado del parqueadero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    if (parqueadero.estaLleno()) {
                        System.out.println("El parqueadero esta lleno.");
                        break;
                    }

                    System.out.println("Tipo de vehiculo (1: Automovil, 2: Motocicleta, 3: Camion): ");
                    int tipo = sc.nextInt(); sc.nextLine();
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Hora de entrada (HH:mm): ");
                    LocalTime horaEntrada = LocalTime.parse(sc.nextLine(), formatter);

                    Vehiculo v = null;
                    if (tipo == 1) {
                        System.out.print("Tipo de combustible: ");
                        String comb = sc.nextLine();
                        v = new Automovil(placa, marca, modelo, horaEntrada, comb);
                    } else if (tipo == 2) {
                        System.out.print("Cilindraje: ");
                        int cil = sc.nextInt(); sc.nextLine();
                        v = new Motocicleta(placa, marca, modelo, horaEntrada, cil);
                    } else if (tipo == 3) {
                        System.out.print("Capacidad de carga (toneladas): ");
                        double carga = sc.nextDouble(); sc.nextLine();
                        v = new Camion(placa, marca, modelo, horaEntrada, carga);
                    } else {
                        System.out.println("Tipo no valido.");
                        break;
                    }

                    if (parqueadero.registrarEntrada(v)) {
                        System.out.println("Vehiculo registrado con exito.");
                    } else {
                        System.out.println("Error al registrar el vehiculo.");
                    }
                    break;

                case 2:
                    System.out.print("Placa del vehiculo a retirar: ");
                    String placaSalida = sc.nextLine();
                    System.out.print("Hora de salida (HH:mm): ");
                    LocalTime horaSalida = LocalTime.parse(sc.nextLine(), formatter);
                    double costo = parqueadero.registrarSalida(placaSalida, horaSalida);
                    if (costo == 0) {
                        System.out.println("Vehiculo no encontrado.");
                    }
                    break;

                case 3:
                    parqueadero.consultarEstado();
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    return;

                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
}

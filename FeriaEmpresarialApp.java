import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class FeriaEmpresarialApp {

    // Clase Empresa
    static class Empresa {
        private String nombre;
        private String sector;
        private String correoElectronico;

        public Empresa(String nombre, String sector, String correoElectronico) {
            this.nombre = nombre;
            this.sector = sector;
            this.correoElectronico = correoElectronico;
        }

        public String toString() {
            return "Empresa: " + nombre + ", Sector: " + sector + ", Email: " + correoElectronico;
        }
    }

    // Clase Stand
    static class Stand {
        private int numero;
        private String ubicacion;
        private String tamano;

        public Stand(int numero, String ubicacion, String tamano) {
            this.numero = numero;
            this.ubicacion = ubicacion;
            this.tamano = tamano;
        }

        public String toString() {
            return "Stand #" + numero + " - Ubicacion: " + ubicacion + ", Tamano: " + tamano;
        }
    }

    // Clase Visitante
    static class Visitante {
        private String nombre;
        private String identificacion;
        private String correoElectronico;

        public Visitante(String nombre, String identificacion, String correoElectronico) {
            this.nombre = nombre;
            this.identificacion = identificacion;
            this.correoElectronico = correoElectronico;
        }

        public String toString() {
            return "Visitante: " + nombre + ", ID: " + identificacion + ", Email: " + correoElectronico;
        }
    }

    // Clase Comentario
    static class Comentario {
        private String nombreVisitante;
        private LocalDate fecha;
        private int calificacion;
        private String comentario;

        public Comentario(String nombreVisitante, LocalDate fecha, int calificacion, String comentario) {
            this.nombreVisitante = nombreVisitante;
            this.fecha = fecha;
            this.calificacion = calificacion;
            this.comentario = comentario;
        }

        public String toString() {
            return "[" + fecha + "] " + nombreVisitante + " (" + calificacion + "/5): " + comentario;
        }
    }

    // Clase Gestora
    static class FeriaEmpresarial {
        private java.util.List<Empresa> empresas = new ArrayList<>();
        private java.util.List<Stand> stands = new ArrayList<>();
        private java.util.List<Visitante> visitantes = new ArrayList<>();
        private java.util.List<Comentario> comentarios = new ArrayList<>();

        public void registrarEmpresa(Empresa empresa) {
            empresas.add(empresa);
            System.out.println("Empresa registrada.");
        }

        public void registrarStand(Stand stand) {
            stands.add(stand);
            System.out.println("Stand registrado.");
        }

        public void registrarVisitante(Visitante visitante) {
            visitantes.add(visitante);
            System.out.println("Visitante registrado.");
        }

        public void agregarComentario(Comentario comentario) {
            comentarios.add(comentario);
            System.out.println("Comentario agregado.");
        }

        public void mostrarEmpresas() {
            System.out.println("\nEmpresas registradas:");
            for (Empresa e : empresas) {
                System.out.println(e);
            }
        }

        public void mostrarStands() {
            System.out.println("\nStands disponibles:");
            for (Stand s : stands) {
                System.out.println(s);
            }
        }

        public void mostrarVisitantes() {
            System.out.println("\nVisitantes registrados:");
            for (Visitante v : visitantes) {
                System.out.println(v);
            }
        }

        public void mostrarComentarios() {
            System.out.println("\nComentarios:");
            for (Comentario c : comentarios) {
                System.out.println(c);
            }
        }
    }

    // Metodo principal
    public static void main(String[] args) {
        FeriaEmpresarial feria = new FeriaEmpresarial();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menu Feria Empresarial ---");
            System.out.println("1. Registrar Empresa");
            System.out.println("2. Registrar Stand");
            System.out.println("3. Registrar Visitante");
            System.out.println("4. Agregar Comentario");
            System.out.println("5. Ver Empresas");
            System.out.println("6. Ver Stands");
            System.out.println("7. Ver Visitantes");
            System.out.println("8. Ver Comentarios");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la empresa: ");
                    String nombreEmpresa = sc.nextLine();
                    System.out.print("Sector: ");
                    String sector = sc.nextLine();
                    System.out.print("Correo electronico: ");
                    String correoEmpresa = sc.nextLine();
                    feria.registrarEmpresa(new Empresa(nombreEmpresa, sector, correoEmpresa));
                    break;
                case 2:
                    System.out.print("Numero del stand: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ubicacion: ");
                    String ubicacion = sc.nextLine();
                    System.out.print("Tamano: ");
                    String tamano = sc.nextLine();
                    feria.registrarStand(new Stand(numero, ubicacion, tamano));
                    break;
                case 3:
                    System.out.print("Nombre del visitante: ");
                    String nombreVisitante = sc.nextLine();
                    System.out.print("Identificacion: ");
                    String id = sc.nextLine();
                    System.out.print("Correo electronico: ");
                    String correoVisitante = sc.nextLine();
                    feria.registrarVisitante(new Visitante(nombreVisitante, id, correoVisitante));
                    break;
                case 4:
                    System.out.print("Nombre del visitante: ");
                    String nombreComentador = sc.nextLine();

                    int calificacion;
                    do {
                        System.out.print("Calificacion (1-5): ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Por favor ingrese un numero valido.");
                            sc.next();
                        }
                        calificacion = sc.nextInt();
                        sc.nextLine();
                        if (calificacion < 1 || calificacion > 5) {
                            System.out.println("Valor incorrecto. Ingrese un numero entre 1 y 5.");
                        }
                    } while (calificacion < 1 || calificacion > 5);

                    System.out.print("Comentario: ");
                    String comentario = sc.nextLine();
                    feria.agregarComentario(new Comentario(nombreComentador, LocalDate.now(), calificacion, comentario));
                    break;
                case 5:
                    feria.mostrarEmpresas();
                    break;
                case 6:
                    feria.mostrarStands();
                    break;
                case 7:
                    feria.mostrarVisitantes();
                    break;
                case 8:
                    feria.mostrarComentarios();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}

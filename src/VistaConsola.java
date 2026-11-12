import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

public class VistaConsola {

    private Scanner entrada;

    public VistaConsola() {

        entrada = new Scanner(System.in);
    }

    public int mostrarMenu() {

        System.out.println("\n===== ARENAGT =====");
        System.out.println("1. Registrar equipo");
        System.out.println("2. Crear encuentro");
        System.out.println("3. Crear exhibicion");
        System.out.println("4. Programar o reprogramar evento");
        System.out.println("5. Registrar resultado");
        System.out.println("6. Mostrar tabla de posiciones");
        System.out.println("7. Mostrar calendario");
        System.out.println("8. Salir");

        return leerEntero("Seleccione una opcion: ");
    }

    public String leerTexto(String mensaje) {

        System.out.print(mensaje);
        return entrada.nextLine();
    }

    public int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                int numero = Integer.parseInt(entrada.nextLine());
                return numero;
                
            } catch (NumberFormatException e) {

                System.out.println("Error: ingrese un numero valido");
            }
        }
    }

    public LocalDate leerFecha(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                String texto = entrada.nextLine();
                return LocalDate.parse(texto);

            } catch (java.time.format.DateTimeParseException e) {

                System.out.println("Fecha invalida. Use el formato AAAA-MM-DD");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }

    public void mostrarTabla(List<Equipo> equipos) {

        System.out.println("\n===== TABLA DE POSICIONES =====");

        System.out.printf("%-10s %-20s %-20s %-10s%n", "Codigo", "Equipo", "Empresa", "Puntos");

        for (Equipo equipo : equipos) {

            System.out.printf("%-10s %-20s %-20s %-10d%n", equipo.getCodigo(), equipo.getNombre(), equipo.getEmpresa(), equipo.getPuntosTabla());
        }
    }

    public void mostrarCalendario(Iterable<Programable> calendario) {

        System.out.println("\n===== CALENDARIO =====");

        for (Programable evento : calendario) {

            System.out.println( evento.getFecha() + " | " + evento.getHora() + ":00 | " + evento.getSede() + " | " + evento.getDescripcion() );
        }
    }

    public Programable elegirEvento(List<Programable> eventos) {

        if (eventos.isEmpty()) {

            System.out.println("No hay eventos registrados");
            return null;
        }

        System.out.println("\n===== EVENTOS =====");

        for (int i = 0; i < eventos.size(); i++) {

            System.out.println((i + 1) + ". " + eventos.get(i).getDescripcion());
        }

        int opcion = leerEntero("Seleccione un evento: ");

        if (opcion < 1 || opcion > eventos.size()) {

            System.out.println("Opcion invalida");
            return null;
        }

        return eventos.get(opcion - 1);
    }

    public Puntuable elegirEncuentro(List<Puntuable> encuentros) {

        if (encuentros.isEmpty()) {

            System.out.println("No hay encuentros registrados");
            return null;
        }

        System.out.println("\n===== ENCUENTROS =====");

        for (int i = 0; i < encuentros.size(); i++) {

            System.out.println((i + 1) + ". " + encuentros.get(i).getDescripcion());
        }

        int opcion = leerEntero("Seleccione un encuentro: ");

        if (opcion < 1 || opcion > encuentros.size()) {

            System.out.println("Opcion invalida");
            return null;
        }

        return encuentros.get(opcion - 1);
    }
    
}
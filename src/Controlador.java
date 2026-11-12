import java.time.LocalDate;

public class Controlador {

    private Copa copa;
    private VistaConsola vista;

    public Controlador(Copa copa, VistaConsola vista) {

        this.copa = copa;
        this.vista = vista;
    }

    public void cargarDatosIniciales() {

        Equipo faro = copa.registrarEquipo("E01", "Faro", "Empresa A");
        Equipo sol = copa.registrarEquipo("E02", "Sol", "Empresa A");
        Equipo luna = copa.registrarEquipo("E03", "Luna", "Empresa B");
        Equipo norte = copa.registrarEquipo("E04", "Norte", "Empresa B");

        Encuentro partido1 = new FutbolSala(faro, sol);
        Encuentro partido2 = new FutbolSala(luna, norte);
        Encuentro partido3 = new Baloncesto(faro, luna);
        Encuentro partido4 = new Baloncesto(sol, norte);

        copa.agregarEncuentro(partido1);
        copa.agregarEncuentro(partido2);
        copa.agregarEncuentro(partido3);
        copa.agregarEncuentro(partido4);

        copa.programarEvento(partido1, LocalDate.of(2026, 11, 12), 9, "Cancha A");
        copa.programarEvento(partido2, LocalDate.of(2026, 11, 12), 11, "Cancha A");
        copa.programarEvento(partido3, LocalDate.of(2026, 11, 13), 10, "Gimnasio");
        copa.programarEvento(partido4, LocalDate.of(2026, 11, 13), 12, "Gimnasio");

        copa.registrarResultado(partido1, 2, 2);
        copa.registrarResultado(partido2, 3, 1);

        Exhibicion exhibicion = new Exhibicion("Clinica de baloncesto");

        copa.agregarExhibicion(exhibicion);
        copa.programarEvento(exhibicion, LocalDate.of(2026, 11, 12), 14, "Gimnasio");
    }

    public void iniciar() {

        int opcion = 0;

        while (opcion != 8) {

            opcion = vista.mostrarMenu();

            try {

                switch (opcion) {

                    case 1:
                        registrarEquipo();
                        break;

                    case 2:
                        crearEncuentro();
                        break;

                    case 3:
                        crearExhibicion();
                        break;

                    case 4:
                        programarEvento();
                        break;

                    case 5:
                        registrarResultado();
                        break;

                    case 6:
                        vista.mostrarTabla(copa.getTablaPosiciones());
                        break;

                    case 7:
                        vista.mostrarCalendario(copa);
                        break;

                    case 8:
                        vista.mostrarMensaje("Gracias por usar ArenaGT");
                        break;

                    default:
                        vista.mostrarMensaje("Opcion invalida");

                }

            } catch (IllegalArgumentException e) {

                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void registrarEquipo() {

        String codigo = vista.leerTexto("Codigo: ");
        String nombre = vista.leerTexto("Nombre: ");
        String empresa = vista.leerTexto("Empresa: ");

        copa.registrarEquipo(codigo, nombre, empresa);

        vista.mostrarMensaje("Equipo registrado correctamente.");
    }

    private void crearEncuentro() {

        vista.mostrarMensaje("\n1. Futbol sala");
        vista.mostrarMensaje("2. Baloncesto");

        int deporte = vista.leerEntero("Seleccione el deporte: ");

        if (deporte != 1 && deporte != 2) {

            vista.mostrarMensaje("Deporte invalido");
            return;
        }

        String codigo1 = vista.leerTexto("Codigo del primer equipo: ");
        String codigo2 = vista.leerTexto("Codigo del segundo equipo: ");

        Equipo equipo1 = copa.buscarEquipo(codigo1);
        Equipo equipo2 = copa.buscarEquipo(codigo2);

        if (equipo1 == null || equipo2 == null) {

            vista.mostrarMensaje("Uno de los equipos no existe.");
            return;
        }

        Encuentro encuentro;

        if (deporte == 1) {

            encuentro = new FutbolSala(equipo1, equipo2);
        } else {

            encuentro = new Baloncesto(equipo1, equipo2);
        }

        copa.agregarEncuentro(encuentro);

        vista.mostrarMensaje("Encuentro creado correctamente.");
    }

    private void crearExhibicion() {

        String titulo = vista.leerTexto("Titulo de la exhibicion: ");

        if (titulo.isEmpty()) {

            vista.mostrarMensaje("El titulo no puede estar vacio");
            return;
        }

        Exhibicion exhibicion = new Exhibicion(titulo);

        copa.agregarExhibicion(exhibicion);

        vista.mostrarMensaje("Exhibicion creada correctamente.");
    }

    private void programarEvento() {

        Programable evento = vista.elegirEvento(copa.getEventos());

        if (evento == null) {

            return;
        }

        LocalDate fecha = vista.leerFecha("Fecha (AAAA-MM-DD): ");
        int hora = vista.leerEntero("Hora (0-23): ");
        String sede = vista.leerTexto("Sede: ");

        copa.programarEvento(evento, fecha, hora, sede);

        vista.mostrarMensaje("Evento programado");
    }

    private void registrarResultado() {

        Puntuable encuentro = vista.elegirEncuentro(copa.getEncuentros());

        if (encuentro == null) {

            return;
        }

        int marcador1 = vista.leerEntero("Marcador del equipo 1: ");
        int marcador2 = vista.leerEntero("Marcador del equipo 2: ");

        copa.registrarResultado(encuentro, marcador1, marcador2);

        vista.mostrarMensaje("Resultado registrado");
    }
}
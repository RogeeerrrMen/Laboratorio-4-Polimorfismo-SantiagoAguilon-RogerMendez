import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Copa implements Iterable<Programable> {

    private List<Equipo> equipos;
    private List<Programable> eventos;
    private List<Puntuable> encuentros;

    public Copa() {

        equipos = new ArrayList<>();
        eventos = new ArrayList<>();
        encuentros = new ArrayList<>();
    }

    public Equipo registrarEquipo(String codigo, String nombre, String empresa) {

        if (codigo == null || codigo.isEmpty()) {

            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }

        if (nombre == null || nombre.isEmpty()) {

            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if (empresa == null || empresa.isEmpty()) {

            throw new IllegalArgumentException("La empresa no puede estar vacia");
        }

        if (buscarEquipo(codigo) != null) {

            throw new IllegalArgumentException("Ya existe un equipo con ese codigo");
        }

        Equipo nuevo = new Equipo(codigo, nombre, empresa);
        equipos.add(nuevo);

        return nuevo;
    }

    public Equipo buscarEquipo(String codigo) {

        for (Equipo equipo : equipos) {

            if (equipo.getCodigo().equals(codigo)) {

                return equipo;
            }
        }

        return null;
    }

    public void agregarEncuentro(Encuentro encuentro) {

        if (encuentro == null) {

            throw new IllegalArgumentException("El encuentro no es valido");
        }

        if (!equipos.contains(encuentro.getEquipo1()) || !equipos.contains(encuentro.getEquipo2())) {

            throw new IllegalArgumentException("Los equipos deben estar registrados");
        }

        if (eventos.contains(encuentro)) {

            throw new IllegalArgumentException("El encuentro ya esta registrado");
        }

        eventos.add(encuentro);
        encuentros.add(encuentro);
    }

    public void agregarExhibicion(Exhibicion exhibicion) {

        if (exhibicion == null) {

            throw new IllegalArgumentException("La exhibicion no es valida");
        }

        if (eventos.contains(exhibicion)) {

            throw new IllegalArgumentException("La exhibicion ya esta registrada");
        }

        eventos.add(exhibicion);
    }

    public List<Programable> getEventos() {

        return new ArrayList<>(eventos);
    }

    public List<Puntuable> getEncuentros() {

        return new ArrayList<>(encuentros);
    }

    public void programarEvento(Programable evento, LocalDate fecha, int hora, String sede) {

        if (!eventos.contains(evento)) {

            throw new IllegalArgumentException("El evento no esta registrado");
        }

        if (fecha == null || hora < 0 || hora > 23 || sede == null || sede.isEmpty()) {

            throw new IllegalArgumentException("Datos de programacion invalidos");
        }

        validarConflictos(evento, fecha, hora, sede);

        evento.programar(fecha, hora, sede);
    }

    private void validarConflictos(Programable evento, LocalDate fecha, int hora, String sede) {

        for (Programable otro : eventos) {

            if (otro == evento || !otro.estaProgramado()) {

                continue;
            }

            if (otro.getFecha().equals(fecha) && otro.getHora() == hora) {

                if (otro.getSede().equalsIgnoreCase(sede)) {

                    throw new IllegalArgumentException( "La sede ya esta ocupada en esa fecha y hora." );
                }

                for (Equipo equipo : evento.getEquiposOcupados()) {

                    if (otro.getEquiposOcupados().contains(equipo)) {

                        throw new IllegalArgumentException( "Un equipo ya tiene otro encuentro en ese horario." );
                    }
                }
            }
        }
    }

    public void registrarResultado(Puntuable encuentro, int marcador1, int marcador2) {

        if (!encuentros.contains(encuentro)) {

            throw new IllegalArgumentException("El encuentro no esta registrado");
        }

        encuentro.registrarResultado(marcador1, marcador2);
    }

    public List<Equipo> getTablaPosiciones() {

        List<Equipo> tabla = new ArrayList<>(equipos);
        Collections.sort(tabla);

        return tabla;
    }

    @Override
    public Iterator<Programable> iterator() {

        List<Programable> calendario = new ArrayList<>();

        for (Programable evento : eventos) {

            if (evento.estaProgramado()) {

                calendario.add(evento);
            }
        }

        Collections.sort(calendario);

        return calendario.iterator();
    }
}
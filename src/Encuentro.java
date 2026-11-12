import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Encuentro implements Programable, Puntuable {

    private Equipo equipo1;
    private Equipo equipo2;
    private Integer marcador1;
    private Integer marcador2;
    private LocalDate fecha;
    private Integer hora;
    private String sede;
    private EstadoEncuentro estado;

    public Encuentro(Equipo equipo1, Equipo equipo2) {

        if (equipo1 == equipo2) {

            throw new IllegalArgumentException("Un equipo no puede jugar contra el mismo");
        }

        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.marcador1 = null;
        this.marcador2 = null;
        this.fecha = null;
        this.hora = null;
        this.sede = null;
        this.estado = EstadoEncuentro.SIN_PROGRAMAR;
    }

    @Override
    public void programar(LocalDate fecha, int hora, String sede) {

        if (estado == EstadoEncuentro.FINALIZADO) {

            throw new IllegalArgumentException("El partido termino");
        }

        if (fecha == null || hora < 0 || hora > 23 || sede == null || sede.isEmpty()) {

            throw new IllegalArgumentException("Datos de programacion no validos");
        }

        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede;
        this.estado = EstadoEncuentro.PROGRAMADO;

    }

    @Override
    public void registrarResultado(int marcador1, int marcador2) {

        if (estado != EstadoEncuentro.PROGRAMADO) {

            throw new IllegalArgumentException("El encuentro no esta programado o ya termino");
        }

        if (marcador1 < 0 || marcador2 < 0) {

            throw new IllegalArgumentException("Los marcadores no pueden ser negativos");
        }

        if (!validarMarcador(marcador1, marcador2)) {

            throw new IllegalArgumentException("Marcador invalido para este deporte");
        }

        int puntos1 = calcularPuntos(marcador1, marcador2);
        int puntos2 = calcularPuntos(marcador2, marcador1);

        this.marcador1 = marcador1;
        this.marcador2 = marcador2;

        equipo1.sumarPuntos(puntos1);
        equipo2.sumarPuntos(puntos2);

        this.estado = EstadoEncuentro.FINALIZADO;
    }

    protected abstract boolean validarMarcador(int marcador1, int marcador2);
    protected abstract int calcularPuntos(int aFavor, int enContra);

    @Override
    public abstract String getDescripcion();

    @Override
    public LocalDate getFecha() {

        return fecha;
    }

    @Override
    public Integer getHora() {

        return hora;
    }

    @Override
    public String getSede() {

        return sede;
    }

    @Override
    public List<Equipo> getEquiposOcupados() {

        List<Equipo> equipos = new ArrayList<>();

        equipos.add(equipo1);
        equipos.add(equipo2);

        return equipos;
    }

    public Equipo getEquipo1() {

        return equipo1;
    }

    public Equipo getEquipo2() {

        return equipo2;
    }

    public Integer getMarcador1() {

        return marcador1;
    }

    public Integer getMarcador2() {

        return marcador2;
    }

    public EstadoEncuentro getEstado() {

        return estado;
    }
    
}
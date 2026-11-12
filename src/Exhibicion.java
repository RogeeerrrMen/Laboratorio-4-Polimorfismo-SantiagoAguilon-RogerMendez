import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Exhibicion implements Programable {

    private String titulo;
    private LocalDate fecha;
    private Integer hora;
    private String sede;
    private EstadoExhibicion estado;

    public Exhibicion(String titulo) {

        this.titulo = titulo;
        this.fecha = null;
        this.hora = null;
        this.sede = null;
        this.estado = EstadoExhibicion.SIN_PROGRAMAR;
    }

    @Override
    public void programar(LocalDate fecha, int hora, String sede) {

        if (fecha == null || hora < 0 || hora > 23 || sede == null || sede.isEmpty()) {

            throw new IllegalArgumentException("Datos de programacion no validos");
        }

        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede;
        this.estado = EstadoExhibicion.PROGRAMADA;
    }

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

        return new ArrayList<Equipo>();
    }

    @Override
    public String getDescripcion() {

        return "Exhibicion: " + titulo;
    }

    public String getTitulo() {

        return titulo;
    }

    public EstadoExhibicion getEstado() {

        return estado;
    }
}
import java.time.LocalDate;
import java.util.List;

public interface Programable extends Comparable<Programable> {

    void programar(LocalDate fecha, int hora, String sede);

    LocalDate getFecha();
    Integer getHora();
    String getSede();

    List<Equipo> getEquiposOcupados();

    String getDescripcion();

    default boolean estaProgramado() {

        return getFecha() != null;
    }

    @Override
    default int compareTo(Programable otro) {

        int comparacion = getFecha().compareTo(otro.getFecha());

        if (comparacion != 0) {

            return comparacion;
        }

        comparacion = getHora().compareTo(otro.getHora());

        if (comparacion != 0) {

            return comparacion;
        }

        return getSede().compareToIgnoreCase(otro.getSede());

    }
}
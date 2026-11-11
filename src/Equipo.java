public class Equipo implements Comparable<Equipo> {

    private final String codigo;
    private final String nombre;
    private final String empresa;
    private int puntosTabla;

    public Equipo(String codigo, String nombre, String empresa) {

        if (codigo == null || codigo.trim().isEmpty()) {

            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }

        if (nombre == null || nombre.trim().isEmpty()) {

            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if (empresa == null || empresa.trim().isEmpty()) {

            throw new IllegalArgumentException("La empresa no puede estar vacia");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.empresa = empresa;
        this.puntosTabla = 0;

    }

    public String getCodigo() {

        return codigo;
    }

    public String getNombre() {

        return nombre;
    }

    public String getEmpresa() {

        return empresa;
    }

    public int getPuntosTabla() {

        return puntosTabla;
    }

    public void sumarPuntos(int puntos) {

        if (puntos >= 0) {

            puntosTabla += puntos;
        }
    }

    @Override
    public int compareTo(Equipo otro) {

        if (this.puntosTabla != otro.puntosTabla) {

            return otro.puntosTabla - this.puntosTabla;
        }

        return this.nombre.compareToIgnoreCase(otro.nombre);
        
    }

}
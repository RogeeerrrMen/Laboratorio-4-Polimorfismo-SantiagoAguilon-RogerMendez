public class Baloncesto extends Encuentro {

    public Baloncesto(Equipo equipo1, Equipo equipo2) {

        super(equipo1, equipo2);
    }

    @Override
    protected boolean validarMarcador(int marcador1, int marcador2) {

        if (marcador1 == marcador2) {

            return false;
        }

        return marcador1 >= 0 && marcador2 >= 0;
    }

    @Override
    protected int calcularPuntos(int aFavor, int enContra) {

        if (aFavor > enContra) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public String getDescripcion() {

        return "Baloncesto: " + getEquipo1().getNombre() + " vs " + getEquipo2().getNombre();
    }
}
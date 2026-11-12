public class FutbolSala extends Encuentro {

    public FutbolSala(Equipo equipo1, Equipo equipo2) {

        super(equipo1, equipo2);
    }

    @Override
    protected boolean validarMarcador(int marcador1, int marcador2) {

        return marcador1 >= 0 && marcador2 >= 0;
    }

    @Override
    protected int calcularPuntos(int aFavor, int enContra) {

        if (aFavor > enContra) {

            return 3;
        } else if (aFavor == enContra) {

            return 1;
        } else {

            return 0;
        }
    }

    @Override
    public String getDescripcion() {

        return "Futbol Sala: " + getEquipo1().getNombre() + " vs " + getEquipo2().getNombre();
    }
}
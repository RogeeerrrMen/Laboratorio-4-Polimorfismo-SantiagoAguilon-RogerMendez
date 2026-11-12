public class Main {

    public static void main(String[] args) {

        Copa copa = new Copa();
        VistaConsola vista = new VistaConsola();
        Controlador controlador = new Controlador(copa, vista);

        controlador.cargarDatosIniciales();
        controlador.iniciar();
    }
}

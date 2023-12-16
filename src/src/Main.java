import controller.SistemaController;
import model.hotel.Hotel;

public class Main {
    public static void main(String[] args) {

        // Instancia um novo hotel
        Hotel hotel = new Hotel();

        // Instancia um novo controlador e inicia o sistema
        SistemaController controlador = new SistemaController(hotel);
        controlador.iniciarSistema();
    }
}
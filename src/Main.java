import controller.SistemaController;
import model.hotel.Hotel;

/**
 * Classe principal do sistema.
 */
public class Main {
    /**
     * Método principal do sistema.
     * @param args Argumentos passados para o sistema.
     */
    public static void main(String[] args) {

        // Instancia um novo hotel
        Hotel hotel = new Hotel();

        // Instancia um novo controlador e inicia o sistema
        SistemaController controlador = new SistemaController(hotel);
        controlador.iniciarSistema();
    }
}   
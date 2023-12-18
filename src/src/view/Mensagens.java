package view;
import javax.swing.*;

/**
 * Essa classe contem todas as mensagens que serão exibidas para o usuário
 */
public class Mensagens {

    public static void erroLogin() {
        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
    }

    public static boolean verificarSair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja sair do programa?") == JOptionPane.YES_OPTION) {
            return true;
        }
        return false; 
    }

    public static void mensagemFinal() {
        JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema!");
    }
}
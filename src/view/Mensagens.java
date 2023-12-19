package view;
import javax.swing.*;

/**
 * Classe que representa as mensagens genéricas que serão exibidas ao usuário.
 */
public class Mensagens {

    /**
     * Método estático para exibir uma mensagem de erro.
     */
    public static void erroLogin() {
        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
    }

    /**
     * Método para verificar se o usuário deseja sair do programa.
     * @return true se o usuário deseja sair, false caso contrário.
     */
    public static boolean verificarSair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja sair do programa?") == JOptionPane.YES_OPTION) {
            return true;
        }
        return false; 
    }

    /**
     * Método estático para exibir uma mensagem final.
     */
    public static void mensagemFinal() {
        JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema!");
    }
}
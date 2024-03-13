package view.utils;

import java.util.*;
import javax.swing.*;


public class UtilsViewImpl implements IUtilsView{

    @Override
    public String[] login() {

        Map<String, JComponent> campos = new HashMap<>();

        campos.put("Login", new JTextField(20));
        campos.put("Senha", new JPasswordField(23));

        JPanel painel = criarPainel();
        addComponentes(painel, campos);


        if (criarTela(painel, "Login") == JOptionPane.OK_OPTION) {

            String login = ((JTextField) campos.get("Login")).getText();
            String senha = new String(((JPasswordField) campos.get("Senha")).getPassword());

            if (login.isEmpty() || senha.isEmpty()) 
            {
                mensagem("Os campos de login e senha não podem estar vazios. Tente novamente.", "Erro");
                return login(); // Chamada recursiva para exibir a tela novamente 
            } 
            else {
                return new String[] {login, senha};
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarSair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja sair do programa?") == JOptionPane.YES_OPTION) {
            return true;
        }
        return false; 
    }

    public static void mensagem(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static JPanel criarPainel() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        return painel;
    }

    public static void addComponentes(JPanel painel, Map<String, JComponent> campos) {
        
        for (Map.Entry<String, JComponent> data : campos.entrySet()) {
            String chave = data.getKey();
            JComponent valor = data.getValue();
            
            painel.add(new JLabel(chave));
            painel.add(valor);
        }
    }

    public static void addComponente(JPanel painel, String titulo, JComponent campo) {
        painel.add(new JLabel(titulo));
        painel.add(campo);
    }

    public static int criarTela(JPanel painel, String titulo) {
        return JOptionPane.showConfirmDialog(null, painel, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }   
}

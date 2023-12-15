package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.hotel.Hotel;
import model.lodging.Hospedagem;
import model.people.*;

public class Sistema {
    public static void start(){

        Hotel hotel = new Hotel();
        boolean superUser = false;
        Hospede logado = null;

        String[] usuario = menuLogin();

        // Se o usuario for admin
        if (usuario[0].equals("admin") && usuario[1].equals("admin")) {

            System.out.println("Bem vindo, administrador!");
            superUser = true;
        } 
        else{
            for (Funcionario funcionario : hotel.getFuncionarios()) {
                if (usuario[0].equals(funcionario.getLogin()) && usuario[1].equals(funcionario.getSenha())) {

                    System.out.println("Bem vindo, " + funcionario.getNome() + "!");
                    superUser = true;
                    break;
                }
            }

            if (superUser == false) {
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (usuario[0].equals(hospedagem.getHospede().getLogin()) && usuario[1].equals(hospedagem.getHospede().getSenha())) 
                {
                    logado = hospedagem.getHospede();
                    System.out.println("Bem vindo, " + hospedagem.getHospede().getNome() + "!, voce é hospede");
                    break;
                }
            }
            }
        }
    }

    public static String[] menuLogin(){
        // Cria campos de texto para os dados
        JTextField campoLogin = new JTextField(10);
        JTextField campoSenha = new JTextField(10);

        // Cria um novo painel
        JPanel painel = new JPanel();

        // Define o layout do painel para BoxLayout.Y_AXIS
        // boxlayout.y_axis é um layout que organiza os componentes em uma coluna
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); 

        // Adiciona rótulos e campos de texto ao painel
        painel.add(new JLabel("Login:"));
        painel.add(campoLogin);

        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);

        // Mostra o painel em um JOptionPane
        int result = JOptionPane.showConfirmDialog(null, painel, 
               "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            String[] array = {campoLogin.getText(), campoSenha.getText()};
            return array;
        }
        return null;
    }
}

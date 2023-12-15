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

        System.out.println("Hello World!");
        boolean superUser = false;

        int opcao = 0;
        String senha;
        hotel.addFuncionario(new Funcionario(123, "henrique", "rua", "cidade", "estado", 123, "data"));

        String[] usuario = menuLogin();

        if (usuario[0].equals("admin") && usuario[1].equals("admin")) {

            System.out.println("Bem vindo, administrador!");
            superUser = true;
        } 
        else{
            for (Funcionario funcionario : hotel.getFuncionarios()) 
            {
                if (usuario[0].equals(funcionario.getLogin()) && usuario[1].equals(funcionario.getSenha())) 
                {
                    System.out.println("Bem vindo, " + funcionario.getNome() + "!");
                    superUser = true;
                    break;
                }
            }

            if (superUser == false) {
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (usuario[0].equals(hospedagem.getHospede().getLogin()) && usuario[1].equals(hospedagem.getHospede().getSenha())) 
                {
                    System.out.println("Bem vindo, " + hospedagem.getHospede().getNome() + "!, voce é hospede");
                    break;
                }
            }
            }
        }
    }

    /*
     * TODO:
     * 
     * 
     * Tela de login;
     * 
     * Administrador (Senha: admin):
        * Tela de cadastro de funcionario;
        * Tela de remover funcionario;
        * Tela de lista de funcionarios; 
     * 
     * Funcionario:
     * Tela principal com opções de:
     * Tela de adicionar reserva;
        * Tela de cadastro de hospede;
        * Tela de remover hospede;
        * Tela de lista de hospedes;
     *  
     * 
     * 
     */

    public static String[] menuLogin(){
        // Cria campos de texto para os dados
        JTextField loginField = new JTextField(10);
        JTextField senhaField = new JTextField(10);

        // Cria um novo painel
        JPanel painel = new JPanel();

        // Define o layout do painel para BoxLayout.Y_AXIS
        // boxlayout.y_axis é um layout que organiza os componentes em uma coluna
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); 

        // Adiciona rótulos e campos de texto ao painel
        painel.add(new JLabel("Login:"));
        painel.add(loginField);
        painel.add(new JLabel("Senha:"));
        painel.add(senhaField);

        // Mostra o painel em um JOptionPane
        int result = JOptionPane.showConfirmDialog(null, painel, 
               "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            String[] array = {loginField.getText(), senhaField.getText()};
            return array;
        }
        return null;
    }



}

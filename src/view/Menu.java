package view;

import javax.swing.*;
import controller.*;

/**
 * Essa classe contem todos os menus
 */
public class Menu {
    
    /**
     * Apresenta um diálogo de login com campos para inserção de login e senha.
     *
     * @return Um array de String contendo o login na posição 0 e a senha na posição 1, caso o usuário clique em "OK".
     *         Retorna null se o usuário cancelar o diálogo.
     */
    public static String[] menuLogin() {

        JTextField campoLogin = new JTextField(10);
        JTextField campoSenha = new JTextField(10);
        

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        
        painel.add(new JLabel("Login:"));
        painel.add(campoLogin);
        
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);
        

        int result = JOptionPane.showConfirmDialog(null, painel, "Preencha todos os campos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
            return new String[]{ campoLogin.getText(), campoSenha.getText() };
        }
        return null;
    }
    
    /**
     * Apresenta um menu de opções para interação com funcionalidades administrativas.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesAdmin}.
     *         Pode ser {@link OpcoesAdmin#CADASTRAR_FUNC}, {@link OpcoesAdmin#LISTAR_FUNC},
     *         {@link OpcoesAdmin#REMOVER_FUNC}, ou {@link OpcoesAdmin#SAIR}.
     */
    public static OpcoesAdmin menuAdmin() {

        OpcoesAdmin[] opcoes = { OpcoesAdmin.CADASTRAR_FUNC, OpcoesAdmin.LISTAR_FUNC, OpcoesAdmin.REMOVER_FUNC, OpcoesAdmin.SAIR };

        return (OpcoesAdmin) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Admin", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    /**
     * Apresenta um menu de opções para interação com funcionalidades relacionadas a funcionários.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesFuncionario}.
     *         Pode ser {@link OpcoesFuncionario#CADASTRAR_HOSPEDAGEM}, {@link OpcoesFuncionario#LISTAR_ACOMODACOES},
     *         {@link OpcoesFuncionario#REMOVER_HOSPEDAGEM}, {@link OpcoesFuncionario#LISTAR_CLIENTES}, ou {@link OpcoesFuncionario#SAIR}.
     */
    public static OpcoesFuncionario menuFuncionario() {

        OpcoesFuncionario[] opcoes = { OpcoesFuncionario.CADASTRAR_HOSPEDAGEM, OpcoesFuncionario.CADASTRAR_ACOMODACAO, OpcoesFuncionario.LISTAR_ACOMODACOES, OpcoesFuncionario.REMOVER_HOSPEDAGEM, OpcoesFuncionario.LISTAR_CLIENTES, OpcoesFuncionario.SAIR };

        return (OpcoesFuncionario) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Funcionario", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }


    /**
     * Apresenta um menu de opções para interação com informações do hóspede.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesHospede}.
     *         Pode ser {@link OpcoesHospede#LISTAR_CONSUMO}, {@link OpcoesHospede#RELATORIO_ESTADIA},
     *         ou {@link OpcoesHospede#SAIR}.
     */
    public static OpcoesHospede menuHospede() {

        OpcoesHospede[] opcoes = { OpcoesHospede.RELATORIO_CONSUMO, OpcoesHospede.RELATORIO_ESTADIA, OpcoesHospede.SAIR };

        return (OpcoesHospede) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Hospede", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

}

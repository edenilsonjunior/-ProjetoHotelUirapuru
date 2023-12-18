package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.OpcoesAdmin;
import controller.OpcoesFuncionario;
import controller.OpcoesHospede;

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
        // Cria campos de texto para os dados
        JTextField campoLogin = new JTextField(10);
        JTextField campoSenha = new JTextField(10);
        
        // Cria um novo painel
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // BoxLayout organiza os componentes em coluna
        
        // Adiciona rótulos e campos de texto ao painel
        painel.add(new JLabel("Login:"));
        painel.add(campoLogin);
        
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);
        
        // Mostra o painel em um JOptionPane
        String titulo = "Preencha todos os campos";

        int result = JOptionPane.showConfirmDialog(null, painel, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            
            // retorna um array com os dados do login e senha
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
        // Cria um array com as opções
        OpcoesAdmin[] opcoes = { OpcoesAdmin.CADASTRAR_FUNC, OpcoesAdmin.LISTAR_FUNC, OpcoesAdmin.REMOVER_FUNC,
                OpcoesAdmin.SAIR };
    
        // Mostra um JOptionPane com as opções
    
        return (OpcoesAdmin) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Admin",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    /**
     * Apresenta um menu de opções para interação com funcionalidades relacionadas a funcionários.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesFuncionario}.
     *         Pode ser {@link OpcoesFuncionario#CADASTRAR_HOSPEDAGEM}, {@link OpcoesFuncionario#LISTAR_ACOMODACOES},
     *         {@link OpcoesFuncionario#REMOVER_HOSPEDAGEM}, {@link OpcoesFuncionario#LISTAR_CLIENTES}, ou {@link OpcoesFuncionario#SAIR}.
     */
    public static OpcoesFuncionario menuFuncionario() {
        // Cria um array com as opções
        OpcoesFuncionario[] opcoes = { OpcoesFuncionario.CADASTRAR_HOSPEDAGEM, OpcoesFuncionario.CADASTRAR_ACOMODACAO, OpcoesFuncionario.LISTAR_ACOMODACOES,
                OpcoesFuncionario.REMOVER_HOSPEDAGEM, OpcoesFuncionario.LISTAR_CLIENTES, OpcoesFuncionario.SAIR };

        // Mostra um JOptionPane com as opções

        return (OpcoesFuncionario) JOptionPane.showInputDialog(null, "Escolha uma opção",
                "Menu Funcionario", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }


    /**
     * Apresenta um menu de opções para interação com informações do hóspede.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesHospede}.
     *         Pode ser {@link OpcoesHospede#LISTAR_CONSUMO}, {@link OpcoesHospede#RELATORIO_ESTADIA},
     *         ou {@link OpcoesHospede#SAIR}.
     */
    public static OpcoesHospede menuHospede() {
        // Cria um array com as opções
        OpcoesHospede[] opcoes = { OpcoesHospede.LISTAR_CONSUMO, OpcoesHospede.RELATORIO_ESTADIA, OpcoesHospede.SAIR };

        // Mostra um JOptionPane com as opções

        return (OpcoesHospede) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Hospede",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

}

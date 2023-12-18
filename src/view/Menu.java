package view;

import java.util.List;

import javax.swing.*;
import controller.opcoes.OpcoesAdmin;
import controller.opcoes.OpcoesFuncionario;
import controller.opcoes.OpcoesHospedagem;
import controller.opcoes.OpcoesHospede;
import controller.opcoes.OpcoesRelatorios;
import model.alojamento.Hospedagem;

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
     * Apresenta um menu de opções para interação com funcionalidades de funcionário.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesFuncionario}.
     *         Pode ser {@link OpcoesFuncionario#OPCOES_HOSPEDAGEM}, {@link OpcoesFuncionario#RELATORIOS},
     *         ou {@link OpcoesFuncionario#SAIR}.
     */
    public static OpcoesFuncionario menuFuncionario() {

        OpcoesFuncionario[] opcoes = { OpcoesFuncionario.OPCOES_HOSPEDAGEM, OpcoesFuncionario.RELATORIOS, OpcoesFuncionario.SAIR };

        return (OpcoesFuncionario) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Funcionario", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }


    /**
     * Apresenta um menu de opções para interação com informações do hóspede.
     *
     * @return A opção escolhida pelo usuário do tipo {@link OpcoesHospede}.
     *         Pode ser {@link OpcoesHospede#RELATORIO_CONSUMO}, {@link OpcoesHospede#RELATORIO_ESTADIA},
     *         ou {@link OpcoesHospede#SAIR}.
     */
    public static OpcoesHospede menuHospede() {

        OpcoesHospede[] opcoes = { OpcoesHospede.RELATORIO_CONSUMO, OpcoesHospede.RELATORIO_ESTADIA,OpcoesHospede.RELATORIO_PAGAMENTO_FATURADO, OpcoesHospede.SAIR };

        return (OpcoesHospede) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Hospede", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    public static OpcoesRelatorios menuRelatorioGerais(){

        OpcoesRelatorios[] opcoes = { OpcoesRelatorios.RELATORIO_HOSPEDES, OpcoesRelatorios.RELATORIO_RESERVAS_HOJE, OpcoesRelatorios.RELATORIO_ACOMODACOES, OpcoesRelatorios.RELATORIO_FATURAMENTO, OpcoesRelatorios.RELATORIO_ATRASADOS, OpcoesRelatorios.SAIR };

        return (OpcoesRelatorios) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Relatorios", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    public static OpcoesHospedagem menuHospedagem(){
            
            OpcoesHospedagem[] opcoes = { OpcoesHospedagem.CADASTRAR_HOSPEDAGEM, OpcoesHospedagem.REMOVER_HOSPEDAGEM, OpcoesHospedagem.CADASTRAR_ACOMODACAO, OpcoesHospedagem.ADICIONAR_ACOMPANHANTE, OpcoesHospedagem.SAIR };
    
            return (OpcoesHospedagem) JOptionPane.showInputDialog(null, "Escolha uma opção", "Menu Hospedagem", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    public static Hospedagem escolherModificado(List<Hospedagem> hospedagens)
    {
        // Instancia um novo StringBuilder para armazenar os códigos das hospedagens
        StringBuilder codigos = new StringBuilder();


        // Adiciona os códigos das hospedagens ao StringBuilder
        for (Hospedagem hospedagem : hospedagens) {
            codigos.append(hospedagem.getCodigo()).append("\n");
        }

        // Exibe um diálogo para o usuário escolher o código da hospedagem
        String codigoEscolhido = JOptionPane.showInputDialog(null, "Digite o código da hospedagem:\n" + codigos.toString(), "Escolher Hospedagem", JOptionPane.PLAIN_MESSAGE);
        
        // Retorna a hospedagem escolhida
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.getCodigo() == Integer.parseInt(codigoEscolhido)) {
                return hospedagem;
            }
        }
        return null;
    }


}

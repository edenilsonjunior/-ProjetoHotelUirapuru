package view;

import java.util.List;

import javax.swing.*;
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
        JPasswordField campoSenha = new JPasswordField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        addComponente(painel, "Login:", campoLogin);
        addComponente(painel, "Senha:", campoSenha);

        int result = criarTela(painel, "Login");

        if (result == JOptionPane.OK_OPTION) {
            String login = campoLogin.getText();
            String senha = new String(campoSenha.getPassword());

            if (login.isEmpty() || senha.isEmpty()) 
            {
                mensagemErro("Os campos de login e senha não podem estar vazios. Tente novamente.", "Erro");
                return menuLogin(); // Chamada recursiva para exibir a tela novamente 
            } 
            else {
                return new String[] {login, senha};
            }
        } else {
            return null;
        }
    }

    
    /**
     * Cria um menu genérico com opções baseadas em uma enumeração.
     * T extends Enum<T> significa que o tipo T deve ser um enum.
     * Esse método tem o objetivo de lidar com todos os menus que possuem opções baseadas em enumerações.
     * 
     * @param <T>       o tipo de enumeração
     * @param tipoEnum  a classe da enumeração
     * @param titulo    o título do menu
     * @return          a opção selecionada pelo usuário
     */
    public static <T extends Enum<T>> T menuGenerico(Class<T> tipoEnum, String titulo) {
        
        return menuOpcoes(
            tipoEnum, 
            tipoEnum.getEnumConstants(),
            titulo);
    }


    /**
     *  Exibe uma lista de todas as hospedagens e retorna a hospedagem escolhida pelo usuário.
     * 
     * @param hospedagens Lista de hospedagens
     * @return            A hospedagem escolhida pelo usuário
     */
    public static Hospedagem escolherModificado(List<Hospedagem> hospedagens){

        String codigos = "";
        for (Hospedagem hospedagem : hospedagens) {

            int numero = hospedagem.getAcomodacao().getNumeroQuarto();
            codigos += "Quarto " + numero + ": " + hospedagem.getCodigo() + "\n";
        }

        String escolhido = telaEscolherHospedagem(codigos);
        
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.getCodigo() == Integer.parseInt(escolhido)) {
                return hospedagem;
            }
        }
        return null;
    }


    /**
     * Exibe uma lista de todas as reservas e retorna a reserva escolhida pelo usuário.
     * @param hospedagens Lista de reservas
     * @return            A reserva escolhida pelo usuário    
     */
    public static Hospedagem escolherCheckIn(List<Hospedagem> hospedagens){

        String codigos = "";
        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.isStatus() == false) {

                int numero = hospedagem.getAcomodacao().getNumeroQuarto();
                codigos += "Quarto " + numero + ": " + hospedagem.getCodigo() + "\n";
            }
        }

        String escolhido = telaEscolherHospedagem(codigos);

        for (Hospedagem hospedagem : hospedagens) {
            if (hospedagem.getCodigo() == Integer.parseInt(escolhido)) {
                return hospedagem;
            }
        }
        return null;
    }


    /**
     * Adiciona um componente ao painel passado como parâmetro.
     * @param painel O painel onde o componente será adicionado.
     * @param titulo O título do componente.
     * @param campo O componente a ser adicionado.
     */
    private static void addComponente(JPanel painel, String titulo, JComponent campo) {
        painel.add(new JLabel(titulo));
        painel.add(campo);
    }


    /**
     * Cria uma tela com o painel passado como parâmetro.
     * @param painel O painel a ser exibido na tela.
     * @param titulo O título da tela.
     * @return O resultado da tela.
     */
    private static int criarTela(JPanel painel, String titulo) {
        return JOptionPane.showConfirmDialog(null, painel, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * Exibe uma mensagem de erro.
     * @param mensagem A mensagem de erro.
     * @param titulo O título da mensagem de erro.
     */
    private static void mensagemErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Exibe um diálogo de confirmação com a mensagem "Deseja realmente sair?".
     * @param <T> O tipo do array de opções.
     * @param tipo O tipo da classe do array de opções.
     * @param opcoes O array de opções.
     * @param titulo O título do diálogo.
     * 
     * @return A opção escolhida pelo usuário.
     */
    private static <T> T menuOpcoes(Class<T> tipo, T[] opcoes, String titulo) {
        Object escolha = JOptionPane.showInputDialog(
                                                    null, 
                                                    "Escolha uma opção", 
                                                    titulo, 
                                                    JOptionPane.QUESTION_MESSAGE, 
                                                    null, 
                                                    opcoes, 
                                                    opcoes[0]);
        return tipo.cast(escolha);
    }

    private static String telaEscolherHospedagem(String codigos) {
        return JOptionPane.showInputDialog(
                                            null, 
                                            "Digite o código da hospedagem:\n" + codigos, 
                                            "Escolher Hospedagem", 
                                            JOptionPane.PLAIN_MESSAGE);
    }
}
    
package view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.alojamento.Acomodacao;
import model.alojamento.Hospedagem;
import model.alojamento.TipoAcomodacao;
import model.hotel.Hotel;
import model.pessoa.Funcionario;
import model.pessoa.Hospede;

/**
 * Essa classe contem todos as telas de modificação
 */
public class Modificar {

    /**
     * Remove um funcionário do hotel com base no código fornecido pelo usuário.
     *
     * @param hotel O objeto {@link Hotel} contendo a lista de funcionários.
     * @return Um objeto {@link Funcionario} representando o funcionário removido.
     *         Retorna null se o usuário cancelar a remoção ou se o funcionário não for encontrado.
     */
    public static Funcionario removerFuncionario(Hotel hotel) {

        JTextField campoCodigo = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            for (Funcionario funcionario : hotel.getFuncionarios()) {
                if (funcionario.getCodigo() == Integer.parseInt(campoCodigo.getText())) {
                    return funcionario;
                }
            }
        }
        return null;
    }

    /**
     * Remove uma hospedagem do hotel com base no código informado.
     *
     * @param hotel O objeto {@link Hotel} contendo as informações das hospedagens.
     * @return A {@link Hospedagem} removida, ou null se não encontrada.
     */
    public static Hospedagem removerHospedagem(Hotel hotel) {

        JTextField campoCodigo = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            for (Hospedagem hospedagem : hotel.getHospedagens()) {
                if (hospedagem.getCodigo() == Integer.parseInt(campoCodigo.getText())) {
                    return hospedagem;
                }
            }
        }
        return null;
    }


    /**
     * Realiza o cadastro de uma nova acomodação solicitando informações ao usuário.
     *
     * @return Um objeto {@link Acomodacao} representando a nova acomodação cadastrada.
     *         Retorna null se o usuário cancelar o cadastro.
     */
    public static Acomodacao cadastrarAcomodacao() {

        JTextField campoCodigo = new JTextField(10);
        JComboBox<TipoAcomodacao> campoOpcao = new JComboBox<>(TipoAcomodacao.values());
        JTextField campoDiaria = new JTextField(10);
        JTextField campoMaxAdultos = new JTextField(10);
        JTextField campoMaxCriancas = new JTextField(10);
        JTextField campoAndar = new JTextField(10);
        JTextField campoNumeroQuarto = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);
        painel.add(new JLabel("Tipo do quarto:"));
        painel.add(campoOpcao);
        painel.add(new JLabel("Diaria:"));
        painel.add(campoDiaria);
        painel.add(new JLabel("Maximo de adultos:"));
        painel.add(campoMaxAdultos);
        painel.add(new JLabel("Maximo de criancas:"));
        painel.add(campoMaxCriancas);
        painel.add(new JLabel("Andar:"));
        painel.add(campoAndar);
        painel.add(new JLabel("Numero do quarto:"));
        painel.add(campoNumeroQuarto);

        // Mostra o painel em um JOptionPane
        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new Acomodacao(
                    Integer.parseInt(campoCodigo.getText()),
                    (TipoAcomodacao) campoOpcao.getSelectedItem(),
                    Double.parseDouble(campoDiaria.getText()),
                    Integer.parseInt(campoMaxAdultos.getText()),
                    Integer.parseInt(campoMaxCriancas.getText()),
                    Integer.parseInt(campoAndar.getText()),
                    Integer.parseInt(campoNumeroQuarto.getText()));
        }
        return null;
    }

   /**
     * Realiza o cadastro de um novo hóspede solicitando informações ao usuário.
     *
     * @return Um objeto {@link Hospede} representando o novo hóspede cadastrado.
     *         Retorna null se o usuário cancelar o cadastro.
     */
    public static Hospede cadastrarHospede() {

        JTextField campoNome = new JTextField(10);
        JTextField campoEndereco = new JTextField(10);
        JTextField campoCidade = new JTextField(10);
        JTextField campoEstado = new JTextField(10);
        JTextField campoTelefone = new JTextField(10);
        JTextField campoDataNascimento = new JTextField(10);
        JTextField campoPais = new JTextField(10);
        JTextField campoEmail = new JTextField(10);
        JTextField campoIdentificacao = new JTextField(10);
        JTextField campoNomePai = new JTextField(10);
        JTextField campoNomeMae = new JTextField(10);
        JTextField campoDadosCartao = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Endereco:"));
        painel.add(campoEndereco);
        painel.add(new JLabel("Cidade:"));
        painel.add(campoCidade);
        painel.add(new JLabel("Estado:"));
        painel.add(campoEstado);
        painel.add(new JLabel("Telefone:"));
        painel.add(campoTelefone);
        painel.add(new JLabel("Data de Nascimento:"));
        painel.add(campoDataNascimento);
        painel.add(new JLabel("Pais:"));
        painel.add(campoPais);
        painel.add(new JLabel("Email:"));
        painel.add(campoEmail);
        painel.add(new JLabel("Identificacao:"));
        painel.add(campoIdentificacao);
        painel.add(new JLabel("Nome do Pai:"));
        painel.add(campoNomePai);
        painel.add(new JLabel("Nome da Mae:"));
        painel.add(campoNomeMae);
        painel.add(new JLabel("Dados do Cartao:"));
        painel.add(campoDadosCartao);

        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            return new Hospede(
                    campoPais.getText(),
                    campoEmail.getText(),
                    Integer.parseInt(campoIdentificacao.getText()),
                    campoNomePai.getText(), campoNomeMae.getText(),
                    Integer.parseInt(campoDadosCartao.getText()),
                    campoNome.getText(), campoEndereco.getText(),
                    campoCidade.getText(), campoEstado.getText(),
                    Integer.parseInt(campoTelefone.getText()),
                    campoDataNascimento.getText());
        }
        return null;
    }

    /**
     * Realiza o cadastro de um novo funcionário solicitando informações ao usuário.
     *
     * @return Um objeto {@link Funcionario} representando o novo funcionário cadastrado.
     *         Retorna null se o usuário cancelar o cadastro.
     */
    public static Funcionario CadastrarFuncionario() {

        JTextField campoNome = new JTextField(10);
        JTextField campoEndereco = new JTextField(10);
        JTextField campoCidade = new JTextField(10);
        JTextField campoEstado = new JTextField(10);
        JTextField campoTelefone = new JTextField(10);
        JTextField campoDataNascimento = new JTextField(10);
        JTextField campoCodigo = new JTextField(10);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Endereco:"));
        painel.add(campoEndereco);
        painel.add(new JLabel("Cidade:"));
        painel.add(campoCidade);
        painel.add(new JLabel("Estado:"));
        painel.add(campoEstado);
        painel.add(new JLabel("Telefone:"));
        painel.add(campoTelefone);
        painel.add(new JLabel("Data de Nascimento:"));
        painel.add(campoDataNascimento);
        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        int result = JOptionPane.showConfirmDialog(null, painel,
                "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

                return new Funcionario(
                        Integer.parseInt(campoCodigo.getText()),
                        campoNome.getText(), campoEndereco.getText(),
                        campoCidade.getText(), campoEstado.getText(),
                        Integer.parseInt(campoTelefone.getText()),
                        campoDataNascimento.getText());
        }
        return null;
    }

        public static Hospedagem cadastrarHospedagem(List<Acomodacao> acomodacoes) {
            // TODO: Implementar (letra G)
        return null;
    }

}


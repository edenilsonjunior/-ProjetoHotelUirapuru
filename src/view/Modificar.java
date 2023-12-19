package view;

import com.toedter.calendar.JDateChooser;
import java.time.*;
import javax.swing.*;

import model.alojamento.*;
import model.consumo.Consumo;
import model.consumo.TipoConsumo;
import model.hotel.*;
import model.pagamento.*;
import model.pessoa.*;

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
        
        JPanel painel = criarPainel();
        addComponente(painel, "Codigo:", campoCodigo);

        int result = criarTela(painel, "Digite o codigo do funcionario");

        if (result == JOptionPane.OK_OPTION) {
            for (Funcionario f : hotel.getFuncionarios()) {

                int codigo = Integer.parseInt(campoCodigo.getText());
                if (f.getCodigo() == codigo) {
                    return f;
                }
            }
        }
        return null;
    }

    
    /**
     * Metodo que cadastra uma nova Hospedagem.
     * @param hotel O objeto {@link Hotel} contendo as informações das hospedagens.
     * @return      A {@link Hospedagem} que será cadastrada, ou null se não encontrada.
     */
    public static Hospedagem cadastrarHospedagem(Hotel hotel) {

        JComboBox<TipoAcomodacao> campoOpcao = new JComboBox<>(TipoAcomodacao.values());
        JComboBox<TipoPagamento> campoPagamento = new JComboBox<>(TipoPagamento.values());
        JComboBox<String> campoStatus = new JComboBox<>(new String[]{"RESERVA", "HOSPEDAGEM"});
        
        JDateChooser campoChegada = new JDateChooser();
        JDateChooser campoSaida = new JDateChooser();
        JTextField campoCodigo = new JTextField(10);
        

        JPanel painel = criarPainel();

        addComponente(painel, "Tipo do quarto:", campoOpcao);
        addComponente(painel, "Chegada:", campoChegada);
        addComponente(painel, "Saída:", campoSaida);
        addComponente(painel, "Tipo de pagamento:", campoPagamento);
        addComponente(painel, "Código:", campoCodigo);
        addComponente(painel, "Status:", campoStatus);


        int result = criarTela(painel, "Cadastro de Hospedagem");
        
        if (result == JOptionPane.OK_OPTION) {

            Hospede hospede = cadastrarHospede();
            TipoAcomodacao tipoAcomodacao = (TipoAcomodacao) campoOpcao.getSelectedItem();
            TipoPagamento tipoPagamento = (TipoPagamento) campoPagamento.getSelectedItem();

            boolean status = campoStatus.getSelectedItem().equals("RESERVA") ? false : true;
            LocalDate chegada = Instant.ofEpochMilli(campoChegada.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate saida = Instant.ofEpochMilli(campoSaida.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

            int codigo = Integer.parseInt(campoCodigo.getText());
            Acomodacao escolhida = null;
            
            // Procura uma acomodação disponível do tipo escolhido e marca como ocupada
            for (Acomodacao acomodacao : hotel.getAcomodacoes()) {
                if (acomodacao.getOpcao().equals(tipoAcomodacao) && acomodacao.isOcupado() == false) {

                    escolhida = acomodacao;
                    acomodacao.setOcupado(true);
                    break;
                }
            }

            if (escolhida != null && hospede != null) {
                return new Hospedagem(escolhida, hospede, chegada, saida, tipoPagamento, codigo, status);
            }
        }
        return null;
    }


    /**
     * Metódo que retorna uma acomodacao para ser removida.
     *
     * @param hotel O objeto {@link Hotel} contendo as informações das hospedagens.
     * @return A {@link Hospedagem} que será removida, ou null se não encontrada.
     */
    public static Hospedagem removerHospedagem(Hotel hotel) {

        JTextField campoCodigo = new JTextField(10);

        JPanel painel = criarPainel();
        addComponente(painel, "Codigo:", campoCodigo);


        int result = criarTela(painel, "Digite o codigo da Hospedagem");

        if (result == JOptionPane.OK_OPTION) {
            for (Hospedagem hospedagem : hotel.getHospedagens()) {

                int codigo = Integer.parseInt(campoCodigo.getText());
                if (hospedagem.getCodigo() == codigo) {
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

        JComboBox<TipoAcomodacao> campoOpcao = new JComboBox<>(TipoAcomodacao.values());

        JTextField campoCodigo = new JTextField(10);
        JTextField campoDiaria = new JTextField(10);
        JTextField campoMaxAdultos = new JTextField(10);
        JTextField campoMaxCriancas = new JTextField(10);
        JTextField campoAndar = new JTextField(10);
        JTextField campoNumeroQuarto = new JTextField(10);

        JPanel painel = criarPainel();  

        addComponente(painel, "Codigo:", campoCodigo);
        addComponente(painel, "Tipo do quarto:", campoOpcao);
        addComponente(painel, "Diaria:", campoDiaria);
        addComponente(painel, "Maximo de adultos:", campoMaxAdultos);
        addComponente(painel, "Maximo de criancas:", campoMaxCriancas);
        addComponente(painel, "Andar:", campoAndar);
        addComponente(painel, "Numero do quarto:", campoNumeroQuarto);


        // Mostra o painel em um JOptionPane
        int result = criarTela(painel, "Cadastro de Acomodacao");

        if (result == JOptionPane.OK_OPTION) {

            int codigo = Integer.parseInt(campoCodigo.getText());
            TipoAcomodacao tipoAcomodacao = (TipoAcomodacao) campoOpcao.getSelectedItem();
            double diaria = Double.parseDouble(campoDiaria.getText());
            int maxAdultos = Integer.parseInt(campoMaxAdultos.getText());
            int maxCriancas = Integer.parseInt(campoMaxCriancas.getText());
            int andar = Integer.parseInt(campoAndar.getText());
            int numeroQuarto = Integer.parseInt(campoNumeroQuarto.getText());

            return new Acomodacao(codigo, tipoAcomodacao, diaria, maxAdultos, maxCriancas, andar, numeroQuarto);
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


        JPanel painel = criarPainel();

        addComponente(painel, "Nome:", campoNome);
        addComponente(painel, "Endereco:", campoEndereco);
        addComponente(painel, "Cidade:", campoCidade);
        addComponente(painel, "Estado:", campoEstado);
        addComponente(painel, "Telefone:", campoTelefone);
        addComponente(painel, "Data de Nascimento:", campoDataNascimento);
        addComponente(painel, "Pais:", campoPais);
        addComponente(painel, "Email:", campoEmail);
        addComponente(painel, "Identificacao:", campoIdentificacao);
        addComponente(painel, "Nome do Pai:", campoNomePai);
        addComponente(painel, "Nome da Mae:", campoNomeMae);
        addComponente(painel, "Dados do Cartao:", campoDadosCartao);

        int result = criarTela(painel, "Cadastro de Hospede");

        if (result == JOptionPane.OK_OPTION) {

            String pais = campoPais.getText();
            String email = campoEmail.getText();
            int identificacao = Integer.parseInt(campoIdentificacao.getText());

            String nomePai = campoNomePai.getText();
            String nomeMae = campoNomeMae.getText();
            int dadosCartao = Integer.parseInt(campoDadosCartao.getText());

            String nome = campoNome.getText(); 
            String endereco = campoEndereco.getText();
            String cidade = campoCidade.getText();

            String estado = campoEstado.getText();
            int telefone = Integer.parseInt(campoTelefone.getText());
            String dataNascimento = campoDataNascimento.getText();

            return new Hospede(pais, email, identificacao, nomePai, nomeMae, dadosCartao, nome, endereco, cidade, estado, telefone, dataNascimento);
                
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

        JPanel painel = criarPainel();

        addComponente(painel, "Nome:", campoNome);
        addComponente(painel, "Endereco:", campoEndereco);
        addComponente(painel, "Cidade:", campoCidade);
        addComponente(painel, "Estado:", campoEstado);
        addComponente(painel, "Telefone:", campoTelefone);
        addComponente(painel, "Data de Nascimento:", campoDataNascimento);
        addComponente(painel, "Codigo:", campoCodigo);


        int result = criarTela(painel, "Cadastro de Funcionario");
       
        if (result == JOptionPane.OK_OPTION) {

            int codigo = Integer.parseInt(campoCodigo.getText());
            String nome = campoNome.getText();
            String endereco = campoEndereco.getText();
            String cidade = campoCidade.getText();
            String estado = campoEstado.getText();
            int telefone = Integer.parseInt(campoTelefone.getText());
            String dataNascimento = campoDataNascimento.getText();

            return new Funcionario(codigo, nome, endereco, cidade, estado, telefone, dataNascimento);
        }
        return null;
    }


    /**
     * Realiza o cadastro de um novo acompanhante solicitando informações ao usuário.
     * @return Um objeto {@link Acompanhante} representando o novo acompanhante cadastrado.
     */
    public static Acompanhante cadastrarAcompanhante(){
        
        JTextField campoNome = new JTextField(10);
        JTextField campoIdade = new JTextField(10);
        
        JPanel painel = criarPainel();

        addComponente(painel, "Nome:", campoNome);
        addComponente(painel, "Idade:", campoIdade);

        
        int result = criarTela(painel, "Cadastro de Acompanhante");
       
        if (result == JOptionPane.OK_OPTION) {
            
            String nome = campoNome.getText();
            int idade = Integer.parseInt(campoIdade.getText());
            
            return new Acompanhante(nome, idade);
        }
        return null;
    }


    /**
     * Exibe uma lista de todas as acomodações e retorna a acomodação escolhida pelo usuário.
     * @return A acomodação escolhida pelo usuário.
     */
    public static LocalDate[] escolherData(){
        
        JDateChooser campoInicio = new JDateChooser();
        JDateChooser campoFim = new JDateChooser();

        JPanel painel = criarPainel();

        addComponente(painel, "Inicio:", campoInicio);
        addComponente(painel, "Fim:", campoFim);

        
        int result = criarTela(painel, "Escolha as datas");

        if (result == JOptionPane.OK_OPTION) {
            
            // Converte a data para LocalDate
            LocalDate inicio = Instant.ofEpochMilli(campoInicio.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fim = Instant.ofEpochMilli(campoFim.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            
            return new LocalDate[]{ inicio, fim };
        }
        return null;
    }


    /**
     * Realiza o cadastro de um novo consumo solicitando informações ao usuário.
     * @return Um objeto {@link Consumo} representando o novo consumo cadastrado.
     */
    public static Consumo cadastrarConsumo(){
            
            JComboBox<TipoConsumo> campoOpcao = new JComboBox<>(TipoConsumo.values());

            JTextField campoCodigo = new JTextField(10);
            JTextField campoDescricao = new JTextField(10);
            
            JPanel painel = criarPainel();

            addComponente(painel, "Codigo:", campoCodigo);
            addComponente(painel, "Tipo do consumo:", campoOpcao);
            addComponente(painel, "Descricao:", campoDescricao);


            int result = criarTela(painel, "Cadastro de Consumo");

            if (result == JOptionPane.OK_OPTION) {
                
                int codigo = Integer.parseInt(campoCodigo.getText());
                TipoConsumo tipo = (TipoConsumo) campoOpcao.getSelectedItem();
                String descricao = campoDescricao.getText();
                
                return new Consumo(codigo, tipo, descricao);
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
     * Cria um painel com layout vertical.
     * @return O painel criado.
     */
    private static JPanel criarPainel() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        return painel;
    }


}



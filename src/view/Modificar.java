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


        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));


        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);


        int result = JOptionPane.showConfirmDialog(null, painel,"Digite o codigo do funcionario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) 
        {
            for (Funcionario f : hotel.getFuncionarios()) 
            {
                int codigo = Integer.parseInt(campoCodigo.getText());

                if (f.getCodigo() == codigo) {
                    return f;
                }
            }
        }
        return null;
    }

    
    public static Hospedagem cadastrarHospedagem(Hotel hotel) {

        JComboBox<TipoAcomodacao> campoOpcao = new JComboBox<>(TipoAcomodacao.values());
        JComboBox<TipoPagamento> campoPagamento = new JComboBox<>(TipoPagamento.values());
        JComboBox<String> campoStatus = new JComboBox<>(new String[]{"RESERVA", "HOSPEDAGEM"});
        
        JDateChooser campoChegada = new JDateChooser();
        JDateChooser campoSaida = new JDateChooser();
        
        JTextField campoCodigo = new JTextField(10);
        

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Tipo do quarto:"));
        painel.add(campoOpcao);
        painel.add(new JLabel("Chegada:"));
        painel.add(campoChegada);
        painel.add(new JLabel("Saída:"));
        painel.add(campoSaida);
        painel.add(new JLabel("Tipo de pagamento:"));
        painel.add(campoPagamento);
        painel.add(new JLabel("Código:"));
        painel.add(campoCodigo);
        painel.add(new JLabel("Status:"));
        painel.add(campoStatus);


        int result = JOptionPane.showConfirmDialog(null, painel,"Cadastro de Hospedagem", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {

            Hospede hospede = cadastrarHospede();
            TipoAcomodacao tipoAcomodacao = (TipoAcomodacao) campoOpcao.getSelectedItem();
            TipoPagamento tipoPagamento = (TipoPagamento) campoPagamento.getSelectedItem();

            boolean status = campoStatus.getSelectedItem().equals("RESERVA") ? false : true;
            LocalDate chegada = Instant.ofEpochMilli(campoChegada.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate saida = Instant.ofEpochMilli(campoSaida.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

            int codigo = Integer.parseInt(campoCodigo.getText());
            Acomodacao escolhida = null;
            
            
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

        
        int result = JOptionPane.showConfirmDialog(null, painel, "Digite o codigo da Hospedagem", JOptionPane.OK_CANCEL_OPTION);

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
        int result = JOptionPane.showConfirmDialog(null, painel, "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);

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


        int result = JOptionPane.showConfirmDialog(null, painel, "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);

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


        int result = JOptionPane.showConfirmDialog(null, painel, "Por favor, preencha todos os campos", JOptionPane.OK_CANCEL_OPTION);
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


    public static Acompanhante cadastrarAcompanhante(){
        
        JTextField campoNome = new JTextField(10);
        JTextField campoIdade = new JTextField(10);
        
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Idade:"));
        painel.add(campoIdade);
        
        int result = JOptionPane.showConfirmDialog(null, painel, "Digite os dados do acompanhante", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            
            String nome = campoNome.getText();
            int idade = Integer.parseInt(campoIdade.getText());
            
            return new Acompanhante(nome, idade);
        }
        return null;
    }


    public static LocalDate[] escolherData(){
        
        JDateChooser campoInicio = new JDateChooser();
        JDateChooser campoFim = new JDateChooser();


        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));


        painel.add(new JLabel("Inicio:"));
        painel.add(campoInicio);
        painel.add(new JLabel("Fim:"));
        painel.add(campoFim);


        int result = JOptionPane.showConfirmDialog(null, painel, "Escolha as datas", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            
            LocalDate inicio = Instant.ofEpochMilli(campoInicio.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fim = Instant.ofEpochMilli(campoFim.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            
            return new LocalDate[]{ inicio, fim };
            
        }
        return null;
    }


    public static Consumo cadastrarConsumo(){
            
            JComboBox<TipoConsumo> campoOpcao = new JComboBox<>(TipoConsumo.values());
            JTextField campoCodigo = new JTextField(10);
            JTextField campoDescricao = new JTextField(10);
            
            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            
            painel.add(new JLabel("Codigo:"));
            painel.add(campoCodigo);
            painel.add(new JLabel("Tipo do consumo:"));
            painel.add(campoOpcao);
            painel.add(new JLabel("Descricao:"));
            painel.add(campoDescricao);
            
            int result = JOptionPane.showConfirmDialog(null, painel, "Digite os dados do consumo", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                
                int codigo = Integer.parseInt(campoCodigo.getText());
                TipoConsumo tipo = (TipoConsumo) campoOpcao.getSelectedItem();
                String descricao = campoDescricao.getText();
                
                return new Consumo(codigo, tipo, descricao);
            }
            return null;
    }

}



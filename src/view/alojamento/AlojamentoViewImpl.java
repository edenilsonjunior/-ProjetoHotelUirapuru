package view.alojamento;

import java.time.*;
import java.util.*;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import dto.*;
import view.utils.UtilsViewImpl;

public class AlojamentoViewImpl implements IAlojamentoView{

    private String title;

    public AlojamentoViewImpl() {
        this.title = "Alojamento";
    }

    @Override
    public String menuHospedagem(String[] opcoes) {
        StringBuilder sb = new StringBuilder();

        sb.append("Escolha uma opção: \n");

        for (int i = 0; i < opcoes.length; i++) {
            sb.append(i + 1);
            sb.append(" - ");
            sb.append(opcoes[i]);
            sb.append("\n");
        }

        return JOptionPane.showInputDialog(null, sb.toString(), this.title, JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public AcompanhanteDTO adicionarAcompanhante() {

        JTextField campoNome = new JTextField(20);
        JTextField campoIdade = new JTextField(20);
        
        JPanel painel = UtilsViewImpl.criarPainel();

        UtilsViewImpl.addComponente(painel, "Nome:", campoNome);
        UtilsViewImpl.addComponente(painel, "Idade:", campoIdade);

        if (UtilsViewImpl.criarTela(painel, "Cadastro de Acompanhante") == JOptionPane.OK_OPTION) {

            int idade;

            try {
                idade = Integer.parseInt(campoIdade.getText());
                
            } catch (Exception e) {
                UtilsViewImpl.mensagem("O campo idade ser um numero inteiro.", "Erro");
                return adicionarAcompanhante();
            }
            
            String nome = campoNome.getText();
            
            return new AcompanhanteDTO(nome, idade);
        }
        return null;
    }

    @Override
    public HospedagemDTO cadastrarHospedagem(String[] tiposAcomodacao, String[] tiposPagamento) {
        
        Map<String, JComponent> campos = new HashMap<>();
        
        campos.put("Tipo do quarto", new JComboBox<>(tiposAcomodacao));
        campos.put("Chegada", new JDateChooser());

        campos.put("Saida", new JDateChooser());
        campos.put("Tipo de pagamento", new JComboBox<>(tiposPagamento));

        campos.put("Codigo", new JTextField(20));
        campos.put("Status", new JComboBox<>(new String[]{"RESERVA", "HOSPEDAGEM"}));
        
        JPanel painel = UtilsViewImpl.criarPainel();
        UtilsViewImpl.addComponentes(painel, campos);


        // public HospedagemDTO(AcomodacaoDTO acomodacao, HospedeDTO hospede, LocalDate chegada, LocalDate saida, String tipoPagamento, int codigo, boolean status)
        if (UtilsViewImpl.criarTela(painel, "Cadastro de Hospedagem") == JOptionPane.OK_OPTION) {

            // TODO
        }
        return null;
    }

    @Override
    public HospedeDTO cadastrarHospede() {

        JPanel painel = UtilsViewImpl.criarPainel();
        Map<String, JComponent> campos = new HashMap<>();

        campos.put("Nome", new JTextField(20));
        campos.put("Endereco", new JTextField(20));
        campos.put("Cidade", new JTextField(20));
        campos.put("Estado", new JTextField(20));
        campos.put("Telefone", new JTextField(20));
        campos.put("Data de Nascimento", new JDateChooser());
        campos.put("Pais", new JTextField(20));
        campos.put("Email", new JTextField(20));
        campos.put("Identificacao", new JTextField(20));
        campos.put("Nome do Pai", new JTextField(20));
        campos.put("Nome da Mae", new JTextField(20));
        campos.put("Dados do Cartao", new JTextField(20));

        UtilsViewImpl.addComponentes(painel, campos);

        if (UtilsViewImpl.criarTela(painel, "Cadastro de Hospede") == JOptionPane.OK_OPTION) {

            int identificacao, dadosCartao;
            LocalDate dataNascimento;

            try {

                identificacao = Integer.parseInt(((JTextField) campos.get("Identificacao")).getText());
                dadosCartao = Integer.parseInt(((JTextField) campos.get("Dados do Cartao")).getText());
                dataNascimento = Instant.ofEpochMilli(((JDateChooser) campos.get("Data de Nascimento")).getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                
            } catch (Exception e) {

                StringBuilder sb = new StringBuilder();
                sb.append("Os campos devem ser: \n");
                sb.append("Identificacao: inteiro \n");
                sb.append("Dados do Cartao: inteiro \n");
                sb.append("Data de nasimento: data \n");
                
                UtilsViewImpl.mensagem(sb.toString(), "Erro");
                return cadastrarHospede();
            }

            return new HospedeDTO(
                ((JTextField) campos.get("Pais")).getText(),
                ((JTextField) campos.get("Email")).getText(),
                identificacao,
                ((JTextField) campos.get("Nome do Pai")).getText(),
                ((JTextField) campos.get("Nome da Mae")).getText(),
                dadosCartao,
                ((JTextField) campos.get("Nome")).getText(),
                ((JTextField) campos.get("Endereco")).getText(),
                ((JTextField) campos.get("Cidade")).getText(),
                ((JTextField) campos.get("Estado")).getText(),
                ((JTextField) campos.get("Telefone")).getText(),
                dataNascimento
            );
        }
        return null;
    }


    @Override
    public ConsumoDTO cadastrarConsumo(String[] tiposConsumo) {
        
            JPanel painel = UtilsViewImpl.criarPainel();  

            Map<String, JComponent> campos = new HashMap<>();
            campos.put("Codigo", new JTextField(20));
            campos.put("Tipo do consumo", new JComboBox<>(tiposConsumo));
            campos.put("Descricao", new JTextField(20));
    
            UtilsViewImpl.addComponentes(painel, campos);

            if (UtilsViewImpl.criarTela(painel, "Cadastro de Consumo") == JOptionPane.OK_OPTION) {

                int codigo;
                try {
                    codigo = Integer.parseInt(((JTextField) campos.get("Codigo")).getText());
                    
                } catch (Exception e) {
                    UtilsViewImpl.mensagem("O campo codigo ser um numero inteiro.", "Erro");
                    return cadastrarConsumo(tiposConsumo);
                }

                //  public ConsumoDTO(int codigo, String tipo, String descricao, double valor)
                return new ConsumoDTO(
                    codigo,
                    (String) ((JComboBox<?>) campos.get("Tipo do consumo")).getSelectedItem(),
                    ((JTextField) campos.get("Descricao")).getText(),
                    0
                );  
            }
            return null;
    }


    @Override
    public HospedagemDTO escolherHospedagem(String[] hospedagens) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int registrarCheckIn(String[] hospedagens) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void relatorioSaidaHospede(String relatorio) {
        // TODO Auto-generated method stub
    }

    @Override
    public HospedagemDTO removerHospedagem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AcomodacaoDTO cadastrarAcomodacao(String[] tiposAcomodacao, String[] tiposPagamento) {
        // TODO Auto-generated method stub
        return null;
    }
}

package view.admin;

import java.time.*;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import dto.FuncionarioDTO;
import view.utils.UtilsViewImpl;

public class AdminViewImpl implements IAdminView{

    private String title;

    public AdminViewImpl() {
        this.title = "Administrador";
    }

    @Override
    public int menuAdmin(Map<Integer, String> opcoes) {

        StringBuilder sb = new StringBuilder();

        sb.append("Escolha uma opção: \n");

        for (Map.Entry<Integer, String> entry : opcoes.entrySet()) {
            sb.append(entry.getKey() + " - " + entry.getValue() + "\n");
        }

        int escolha = -1;

        try {
            escolha = Integer.parseInt(JOptionPane.showInputDialog(null, sb.toString(), title, JOptionPane.PLAIN_MESSAGE));
        } catch (NumberFormatException e) 
        {
            UtilsViewImpl.mensagem("Digite um valor válido", "Erro");
            return menuAdmin(opcoes);
        }

        return escolha;
    }

    @Override
    public FuncionarioDTO cadastrarFuncionario() {

        Map<String, JComponent> campos = new HashMap<>();

        campos.put("Nome", new JTextField(20));
        campos.put("Endereco", new JTextField(20));
        campos.put("Cidade", new JTextField(20));
        campos.put("Estado", new JTextField(20));
        campos.put("Telefone", new JTextField(20));
        campos.put("Data de Nascimento", new JDateChooser());
        campos.put("Codigo", new JTextField(20));

        JPanel painel = UtilsViewImpl.criarPainel();
        UtilsViewImpl.addComponentes(painel, campos);

        int ok = UtilsViewImpl.criarTela(painel, "Cadastro de Funcionario");

        if (ok == JOptionPane.OK_OPTION) {

            int codigo;
            LocalDate dataNascimento;

            try{
                String codigoStr = ((JTextField)campos.get("Codigo")).getText();
                codigo = Integer.parseInt(codigoStr);

                dataNascimento = Instant.ofEpochMilli(((JDateChooser) campos.get("Data de Nascimento")).getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

            } 
            catch (NumberFormatException e) {
                UtilsViewImpl.mensagem("O código deve ser um número inteiro", "Erro");
                return cadastrarFuncionario();
            }

            return new FuncionarioDTO(
                codigo,
                ((JTextField)campos.get("Nome")).getText(),
                ((JTextField)campos.get("Endereco")).getText(),
                ((JTextField)campos.get("Cidade")).getText(),
                ((JTextField)campos.get("Estado")).getText(),
                ((JTextField)campos.get("Telefone")).getText(),
                dataNascimento
                );
        }
        return null;
    }


    @Override
    public void listarFuncionarios(List<String> funcionarios){
        
        for (String descricao : funcionarios) {
            JOptionPane.showMessageDialog(null, descricao);
        }
    }

    @Override
    public int removerFuncionario() {
                
        JTextField campoCodigo = new JTextField(20);
        
        JPanel painel = UtilsViewImpl.criarPainel();

        painel.add(new JLabel("Codigo:"));
        painel.add(campoCodigo);

        if (UtilsViewImpl.criarTela(painel, "Digite o codigo do funcionario") == JOptionPane.OK_OPTION) {

            try {
                return Integer.parseInt(campoCodigo.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido.");
            }
        }
        return -1;
    }
}



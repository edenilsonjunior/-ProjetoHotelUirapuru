package view.funcionario;

import java.util.List;

import javax.swing.JOptionPane;

public class FuncionarioViewImpl implements IFuncionarioView{

    private String title;

    public FuncionarioViewImpl() {
        this.title = "Menu Funcionário";
    }

    @Override
    public int menuFuncionario(List<String> opcoes) {
        
        StringBuilder sb = new StringBuilder();
        int opcao;

        sb.append("Escolha uma opção: \n");
        for (int i = 0; i < opcoes.size(); i++) {
            sb.append(i + 1);
            sb.append(" - ");
            sb.append(opcoes.get(i));
            sb.append("\n");
        }

        try {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null, sb.toString(), title, JOptionPane.PLAIN_MESSAGE));
        } 
        catch (NumberFormatException e) {
            opcao = -1;
        }

        return opcao;
    }
}

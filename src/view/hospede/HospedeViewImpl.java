package view.hospede;

import javax.swing.*;

public class HospedeViewImpl implements IHospedeView{

    private String title;

    public HospedeViewImpl() {
        this.title = "Hospede";
    }

    @Override
    public String menuHospede(String[] opcoes) {

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
}

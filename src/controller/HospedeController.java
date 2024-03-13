package controller;

import model.alojamento.Hospedagem;
import view.hospede.IHospedeView;
import view.utils.UtilsViewImpl;

public class HospedeController {
    
    private IHospedeView hospedeView;

    public HospedeController(IHospedeView hospedeView) {
        
        this.hospedeView = hospedeView;
    }

    /**
     * Método responsável por executar as opções disponíveis para os hóspedes.
     */
    public void funcoesHospede(Hospedagem atual) {
        
        String escolha;
 
        String[] opcoes = new String[] {"Relatório de Consumo", "Relatório de Estadia", "Relatório de Pagamento Faturado", "Fazer Pagamento", "Sair"};

        do {
            escolha = hospedeView.menuHospede(opcoes);

            if (escolha == null) {
                break;
            }

            if (escolha.equals(opcoes[0])) 
                UtilsViewImpl.mensagem(atual.getRelatorioConsumo(), "Relatório de Consumo");
            else if(escolha.equals(opcoes[1]))
                UtilsViewImpl.mensagem(atual.getDescricaoHospede(), "Relatório de Estadia");           
            else if(escolha.equals(opcoes[2]))
                UtilsViewImpl.mensagem(atual.getRelatorioTipoFaturado(), "Relatório de Pagamento Faturado");
            else if(escolha.equals(opcoes[3]))
                UtilsViewImpl.mensagem(atual.getPagamento().fazerPagamento(atual), "Fazer Pagamento");
        } 
        while (!escolha.equals(opcoes[4]));
    }

}

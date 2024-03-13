package controller;

import java.util.ArrayList;
import java.util.List;

import dto.AcomodacaoDTO;
import model.alojamento.Acomodacao;
import model.alojamento.Hospedagem;
import model.alojamento.TipoAcomodacao;
import model.hotel.Hotel;
import model.pessoa.Acompanhante;
import view.alojamento.IAlojamentoView;
import view.funcionario.IFuncionarioView;
import view.utils.UtilsViewImpl;

public class FuncionarioController {

    private IFuncionarioView funcionarioView;
    private IAlojamentoView alojamentoView;
    private Hotel hotel;

    public FuncionarioController(IFuncionarioView funcionarioView, IAlojamentoView alojamentoView) {
        
        this.funcionarioView = funcionarioView;
        this.alojamentoView = alojamentoView;
        this.hotel = Hotel.getInstance();
    }

    public void funcoesFuncionario() {
        
        final int OPCAO_HOSPEDAGEM = 0;
        final int OPCAO_RELATORIOS = 1;
        final int SAIR = 2;
        int escolha;
        
        do {
            escolha = 1; // TODO: funcionarioView.menuFuncionario(opcoes);

            if (escolha == -1) {
                break;
            }

            if (escolha == OPCAO_HOSPEDAGEM){
                funcoesHospedagem();
            }
            else if (escolha == OPCAO_RELATORIOS){
                funcoesRelatorio();
            }
        } 
        while (escolha != SAIR);
    }


    private void funcoesRelatorio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }


    private void funcoesHospedagem(){

        // TODO: Implementar as funções de hospedagem
        String escolha;
        // Hospedagem hospedagemModificada;

        String[] opcoes = new String[] {"Cadastrar Hospedagem", "Remover Hospedagem", "Registrar Check-In", "Cadastrar Acomodação", "Adicionar Acompanhante", "Adicionar Consumo", "Voltar"};
        
        do {
            escolha = alojamentoView.menuHospedagem(opcoes);

            if (escolha == null) break;
            
            switch (escolha) {
                case "Cadastrar Hospedagem":                    
                    break;
                case "Remover Hospedagem":
                    break;
                case "Registrar Check-In":
                    break;
                case "Cadastrar Acomodação":
                    break;
                case "Adicionar Acompanhante":
                    break;
                case "Adicionar Consumo":
                    break;
                default:
                    break;
            }

        } 
        while (!escolha.equals("Voltar"));
    }


    private void verificarDisponibilidade(Hospedagem hospedagem) {

        int maxAdultos = hospedagem.getAcomodacao().getMaxAdultos();
        int maxCriancas = hospedagem.getAcomodacao().getMaxCriancas();

        int adultos = 1;
        int criancas = 0;

        for (Acompanhante a : hospedagem.getHospede().getAcompanhantes()) {
            if (a.getIdade() >= 18) {
                adultos++;
            } else {
                criancas++;
            }
        }

        // Cria um acompanhante e verifica se é adulto ou criança
        Acompanhante acompanhante = alojamentoView.adicionarAcompanhante().toModelDomain();
        if (acompanhante.getIdade() >= 18) {
            adultos++;
        } else {
            criancas++;
        }

        // Verifica se é possível adicionar o acompanhante
        if (adultos <= maxAdultos && criancas <= maxCriancas) {
            hospedagem.getHospede().addAcompanhante(acompanhante);
        } 
        else {
            UtilsViewImpl.mensagem("Não é possível adicionar mais acompanhantes!", "Erro");
        }
    }

    
    public AcomodacaoDTO acharAcomodacaoDisponivel(String tipo){

        List<AcomodacaoDTO> lista = new ArrayList<>();
        for (Acomodacao a : hotel.getAcomodacoes()) {
            lista.add(new AcomodacaoDTO(a));
        }

        for (Acomodacao a : hotel.getAcomodacoes()) {
            if (a.getOpcao().equals(TipoAcomodacao.valueOf(tipo)) && !a.isOcupado()) {
                return new AcomodacaoDTO(a);
            }
        }
        return null;
    }
}

package view.alojamento;

import dto.AcomodacaoDTO;
import dto.AcompanhanteDTO;
import dto.ConsumoDTO;
import dto.HospedagemDTO;
import dto.HospedeDTO;

public interface IAlojamentoView {

    // Menu Hospedagem
    String menuHospedagem(String[] opcoes);

    // Cadastrar Hospedagem
    HospedagemDTO cadastrarHospedagem(String[] tiposAcomodacao, String[] tiposPagamento);

    // Cadastrar Hospede
    HospedeDTO cadastrarHospede();
    
    // Remover Hospedagem
    HospedagemDTO removerHospedagem();
    void relatorioSaidaHospede(String relatorio);

    // Registrar Check In
    int registrarCheckIn(String[] hospedagens);

    // Cadastrar Acomodacao
    AcomodacaoDTO cadastrarAcomodacao(String[] tiposAcomodacao, String[] tiposPagamento);

    // Metodos de adicionar algo
    HospedagemDTO escolherHospedagem(String[] hospedagens);

    // Adicionar Acompanhante
    AcompanhanteDTO adicionarAcompanhante();

    // Adicionar Consumo
    ConsumoDTO cadastrarConsumo(String[] tiposConsumo);
}
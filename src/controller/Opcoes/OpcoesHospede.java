package controller.opcoes;

/**
 * Enumerador de opções para o Hóspede.
 * <p>
 * Este enum representa as possíveis ações que um hóspede pode realizar.
 */
public enum OpcoesHospede {
    /**
     * Opção para visualizar o relatório de consumo.
     */
    RELATORIO_CONSUMO, 

    /**
     * Opção para visualizar o relatório de estadia.
     */
    RELATORIO_ESTADIA, 

    /**
     * Opção para registrar o check-out.
     */
    FAZER_PAGAMENTO,

    /**
     * Opção para visualizar o relatório de pagamento faturado.
     */
    RELATORIO_PAGAMENTO_FATURADO, 

    /**
     * Opção para sair do sistema.
     */
    SAIR;
}
package controller.opcoes;

/**
 * Enumerador de opções para o relatório.
 * <p>
 * Este enum representa as possíveis opções de relatórios que podem ser gerados.
 */
public enum OpcoesRelatorios {
    /**
     * Opção para gerar um relatório de hóspedes.
     */
    RELATORIO_HOSPEDES, 

    /**
     * Opção para gerar um relatório das reservas do dia atual.
     */
    RELATORIO_RESERVAS_HOJE, 

    /**
     * Opção para gerar um relatório de acomodações.
     */
    RELATORIO_ACOMODACOES, 

    /**
     * Opção para gerar um relatório de faturamento.
     */
    RELATORIO_FATURAMENTO, 

    /**
     * Opção para gerar um relatório de atrasados.
     */
    RELATORIO_ATRASADOS,

    /**
     * Opção para voltar ao menu anterior.
     */
    VOLTAR;
}
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.FuncionarioDTO;
import model.hotel.Hotel;
import model.pessoa.Funcionario;
import view.admin.IAdminView;

public class AdminController {
    
    private Hotel hotel;
    private IAdminView adminView;
    private Map<Integer, String> opcoes;

    private final int OPCAO_CADASTRAR = 0;
    private final int OPCAO_LISTAR = 1;
    private final int OPCAO_REMOVER = 2;
    private final int OPCAO_SAIR = 3;

    public AdminController(IAdminView adminView) {
    
        this.hotel = Hotel.getInstance();

        this.adminView = adminView;
        this.opcoes = new HashMap<>();

        opcoes.put(0, "Cadastrar Funcionário");
        opcoes.put(1, "Listar Funcionários");
        opcoes.put(2, "Remover Funcionário");
        opcoes.put(3, "Sair");
    }


    public void funcoesAdmin() {


        int escolha;

        do {
            escolha = adminView.menuAdmin(opcoes);

            if (escolha == -1) break;
            
            switch (escolha) {
                case OPCAO_CADASTRAR:
                    cadastrarFuncionario();
                    break;
                case OPCAO_LISTAR:
                    listarFunc();
                    break;
                case OPCAO_REMOVER:
                    removerFuncionario();
                    break;
            }
        } 
        while (escolha != OPCAO_SAIR);
    }

    private void cadastrarFuncionario() {
        
        FuncionarioDTO novoFunc = adminView.cadastrarFuncionario();
        
        if (novoFunc != null) {
            hotel.addFuncionario(novoFunc.toModelDomain());
        }
    }

    private void removerFuncionario() {

        int escolha = adminView.removerFuncionario();
        Funcionario funcionario = hotel.getFuncionarios().get(escolha);

        if (funcionario != null) {
           hotel.removeFuncionario(funcionario);
        }
    }

    private void listarFunc() {

        List<String> descricao = new ArrayList<>();

        for (Funcionario funcionario : hotel.getFuncionarios()) {
            descricao.add(funcionario.getDescricaoCurta());
        }

        adminView.listarFuncionarios(descricao);
    }

}

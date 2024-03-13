package view.admin;

import java.util.List;
import java.util.Map;

import dto.FuncionarioDTO;

public interface IAdminView {

    int menuAdmin(Map<Integer, String> opcoes);

    FuncionarioDTO cadastrarFuncionario();
    
    void listarFuncionarios(List<String> funcionarios);

    int removerFuncionario();
    
}

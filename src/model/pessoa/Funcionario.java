package model.pessoa;
import java.time.LocalDate;

/**
 * Classe que representa um funcionário.
 */
public class Funcionario extends Pessoa{
    
    private int codigo;


    /**
     * Construtor da classe Funcionário.
     *
     * @param codigo          O código do funcionário.
     * @param nome            O nome do funcionário.
     * @param endereco        O endereço do funcionário.
     * @param cidade          A cidade do funcionário.
     * @param estado          O estado do funcionário.
     * @param telefone        O número de telefone do funcionário.
     * @param dataNascimento  A data de nascimento do funcionário.
     */
    public Funcionario(int codigo, String nome, String endereco, String cidade, String estado, String telefone, LocalDate dataNascimento) {
        super(nome, endereco, cidade, estado, telefone, dataNascimento);
        this.codigo = codigo;
    }


    /**
     * Construtor da classe Funcionário.
     * Cria admin
     *
     * @param codigo O código do funcionário.
     * @param nome O nome do funcionário.
     */
    public Funcionario(int codigo, String nome){
        super(nome);
        this.codigo = codigo;
    }


    /**
     * Retorna uma descrição formatada do funcionário.
     *
     * @return Uma string formatada com o nome, código e cargo do funcionário.
     */
    @Override
    public String getDescricao() {
        String str = "";
        str += "Nome: " + getNome() + "\n";
        str += "Código: " + getCodigo() + "\n";
        str += "Endereço: " + getEndereco() + "\n";
        str += "Cidade: " + getCidade() + "\n";
        str += "Estado: " + getEstado() + "\n";
        str += "Telefone: " + getTelefone() + "\n";
        str += "Data de nascimento: " + getDataNascimento() + "\n";
        return str;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
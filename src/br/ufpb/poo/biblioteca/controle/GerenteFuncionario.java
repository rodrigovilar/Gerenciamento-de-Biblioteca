package br.ufpb.poo.biblioteca.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.poo.biblioteca.entidade.Funcionario;
import br.ufpb.poo.biblioteca.entidade.Pessoa;
import br.ufpb.poo.biblioteca.excecao.CampoInvalidoException;
import br.ufpb.poo.biblioteca.excecao.FuncionarioInexistenteException;
import br.ufpb.poo.biblioteca.excecao.FuncionarioJaCadastradoException;


public class GerenteFuncionario {

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public void addFuncionario(Funcionario funcionario) {
		validarFuncionario(funcionario);
		funcionarios.add(funcionario);
	}

	private void validarFuncionario(Funcionario funcionario) {
		if(funcionarioJaCadastrado(funcionario)){
			throw new FuncionarioJaCadastradoException("Funcionario ja cadastrado!!!");
		}
		if(funcionario.getNome() == null){
			throw new CampoInvalidoException("Funcionario sem nome!!!");
		}
		if(funcionario.getMatricula() == 0){
			throw new CampoInvalidoException("Funcionario sem matricula!!!");
		}
		if(funcionario.getFuncao() == null){
			throw new CampoInvalidoException("Funcionario sem fun��o");
		}
		if(funcionario.cpf().getCpf() == null){
			throw new CampoInvalidoException("Funcionario sem cpf");
		}
		if(!funcionario.cpf().validarCpf()){
			throw new CampoInvalidoException("Funcionario com CPF invalido!!!");
		}
		if(cpfJaCadastrado(funcionario)){
			throw new CampoInvalidoException("Funcionario com CPF ja cadastrado!!!");
		}
	}

	private boolean funcionarioJaCadastrado(Funcionario funcionario) {
		boolean cadastrado = false;
		for (Funcionario f : funcionarios) {
			if(f.equals(funcionario)){
				cadastrado = true;
			}
		}
		return cadastrado;
	}

	public int quantidadeDefuncionarios() {
		return funcionarios.size();
	}

	public Funcionario getFuncionario(int pos) {
		return funcionarios.get(pos);
	}
	
	private boolean cpfJaCadastrado(Funcionario funcionario) {
		boolean cadastrado = false;
		for(Pessoa a: funcionarios){
			if(a.cpf().getCpf().equals(funcionario.cpf().getCpf())){
				cadastrado = true;
			}
		}
		return cadastrado;
	}

	public void removeFuncionario(Funcionario funcionario) {
		if(quantidadeDefuncionarios() == 0){
			throw new FuncionarioInexistenteException("N�o existe funcionario para remover!!!");
		}
		if(!exiteFuncionario(funcionario)){
			throw new FuncionarioInexistenteException("Funcionario inexistente!!!");
		}
		funcionarios.remove(funcionario);
	}

	public boolean exiteFuncionario(Funcionario funcionario){
		boolean existe = false;
		for(Funcionario f :funcionarios){
			if(f.equals(funcionario)){
				existe = true;
			}
		}
		return existe;
	}
}

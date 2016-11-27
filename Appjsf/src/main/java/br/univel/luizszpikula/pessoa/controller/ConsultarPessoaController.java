package br.univel.luizszpikula.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.univel.luizszpikula.model.PessoaModel;
import br.univel.luizszpikula.repository.PessoaRepository;

/**
* Controlador para Consultar Pessoas
* @author Luiz Carlos Szpikula Junior
*/

@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject transient
	private PessoaRepository pessoaRepository;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init(){

		//RETORNAR AS PESSOAS CADASTRADAS
		this.pessoas = pessoaRepository.GetPessoas();
	}

	/***
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * @param PessoaModel pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel){

		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1)); // PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)

		this.pessoaModel = pessoaModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){

		this.pessoaRepository.AlterarRegistro(this.pessoaModel);

		this.init(); // RECARREGA OS REGISTROS
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * @param PessoaModel pessoaModel
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel){

		this.pessoaRepository.ExcluirRegistro(pessoaModel.getCodigo());	// EXCLUI A PESSOA DO BANCO DE DADOS

		this.pessoas.remove(pessoaModel); // REMOVENDO A PESSOA DA LISTA e DATATABLE É ATUALIZADO

	}
}

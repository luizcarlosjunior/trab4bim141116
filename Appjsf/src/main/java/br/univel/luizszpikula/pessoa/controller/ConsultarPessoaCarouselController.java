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
* Controlador para Consultar Pessoas em modo Carrossel
* @author Luiz Carlos Szpikula Junior
*/

@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/***
	 * Inicializador
	 */
	@PostConstruct
	private void init(){
		this.pessoas = pessoaRepository.GetPessoas(); //busca todos os registros
	}




}

package br.univel.luizszpikula.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.univel.luizszpikula.model.PessoaModel;
import br.univel.luizszpikula.repository.PessoaRepository;
import br.univel.luizszpikula.usuario.controller.UsuarioController;
import br.univel.luizszpikula.uteis.Uteis;

/**
* Controller para cadastro de pessoas
* @see play.mvc.Controller
* @author Luiz Carlos Szpikula Junior
*/

@Named(value="cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	@Inject
	PessoaModel pessoaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	/**
	 * retorna o modelo pessoa que está setado na variável pessoaModel
	 * @return PessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * seta um modelo pessoa passado por parâmetro
	 * @param PessoaModel pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Salva um novo registro via imput
	 */
	public void SalvarNovaPessoa(){

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		pessoaModel.setOrigemCadastro("I"); // INFORMANDO QUE O CADASTRO FOI VIA INPUT

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}

}
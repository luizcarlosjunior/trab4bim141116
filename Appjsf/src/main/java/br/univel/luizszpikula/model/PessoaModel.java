package br.univel.luizszpikula.model;

import java.time.LocalDateTime;

/**
* Modelo Pessoa
* @see javax.lang.model
* @author Luiz Carlos Szpikula Junior
*/

public class PessoaModel {

	private Integer       codigo;
	private String        nome;
	private String        sexo;
	private LocalDateTime dataCadastro;
	private String        email;
	private String        endereco;
	private String        origemCadastro;
	private UsuarioModel  usuarioModel;


	/**
	 * retorna o código da Pessoa
	 * @return Integer codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * seta um novo código para a Pessoa
	 * @param Integer codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * retorna o nome da Pessoa
	 * @return String nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * seta um novo nome para a Pessoa
	 * @param String nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * retorna o sexo da Pessoa
	 * @return String sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * seta um novo sexo para a Pessoa
	 * @param String sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * retorna a data de cadastro
	 * @return LocalDateTime dataCadastro
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * seta uma nova data de cadastro para a Pessoa
	 * @param LocalDateTime dataCadastro
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * retorna o email da Pessoa
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * seta um novo email para a Pessoa
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * retorna o endereço da Pessoa
	 * @return String endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * seta um novo endereço para a Pessoa
	 * @param String endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * retorna a origem do cadastro da Pessoa
	 * @return String origemCadastro
	 */
	public String getOrigemCadastro() {
		return origemCadastro;
	}

	/**
	 * seta uma nova origem de cadastro para a Pessoa
	 * @param String origemCadastro
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	/**
	 * retorna o modelo Pessoa
	 * @return UsuarioModel usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * seta um novo modelo de usuário para a Pessoa
	 * @param UsuarioModel usuarioModel
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}
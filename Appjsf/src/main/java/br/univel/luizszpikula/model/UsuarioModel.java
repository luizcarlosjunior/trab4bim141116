package br.univel.luizszpikula.model;

import java.io.Serializable;

/**
* Modelo Usuário
* @see javax.lang.model
* @author Luiz Carlos Szpikula Junior
*/

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String usuario;
	private String senha;

	/**
	 * retorna o código do usuário
	 * @return String codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * seta um novo código para o usuário
	 * @param String codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * retorna o nome do usuário
	 * @return String usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * seta um novo nome para o usuário
	 * @param String usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * retorna a senha do usuário
	 * @return String senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * seta uma nova senha par ao usuário
	 * @param String senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
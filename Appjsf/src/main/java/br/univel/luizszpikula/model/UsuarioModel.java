package br.univel.luizszpikula.model;

import java.io.Serializable;

/**
* Modelo Usu�rio
* @see javax.lang.model
* @author Luiz Carlos Szpikula Junior
*/

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String usuario;
	private String senha;

	/**
	 * retorna o c�digo do usu�rio
	 * @return String codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * seta um novo c�digo para o usu�rio
	 * @param String codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * retorna o nome do usu�rio
	 * @return String usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * seta um novo nome para o usu�rio
	 * @param String usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * retorna a senha do usu�rio
	 * @return String senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * seta uma nova senha par ao usu�rio
	 * @param String senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
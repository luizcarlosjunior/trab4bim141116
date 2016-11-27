package br.univel.luizszpikula.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.univel.luizszpikula.model.UsuarioModel;
import br.univel.luizszpikula.repository.entity.UsuarioEntity;
import br.univel.luizszpikula.uteis.Uteis;

/**
* Abstração para persistir os dados de Usuários
* @author Luiz Carlos Szpikula Junior
*/
public class UsuarioRepository implements Serializable {


	private static final long serialVersionUID = 1L;

	/***
	 * Método responsável por salvar um usuário
	 * @param UsuarioModel usuarioModel
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){

		try {

			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser"); //QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)

			query.setParameter("usuario", usuarioModel.getUsuario()); // parâmetro usuario da query
			query.setParameter("senha", usuarioModel.getSenha()); // parâmetro senha da query

			return (UsuarioEntity)query.getSingleResult(); // RETORNA O USUÁRIO SE FOR LOCALIZADO

		} catch (Exception e) {
			return null;
		}

	}
}
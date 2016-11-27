package br.univel.luizszpikula.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.univel.luizszpikula.model.UsuarioModel;
import br.univel.luizszpikula.repository.entity.UsuarioEntity;
import br.univel.luizszpikula.uteis.Uteis;

/**
* Abstra��o para persistir os dados de Usu�rios
* @author Luiz Carlos Szpikula Junior
*/
public class UsuarioRepository implements Serializable {


	private static final long serialVersionUID = 1L;

	/***
	 * M�todo respons�vel por salvar um usu�rio
	 * @param UsuarioModel usuarioModel
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){

		try {

			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser"); //QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)

			query.setParameter("usuario", usuarioModel.getUsuario()); // par�metro usuario da query
			query.setParameter("senha", usuarioModel.getSenha()); // par�metro senha da query

			return (UsuarioEntity)query.getSingleResult(); // RETORNA O USU�RIO SE FOR LOCALIZADO

		} catch (Exception e) {
			return null;
		}

	}
}
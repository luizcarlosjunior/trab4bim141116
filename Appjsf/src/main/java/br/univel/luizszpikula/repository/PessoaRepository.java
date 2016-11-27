package br.univel.luizszpikula.repository;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.univel.luizszpikula.model.PessoaModel;
import br.univel.luizszpikula.repository.entity.PessoaEntity;
import br.univel.luizszpikula.repository.entity.UsuarioEntity;
import br.univel.luizszpikula.uteis.Uteis;

/**
* Abstração para persistir os dados de Pessoas
* @author Luiz Carlos Szpikula Junior
*/

public class PessoaRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/***
	 * Método responsável por salvar uma pessoa
	 * @param PessoaModel pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){

		entityManager = Uteis.JpaEntityManager();
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo());

		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity); // persiste

	}
}
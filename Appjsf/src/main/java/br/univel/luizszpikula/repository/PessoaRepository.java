package br.univel.luizszpikula.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.univel.luizszpikula.model.PessoaModel;
import br.univel.luizszpikula.model.UsuarioModel;
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

	/***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * @return List<PessoaModel> lista de pessoas
	 */
	public List<PessoaModel> GetPessoas(){

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

		entityManager =  Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();

		PessoaModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : pessoasEntity) {

			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());

			if(pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");


			if(pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");

			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);

			pessoasModel.add(pessoaModel);
		}

		return pessoasModel;

	}

	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
	 * @param int codigo
	 * @return PessoaEntity
	 */
	private PessoaEntity GetPessoa(int codigo){

		entityManager =  Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param PessoaModel pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel){

		entityManager =  Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param int codigo
	 */
	public void ExcluirRegistro(int codigo){

		entityManager =  Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(codigo);

		entityManager.remove(pessoaEntity);
	}


	/***
	 * RETORNA OS TIPOS DE PESSOA AGRUPADOS
	 * @return Hashtable<String, Integer>
	 */
	public Hashtable<String, Integer> GetOrigemPessoa(){

		Hashtable<String, Integer> hashtableRegistros = new Hashtable<String,Integer>();

		entityManager =  Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.GroupByOrigemCadastro");

		@SuppressWarnings("unchecked")
		Collection<Object[]> collectionRegistros  = (Collection<Object[]>)query.getResultList();

		for (Object[] objects : collectionRegistros) {


			String tipoPessoa 		= (String)objects[0];
			int	   totalDeRegistros = ((Number)objects[1]).intValue();

			if(tipoPessoa.equals("X"))
				tipoPessoa = "XML";
			else
				tipoPessoa = "INPUT";

			hashtableRegistros.put(tipoPessoa, totalDeRegistros);

		}

		return hashtableRegistros;

	}


}
package br.univel.luizszpikula.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * retorna o modelo pessoa que est� setado na vari�vel pessoaModel
	 * @return PessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * seta um modelo pessoa passado por par�metro
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

	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 */
	public void UploadRegistros() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			if (this.file.getFileName().equals("")) {
				Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				return;
			}

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(this.file.getInputstream());

			Element element = doc.getDocumentElement();

			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elementPessoa = (Element) node;

					// PEGANDO OS VALORES DO ARQUIVO XML
					String nome = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
					String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
					String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

					PessoaModel newPessoaModel = new PessoaModel();

					newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
					newPessoaModel.setEmail(email);
					newPessoaModel.setEndereco(endereco);
					newPessoaModel.setNome(nome);
					newPessoaModel.setOrigemCadastro("X");
					newPessoaModel.setSexo(sexo);

					pessoaRepository.SalvarNovoRegistro(newPessoaModel); // SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
				}
			}

			Uteis.MensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
package br.univel.luizszpikula.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.univel.luizszpikula.model.UsuarioModel;
import br.univel.luizszpikula.repository.UsuarioRepository;
import br.univel.luizszpikula.repository.entity.UsuarioEntity;
import br.univel.luizszpikula.uteis.Uteis;

/**
* Controlador para Usu�rios
* @author Luiz Carlos Szpikula Junior
*/

@Named(value="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioEntity usuarioEntity;


	/**
	* retorna um modelo de usu�rio
	* @return UsuarioModel
	*/
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	* seta um modelo de usu�rio
	* @param UsuarioModel usuarioModel
	*/
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}


	/**
	* retorna um modelo de usu�rio da sess�o
	* @return UsuarioModel
	*/
	public UsuarioModel GetUsuarioSession(){

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	/**
	* Desconecta o usu�rio e retorna o endere�o para redirect
	* @return String endere�o de redirect
	*/
	public String Logout(){

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // reset na session

		return "/index.xhtml?faces-redirect=true"; //redirect
	}
	
	/**
	* Fun��o para efetuar login
	* @return String endere�o de redirect
	*/
	public String EfetuarLogin(){

		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){

			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){

			Uteis.Mensagem("Favor informara senha!");
			return null;
		}
		else{

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);

			if(usuarioEntity!= null){

				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());


				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);


				return "sistema/home?faces-redirect=true";
			}
			else{
				Uteis.Mensagem("N�o foi poss�vel efetuar o login com esse usu�rio e senha!");
				return null;
			}
		}

	}

}
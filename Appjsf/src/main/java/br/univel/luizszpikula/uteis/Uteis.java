package br.univel.luizszpikula.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
* Classe de funções uteis/extras
* @author Luiz Carlos Szpikula Junior
*/
public class Uteis {
	/**
	 * Define a interface para extensões do EclipseLink para o EntityManage
	 * @return EntityManager
	 */
	public static EntityManager JpaEntityManager(){

		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();

		return (EntityManager)request.getAttribute("entityManager");
	}

	/**
	 * para mostrar mensagens de alerta na tela
	 * @param String mensagem
	 */
	public static void Mensagem(String mensagem){

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
	}

	/**
	 * para mostrar mensagens de atenção na tela
	 * @param String mensagem
	 */
	public static void MensagemAtencao(String mensagem){

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	/**
	 * para mostrar mensagens de informação na tela
	 * @param String mensagem
	 */
	public static void MensagemInfo(String mensagem){

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

}
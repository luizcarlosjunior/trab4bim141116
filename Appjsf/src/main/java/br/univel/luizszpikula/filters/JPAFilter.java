package br.univel.luizszpikula.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
* ESSE FILTER VAI SER CHAMADO TODA VEZ QUE FOR REALIZADO UMA REQUISIÇÃO PARA O FACES SERVLET.
* @see javax.servlet.Filter
* @author Luiz Carlos Szpikula Junior
*/

@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

	/**
	* O construtor JPAFilter sem argumento
	*/
    public JPAFilter() {

    }

	/**
	* Método destroy
	*/
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	* Método doFilter
	* @param ServletRequest
	* @param ServletResponse
	* @param FilterChain
	* @throws IOException, ServletException
	*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		EntityManager entityManager =  this.entityManagerFactory.createEntityManager(); // CRIANDO UM ENTITYMANAGER
		request.setAttribute("entityManager", entityManager); // ADICIONANDO ELE NA REQUISIÇÃO
		entityManager.getTransaction().begin(); // INICIANDO UMA TRANSAÇÃO
		chain.doFilter(request, response); // INICIANDO FACES SERVLET

		try {
			entityManager.getTransaction().commit(); // SE NÃO TIVER ERRO NA OPERAÇÃO ELE EXECUTA O COMMIT
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); // SE TIVER ERRO NA OPERAÇÃO É EXECUTADO O rollback
		}
		finally{
			entityManager.close(); // DEPOIS DE DAR O COMMIT OU ROLLBACK ELE FINALIZA O entityManager
		}
	}

	/**
	* Método init
	* @param FilterConfig
	* @throws ServletException
	*/
	public void init(FilterConfig fConfig) throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); // CRIA O entityManagerFactory COM OS PARÂMETROS DEFINIDOS NO persistence.xml
	}

}
package br.univel.luizszpikula.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.univel.luizszpikula.model.UsuarioModel;

/**
* Essa classe auxilia na autenticação do sistema.
* @see javax.servlet.Filter
* @author Luiz Carlos Szpikula Junior
*/

@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {


	/**
	* O construtor AutenticacaoFilter sem argumento
	*/
    public AutenticacaoFilter() {

    }

	/**
	* Método destroy
	*/
	public void destroy() {

	}

	/**
	* Método doFilter
	* @param ServletRequest
	* @param ServletResponse
	* @param FilterChain
	* @throws
	*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession 				= ((HttpServletRequest) request).getSession();

		HttpServletRequest httpServletRequest   = (HttpServletRequest) request;

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if(httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){

			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado");

			if(usuarioModel == null){

				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml");

			}
			else{

				chain.doFilter(request, response);
			}
		}
		else{

			chain.doFilter(request, response);
		}
	}


	/**
	* Método init
	* @param FilterConfig
	*/
	public void init(FilterConfig fConfig) throws ServletException {

	}

}

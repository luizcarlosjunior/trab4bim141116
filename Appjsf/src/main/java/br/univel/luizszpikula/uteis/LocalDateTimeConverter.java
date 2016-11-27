package br.univel.luizszpikula.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
* Classe de formata��o de datas
* @author Luiz Carlos Szpikula Junior
*/

@FacesConverter(value= LocalDateTimeConverter.ID)
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID="br.univel.luizszpikula.uteis.LocalDateTimeConverter";

	/**
	 * para mostrar mensagens de alerta na tela
	 * @param FacesContext facesContext
	 * @param UIComponent uiComponent
	 * @param String value
	 * @return Object
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null;
		LocalDateTime localDateTime = null;

		Object object = super.getAsObject(facesContext, uiComponent, value);

		if(object instanceof Date){

			date = (Date) object;

			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	  		return localDateTime;

		}
		else{

			throw new IllegalArgumentException(String.format("value=%s N�o foi poss�vel converter LocalDateTime, resultado super.getAsObject=%s",value,object));
		}


	}

	/**
	 * pega o objetod e data e retorna em string formatada
	 * @param FacesContext facesContext
	 * @param UIComponent uiComponent
	 * @param Object value
	 * @return String
	 */
	  @Override
	  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

		  if(value == null)
			  return super.getAsString(facesContext, uiComponent, value);

		  if(value instanceof LocalDateTime){

			  LocalDateTime localDateTime = (LocalDateTime) value;

			  Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

			  Date  date =  Date.from(instant);

			  return super.getAsString(facesContext, uiComponent, date);
		  }
		  else{

			  throw new IllegalArgumentException(String.format("value=%s n�o � um LocalDateTime",value));
		  }

	  }
}
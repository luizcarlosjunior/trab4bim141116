package br.univel.luizszpikula.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
* Classe de copnvers�o de datas
* @author Luiz Carlos Szpikula Junior
*/
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * TRANSFORMA EM Timestamp NA HORA DE PERSISTIR NO BANCO DE DADOS
	 * @param LocalDateTime localDateTime
	 * @return Timestamp
	 */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);

    	return null;
    }

    /**
	 * TRANSFORMA UM Timestamp EM LocalDateTime QUANDO RETORNAR DO BANCO PARA ENTIDADE
	 * @param Timestamp timestamp
	 * @return Timestamp timestamp
	 */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

    	if(timestamp != null)
    		return timestamp.toLocalDateTime();

    	return null;
    }
}
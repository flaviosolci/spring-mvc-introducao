/**================================================
 * File 		= ProdutoValidation.java
 * Description 	=
 * Date 		= 30 de mar de 2019
 * User			= Flavio Solci
 * ================================================
 */
package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

/**
 * @author Flavio Solci
 *
 */
public class ProdutoValidation implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		if (((Produto) target).getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}

	}
}

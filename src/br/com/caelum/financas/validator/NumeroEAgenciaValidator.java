package br.com.caelum.financas.validator;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import br.com.caelum.financas.modelo.Conta;

public class NumeroEAgenciaValidator implements ConstraintValidator<NumeroEAgencia, Conta>{

	public void initialize(NumeroEAgencia arg0) {}

	public boolean isValid(Conta conta, ConstraintValidatorContext arg1) {
		if(conta == null){
			return true;
		}
		boolean agenciaEhVazia = (conta.getAgencia() == null || conta.getAgencia().trim().isEmpty());
		boolean numeroEhVazio = (conta.getNumero() == null || conta.getNumero().trim().isEmpty());
		return !(agenciaEhVazia ^ numeroEhVazio);
	}
   
}

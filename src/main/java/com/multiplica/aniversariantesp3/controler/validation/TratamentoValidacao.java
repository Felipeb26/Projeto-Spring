package com.multiplica.aniversariantesp3.controler.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoValidacao {
	
	@Autowired
	private MessageSource messageSoure;
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e ->{
			String mensagem = messageSoure.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDto erro = new ErroFormDto(e.getField(), mensagem);
			dto.add(erro);
		});	
		
		
		return dto;
	}
	
}

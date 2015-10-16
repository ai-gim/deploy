package com.asiainfo.gim.deploy.api.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.deploy.domain.PostScripts;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostScriptsValidator.Valicator.class)
public @interface PostScriptsValidator {
	String message() default "postscripts bean validate fail!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	public class Valicator implements
			ConstraintValidator<PostScriptsValidator, PostScripts> {
		@Context
		private ContainerRequestContext context;

		@Override
		public void initialize(PostScriptsValidator pv) {

		}

		@Override
		public boolean isValid(PostScripts postScripts,
				ConstraintValidatorContext cvc) {
			if (StringUtils.equals(context.getMethod(), "POST")) {
				valid(postScripts);
			} else if (StringUtils.equals(context.getMethod(), "PUT")) {
				valid(postScripts);
			}
			return true;
		}
		
		private boolean valid(PostScripts postScripts){
			if(postScripts == null || StringUtils.isEmpty(postScripts.getName())){
				return false;
			}
			return true;
		}

	}
}

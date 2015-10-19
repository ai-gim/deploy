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

import com.asiainfo.gim.deploy.domain.TemplateConf;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TemplateValidator.Valicator.class)
public @interface TemplateValidator
{
	String message() default "Template bean validate fail!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	public class Valicator implements ConstraintValidator<TemplateValidator, TemplateConf>
	{
		@Context
		private ContainerRequestContext context;

		@Override
		public void initialize(TemplateValidator dv)
		{
			
		}

		@Override
		public boolean isValid(TemplateConf temp, ConstraintValidatorContext cv)
		{
			if (StringUtils.equals(context.getMethod(), "POST") )
			{
				if (temp == null)
				{
					return false;
				}
			}
			return true;
		}
	}
}
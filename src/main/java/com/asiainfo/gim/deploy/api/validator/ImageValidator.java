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

import com.asiainfo.gim.deploy.domain.Image;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageValidator.Valicator.class)
public @interface ImageValidator
{
	String message() default "Image bean validate fail!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	public class Valicator implements ConstraintValidator<ImageValidator, Image>
	{
		@Context
		private ContainerRequestContext context;

		@Override
		public void initialize(ImageValidator dv)
		{
			
		}

		@Override
		public boolean isValid(Image image, ConstraintValidatorContext cv)
		{
			if (StringUtils.equals(context.getMethod(), "POST") )
			{
				if (StringUtils.isEmpty(image.getIsoFile()))
				{
					return false;
				}
			}
			return true;
		}
	}
}
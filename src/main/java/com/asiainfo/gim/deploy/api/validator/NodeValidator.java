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

import com.asiainfo.gim.deploy.domain.Node;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NodeValidator.Valicator.class)
public @interface NodeValidator {
	String message() default "Node bean validate fail!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	public class Valicator implements ConstraintValidator<NodeValidator, Node> {
		@Context
		private ContainerRequestContext context;

		@Override
		public void initialize(NodeValidator dv) {

		}

		@Override
		public boolean isValid(Node node, ConstraintValidatorContext cv) {
			if (StringUtils.equals(context.getMethod(), "POST")) {
				if (node == null || StringUtils.isEmpty(node.getName())) {
					return false;
				}
			}
			return true;
		}
	}
}
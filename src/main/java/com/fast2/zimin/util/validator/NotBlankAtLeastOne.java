package com.fast2.zimin.util.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fast2.zimin.util.validator.impl.NotBlankAtLeastOneImpl;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlankAtLeastOneImpl.class)
public @interface NotBlankAtLeastOne {
	String message() default "At least one required.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String first();

	String second();

	String third() default "";
}

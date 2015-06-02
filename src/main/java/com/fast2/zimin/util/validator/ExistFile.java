package com.fast2.zimin.util.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fast2.zimin.util.validator.impl.ExistFileImpl;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistFileImpl.class)
public @interface ExistFile {
	String message() default "File not exist.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

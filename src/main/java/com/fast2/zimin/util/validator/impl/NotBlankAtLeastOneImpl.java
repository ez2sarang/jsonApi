package com.fast2.zimin.util.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.fast2.zimin.util.StringUtil;
import com.fast2.zimin.util.TheLogger;
import com.fast2.zimin.util.validator.NotBlankAtLeastOne;

public class NotBlankAtLeastOneImpl implements
		ConstraintValidator<NotBlankAtLeastOne, Object> {
	private String firstField;
	private String secondField;
	private String thirdField;

	@Override
	public void initialize(NotBlankAtLeastOne constraintAnnotation) {
		this.firstField = constraintAnnotation.first();
		this.secondField = constraintAnnotation.second();
		this.thirdField = constraintAnnotation.third();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext ctx) {
		boolean isValid = false;

		try {
			String firstValue = BeanUtils.getProperty(object, firstField);
			String secondValue = BeanUtils.getProperty(object, secondField);
			String thirdValue = "";
			if (StringUtil.isNotBlank(thirdField)) {
				thirdValue = BeanUtils.getProperty(object, thirdField);
			}

			isValid = StringUtil.isNotBlank(firstValue)
					|| StringUtil.isNotBlank(secondValue)
					|| StringUtil.isNotBlank(thirdValue);

			if (!isValid) {
				String propertyNodes = firstField + "," + secondField;
				if (StringUtil.isNotBlank(thirdField)) {
					propertyNodes += "," + thirdField;
				}

				ctx.disableDefaultConstraintViolation();
				ctx.buildConstraintViolationWithTemplate(
						"At least one required.")
						.addPropertyNode(propertyNodes)
						.addConstraintViolation();
			}
		} catch (Exception e) {
			TheLogger.error(e.getMessage(), e);
			return false;
		}

		return isValid;
	}
}

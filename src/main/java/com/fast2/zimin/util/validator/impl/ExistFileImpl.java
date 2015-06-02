package com.fast2.zimin.util.validator.impl;

import java.io.File;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fast2.zimin.util.StringUtil;
import com.fast2.zimin.util.validator.ExistFile;

public class ExistFileImpl implements ConstraintValidator<ExistFile, String> {

	@Override
	public void initialize(ExistFile arg0) {
	}

	@Override
	public boolean isValid(String filePath, ConstraintValidatorContext ctx) {
		if (StringUtil.isBlank(filePath)) {
			return true;
		}

		File file = new File(filePath);
		return (file.exists() && file.isFile());
	}
}

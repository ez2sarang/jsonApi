package com.fast2.zimin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException
	{
		context = ctx;
	}

	public ApplicationContext getApplicationContext() {
        return context;
    }
}

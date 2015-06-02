package com.fast2.zimin.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class MessageSourceProvider implements MessageSourceAware {

	@SuppressWarnings("unused")
	private MessageSource messageSource;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource; 
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

}

package com.idealo.utils;

import java.text.MessageFormat;

import org.springframework.stereotype.Service;

@Service
public class MessageFormatter {
	public String getMessage(String message) {
		return message;
	}
	
	public String getMessage(String message, String... params) {
		MessageFormat messageFormat = new MessageFormat(message);
		return messageFormat.format(params);
	}
}

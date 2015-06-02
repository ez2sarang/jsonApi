package com.fast2.zimin.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TheLogger {
	private static final Logger writer = Logger.getLogger(TheLogger.class);
	private static final String FQCN = TheLogger.class.getName();

	private TheLogger() {
	}

	public static Logger getWriter() {
		return TheLogger.writer;
	}

	public static void log(Level level, String message) {
		writer.log(FQCN, level, message, null);
	}

	public static void trace(String message) {
		log(Level.TRACE, message);
	}

	public static void debug(String message) {
		log(Level.DEBUG, message);
	}

	public static void info(String message) {
		log(Level.INFO, message);
	}

	public static void warn(String message) {
		log(Level.WARN, message);
	}

	public static void error(String message) {
		log(Level.ERROR, message);
	}

	public static void fatal(String message) {
		log(Level.FATAL, message);
	}

	public static void log(Level level, String format, Object... args) {
		writer.log(FQCN, level, String.format(format, args), null);
	}

	public static void trace(String format, Object... args) {
		log(Level.TRACE, format, args);
	}

	public static void debug(String format, Object... args) {
		log(Level.DEBUG, format, args);
	}

	public static void info(String format, Object... args) {
		log(Level.INFO, format, args);
	}

	public static void warn(String format, Object... args) {
		log(Level.WARN, format, args);
	}

	public static void error(String format, Object... args) {
		log(Level.ERROR, format, args);
	}

	public static void fatal(String format, Object... args) {
		log(Level.FATAL, format, args);
	}

	public static void logTrace(Level level, String message, Throwable t) {
		writer.log(FQCN, level, message, t);
	}

	public static void error(String message, Throwable t) {
		logTrace(Level.ERROR, message, t);
	}

	public static void fatal(String message, Throwable t) {
		logTrace(Level.FATAL, message, t);
	}

	public static void logTrace(Level level, Throwable t, String format,
			Object... args) {
		writer.log(FQCN, level, String.format(format, args), t);
	}

	public static void error(Throwable t, String format, Object... args) {
		logTrace(Level.ERROR, t, format, args);
	}

	public static void fatal(Throwable t, String format, Object... args) {
		logTrace(Level.FATAL, t, format, args);
	}
}
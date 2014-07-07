package com.besuikerd.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;

/**
 * Besuikerd's Logger
 * @author Besuikerd
 *
 */
public class BLogger {
	
	private static final Logger logger;
	private static final Object[] EMPTY_ARRAY = new Object[0];
	
	private static boolean debugMode = false;
	
	static {
		logger = LogManager.getLogger("Besuikerd");
		setDebugMode(true);
	}
	
	public static void log(Level level, Object msg, Object... params){
		logger.log(level, String.format(msg.toString(), params));
	}
	
	public static void log(Level level, Object msg){
		log(level, msg, EMPTY_ARRAY);
	}
	
	public static void log(Level level, String msg){
		log(level, msg, EMPTY_ARRAY);
	}
	
	public static void warn(Object msg, Object... params){
		log(Level.WARN, msg, params);
	}
	
	public static void warn(Object msg){
		warn(msg, EMPTY_ARRAY);
	}
	
	public static void warn(String msg){
		warn(msg, EMPTY_ARRAY);
	}
	
	public static void error(Object msg, Object... params){
		log(Level.FATAL, msg, params);
	}
	
	public static void error(Object msg){
		error(msg, EMPTY_ARRAY);
	}
	
	public static void error(String msg){
		error(msg, EMPTY_ARRAY);
	}
	
	public static void info(Object msg, Object... params){
		log(Level.INFO, msg, params);
	}
	
	public static void info(Object msg){
		info(msg, EMPTY_ARRAY);
	}
	
	public static void info(String msg){
		info(msg, EMPTY_ARRAY);
	}
	
	public static void debug(Object msg, Object... params){
		if(debugMode){
			log(Level.INFO, msg, params);
		}
	}
	
	public static void debug(Object msg){
		debug(msg, EMPTY_ARRAY);
	}
	
	public static void debug(String msg){
		debug(msg, EMPTY_ARRAY);
	}
	
	public static boolean isDebugMode(){
		return debugMode;
	}
	
	public static void setDebugMode(boolean enabled){
		if(!debugMode && enabled){
			debug("Debug mode is enabled. Debug messages will be printed");
		} else if(debugMode && !enabled){
			debug("Debug mode is disabled. Debug messages will not be printed");
		}
		debugMode = enabled;
	}
}
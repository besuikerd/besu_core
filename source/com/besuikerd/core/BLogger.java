package com.besuikerd.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

/**
 * Besuikerd's Logger
 * @author Besuikerd
 *
 */
public class BLogger {
	
	private static final Logger logger = Logger.getLogger("Besuikerd");
	
	private static boolean debugMode = false;
	
	public static void init(boolean debugMode){
		logger.setParent(FMLLog.getLogger());
		setDebugMode(debugMode);
		ClientLogger.init();
		ServerLogger.init();
	}
	
	public static void log(Level level, Object msg, Object... params){
		logger.log(level, String.format("%s|%s", FMLCommonHandler.instance().getEffectiveSide(), msg == null ? "null" : String.format(msg.toString(), params)));
	}
	
	public static void log(Level level, Object msg){
		logger.log(level, String.format("%s|%s", FMLCommonHandler.instance().getEffectiveSide(), msg));
	}
	
	public static void warn(Object msg, Object... params){
		log(Level.WARNING, msg, params);
	}
	
	public static void warn(Object msg){
		log(Level.WARNING, msg);
	}
	
	public static void error(Object msg, Object... params){
		log(Level.SEVERE, msg, params);
	}
	
	public static void error(Object msg){
		log(Level.SEVERE, msg);
	}
	
	public static void info(Object msg, Object... params){
		log(Level.INFO, msg, params);
	}
	
	public static void info(Object msg){
		log(Level.INFO, msg);
	}
	
	public static void debug(Object msg, Object... params){
		if(debugMode){
			info(msg, params);
		}
	}
	
	public static void debug(String msg){
		if(debugMode){
			info(msg);
		}
	}
	
	public static boolean isDebugMode(){
		return debugMode;
	}
	
	public static void setDebugMode(boolean enabled){
		if(!debugMode && enabled){
			info("Debug mode is enabled. More debug messages will be printed");
		} else if(debugMode && !enabled){
			info("Debug mode is disabled. Less debug messages will be printed");
		}
		debugMode = enabled;
	}
}

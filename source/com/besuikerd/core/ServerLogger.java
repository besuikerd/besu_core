package com.besuikerd.core;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * Besuikerd's Logger
 * @author Besuikerd
 *
 */
public class ServerLogger {
	
	private static final Logger logger = LogManager.getLogger("Besuikerd");
	private static boolean debugMode = false;
	
	
	public static void log(Level level, Object msg, Object... params){
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER){
			logger.log(level, String.format("%s|%s", FMLCommonHandler.instance().getEffectiveSide(), msg == null ? "null" : String.format(msg.toString(), params)));
		}
	}
	
	public static void log(Level level, Object msg){
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER){
			logger.log(level, String.format("%s|%s", FMLCommonHandler.instance().getEffectiveSide(), msg));
		}
	}
	
	public static void warn(Object msg, Object... params){
		log(Level.WARN, msg, params);
	}
	
	public static void warn(Object msg){
		log(Level.WARN, msg);
	}
	
	public static void error(Object msg, Object... params){
		log(Level.ERROR, msg, params);
	}
	
	public static void error(Object msg){
		log(Level.ERROR, msg);
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

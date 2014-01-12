package com.besuikerd.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;

/**
 * Besuikerd's Logger
 * @author Besuikerd
 *
 */
public class ServerLogger {
	
	private static final Logger logger = Logger.getLogger("Besuikerd");
	
	public static void init(){
		logger.setParent(FMLLog.getLogger());
	}
	
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
		if(BLogger.isDebugMode()){
			info(msg, params);
		}
	}
	
	public static void debug(String msg){
		if(BLogger.isDebugMode()){
			info(msg);
		}
	}
}

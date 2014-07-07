package com.besuikerd.core.utils.profiling;

public class BesuProfiler {
	protected long startTime;
	
	public static BesuProfiler newProfile(){
		BesuProfiler profiler = new BesuProfiler();
		profiler.reset();
		return profiler;
	}
	
	public void reset(){
		this.startTime = System.currentTimeMillis();
	}
	
	public long getTime(){
		return System.currentTimeMillis() - startTime;
	}
	
	@Override
	public String toString() {
		return Long.toString(getTime());
	};
}

package com.besuikerd.core.utils.collection;

import java.util.Map;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.MapConstraint;
import com.google.common.collect.MapConstraints;

public class SafeConstrainedMap<K, V> extends ForwardingMap<K, V>{

	protected Map<K, V> delegate;
	
	public static <K, V> SafeConstrainedMap<K, V> create(Map<K, V> map, MapConstraint<K, V> constraint){
		return new SafeConstrainedMap<K, V>(MapConstraints.constrainedMap(map, constraint));
	}
	
	public SafeConstrainedMap(Map<K, V> delegate) {
		this.delegate = delegate;
	}
	
	@Override
	protected Map<K, V> delegate() {
		return delegate;
	}
	
	@Override
	public V put(K key, V value) {
		try{
			return super.put(key, value);
		} catch(IllegalArgumentException e){
			return null;
		}
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for(Map.Entry entry : entrySet()){
			put((K) entry.getKey(), (V) entry.getValue());
		}
	}
}

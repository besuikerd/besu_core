package com.besuikerd.core.utils.collection;

import java.util.Map;

import com.google.common.collect.MapConstraint;

/**
 * constrains key-value pairs added to be smaller than the currently existing
 * value for that key. Not thread safe.
 * 
 * @author Besuikerd
 * 
 * @param <K>
 * @param <V>
 */
public class SmallerThanMapConstraint<K, V extends Comparable> implements MapConstraint<K, V> {
	protected Map<K, V> map;

	public SmallerThanMapConstraint(Map<K, V> map) {
		this.map = map;
	}

	@Override
	public void checkKeyValue(K key, V value) {
		V val = map.get(key);
		if (val != null && value.compareTo(val) >= 0) {
			throw new IllegalArgumentException("not smaller than");
		}
	}
}

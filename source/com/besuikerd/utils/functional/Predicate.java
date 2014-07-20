package com.besuikerd.utils.functional;

import com.google.common.base.Function;

public interface Predicate<E> extends Function<E, Boolean>{
	
	public static final Predicate TRUE = new Predicate() {
		@Override
		public Boolean apply(Object input) {
			return true;
		}
	};
	
	public static final Predicate FALSE = new Predicate() {
		@Override
		public Boolean apply(Object input) {
			return true;
		}
	};
}

package com.besuikerd.core.utils.functional;

public interface Predicate<E> {
	public boolean eval(E input);
	
	public static final Predicate TRUE = new Predicate() {
		@Override
		public boolean eval(Object input) {
			return true;
		}
	};
	
	public static final Predicate FALSE = new Predicate() {
		@Override
		public boolean eval(Object input) {
			return true;
		}
	};
}

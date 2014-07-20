package com.besuikerd.utils;

import static com.besuikerd.utils.functional.FunctionalUtils.functionGetClass;
import static com.besuikerd.utils.functional.FunctionalUtils.map;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.besuikerd.logging.BLogger;
import com.besuikerd.utils.functional.Predicate;
import com.google.common.primitives.Primitives;

public class ReflectUtils {
	
	public static interface Invokable<E>{
		public E invoke();
	}
	
	public static <E> Invokable<E> invokable(final Method m, final Object o, final Class<E> returnType, final Object... args){
		return m == null ? null : new Invokable<E>() {
			
			@Override
			public E invoke() {
				if(m.getParameterTypes().length <= args.length && returnType == null || m.getReturnType().equals(returnType)){
					try {
						return (E) m.invoke(o, Arrays.copyOfRange(args, 0, m.getParameterTypes().length));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				return null;
			}
		};
	}
	
	public static Invokable<Void> invokable(final Method m, final Object o, final Object... args){
		return invokable(m, o, null, args);
	}
	
	public static <E> E invoke(Invokable<E> invokable){
		if(invokable != null){
			return invokable.invoke();
		}
		return null;
	}
	
	public static Field getField(Object o, Class<?> cls, String name){
		try {
			return cls.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * unsafely access a field in a class and cast it to {@code <E>}
	 * @param o
	 * @param name
	 * @param cls
	 * @return
	 */
	public static <E> E accessField(Object o, Class<?> cls, String name, Class<E> cast){
		E field = null;
		try {
			Field f = getField(o, cls, name);
			f.setAccessible(true);
			field = cast.cast(f.get(o));
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return field;
	}
	
	public static <E> E accessField(Object o, String name, Class<E> cast){
		return accessField(o, o.getClass(), name, cast);
	}
	
	public static void modifyField(Object o, Class<?> cls, String name, Object newValue){
		Field f = getField(o, cls, name);
		f.setAccessible(true);
		try {
			f.set(o, newValue);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void modifyField(Object o, String name, Object newValue){
		modifyField(o, o.getClass(), name, newValue);
	}
	
	public static void invoke(Object obj, String name, Object... params){
		try {
			Method m = obj.getClass().getDeclaredMethod(name);
			m.setAccessible(true);
			m.invoke(obj, params);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <E> E newInstance(Class<E> cls, Object... args){
		return newInstance(cls, map(Class.class, args, functionGetClass), args);
	}
	
	public static <E> E newInstance(Class<E> cls, Class<?>[] parameters, Object... args){
		E instance = null;
		try {
			
			for(Constructor<?> c : cls.getDeclaredConstructors()){
				
				boolean matchingConstructor = c.getParameterTypes().length == parameters.length;
				Class<?>[] tClasses = c.getParameterTypes();
				for(int i = 0 ; i < tClasses.length && i < parameters.length && matchingConstructor ; i++){
					Class<?> tClass = tClasses[i];
					if(!tClass.isAssignableFrom(parameters[i])){
						matchingConstructor = false;
					}
				}
				if(matchingConstructor){
					instance = (E) c.newInstance(args);
					break;
				}
			}
			if(instance == null){
				throw new RuntimeException(String.format("No matching constructor found with the following params: %s", Arrays.toString(parameters)));
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	public static Set<Method> getMatchingMethods(Object o, String functionName){
		Set<Method> result = new HashSet<Method>();
		for(Method m : o.getClass().getMethods()){
			if(m.getName().equals(functionName)){
				result.add(m);
			}
		}
		return result;
	}
	
	/**
	 * find the method in Object o that matches the given function name and (partially) matches the given arguments. The best match (if any) will be returned
	 * @param o
	 * @param methods
	 * @param returnType
	 * @param args
	 * @return
	 */
	public static Method findPartialMatchingMethod(final Object o, Set<Method> methods, Class<?> returnType, final Object... args){
		Method bestMethod = null;
		for(Method m : methods){
			Class<?>[] pTypes = m.getParameterTypes();
			if(args.length >= pTypes.length){
				
				boolean match = pTypes.length <= args.length && returnType == null || returnType.equals(m.getReturnType());
				
				for(int i = 0 ; i < pTypes.length && match ; i++){
					Class<?> argCls = Primitives.unwrap(args[i].getClass());
					Class<?> paramCls = Primitives.unwrap(pTypes[i]);
					match = paramCls.isAssignableFrom(argCls);
				}
				
				if(match){
					if(bestMethod == null || pTypes.length > bestMethod.getParameterTypes().length){
						bestMethod = m;
					} else if(pTypes.length == bestMethod.getParameterTypes().length){
						int compScore = 0;
						int bestScore = 0;
						Class<?>[] bestTypes = bestMethod.getParameterTypes();
						
						//calculate competing method score
						for(int i = 0 ; i < pTypes.length ; i++){
							if(bestTypes[i].isAssignableFrom(pTypes[i])){
								compScore ++;
							}
						}
						
						//calculate current best method score
						for(int i = 0 ; i < pTypes.length ; i++){
							if(pTypes[i].isAssignableFrom(bestTypes[i])){
								bestScore ++;
							}
						}
						
						if(compScore > bestScore){
							bestMethod = m;
						}
						
						
					}
					
				}
			}
		}
		return bestMethod;
	}
	
	
	public static Method findPartialMatchingMethod(final Object o, String functionName, Class<?> returnType, final Object... args){
		return findPartialMatchingMethod(o, getMatchingMethods(o, functionName), returnType, args);
	}
	
	public static Method findPartialMatchingMethod(final Object o, String functionName, final Object... args){
		return findPartialMatchingMethod(o, functionName, null, args);
	}
	
	public static <E> Invokable<E> getPartialMatchingInvokable(final Object o, Set<Method> methods, Class<E> returnType, final Object... args){
		return invokable(findPartialMatchingMethod(o, methods, returnType, args), o, returnType, args);
	}
	
	public static Invokable<Void> getPartialMatchingInvokable(final Object o, Set<Method> methods, final Object... args){
		return getPartialMatchingInvokable(o, methods, null, args);
	}
	
	public static <E> Invokable<E> getPartialMatchingInvokable(final Object o, String functionName, final Class<E> returnType, final Object... args){
		return getPartialMatchingInvokable(o, getMatchingMethods(o, functionName), returnType, args);
	}
	
	public static Invokable<Void> getPartialMatchingInvokable(final Object o, String functionName, final Object... args){
		return getPartialMatchingInvokable(o, functionName, null, args);
	}
	
	public static <E> E invokePartialMatchingMethod(Object o, String functionName, Class<E> returnType, Object... args){
		return invoke(getPartialMatchingInvokable(o, functionName, returnType, args));
	}
	
	public static void invokePartialMatchingMethod(Object o, String functionName, Object... args){
		invokePartialMatchingMethod(o, functionName, null, args);
	}
	
	public static <E extends Annotation> Set<Method> getAnnotatedMethods(Object o, Class<E> annotation, Predicate<E> predicate){
		Set<Method> methods = new HashSet<Method>();
		for(Method m : o.getClass().getMethods()){
			if(m.isAnnotationPresent(annotation) && predicate.apply(m.getAnnotation(annotation))){
				methods.add(m);
			}
		}
		return methods;
	}
	
	public static <E extends Annotation> Set<Method> getAnnotatedMethods(Object o, Class<E> annotation){
		return getAnnotatedMethods(o, annotation, Predicate.TRUE);
	}
	
	public static <E, A extends Annotation> Invokable<E> getBestMatchingAnnotatedMethodInvokable(Object o, Class<A> annotation, Predicate<A> predicate, Class<E> returnType, Object... args){
		return invokable(findPartialMatchingMethod(o, getAnnotatedMethods(o, annotation, predicate), returnType, args), o, returnType, args);
	}
	
	public static <E, A extends Annotation> Invokable<E> getBestMatchingAnnotatedMethodInvokable(Object o, Class<A> annotation, Class<E> returnType, Object... args){
		return getBestMatchingAnnotatedMethodInvokable(o, annotation, Predicate.TRUE, returnType, args);
	}
	
	public static <A extends Annotation> Invokable<Void> getBestMatchingAnnotatedMethodInvokable(Object o, Class<A> annotation, Predicate<A> predicate, Object... args){
		return getBestMatchingAnnotatedMethodInvokable(o, annotation, predicate, null, args);
	}
	
	public static <A extends Annotation> Invokable<Void> getBestMatchingAnnotatedMethodInvokable(Object o, Class<A> annotation, Object... args){
		return getBestMatchingAnnotatedMethodInvokable(o, annotation, (Class) null, args);
	}
	
	public static <E, A extends Annotation> E invokeBestMatchingAnnotatedMethod(Object o, Class<A> annotation, Predicate<A> predicate, Class<E> returnType, Object... args){
		return invoke(getBestMatchingAnnotatedMethodInvokable(o, annotation, predicate, returnType, args));
	}
	
	public static <A extends Annotation> void invokeBestMatchingAnnotatedMethod(Object o, Class<A> annotation, Predicate<A> predicate, Object... args){
		invokeBestMatchingAnnotatedMethod(o, annotation, predicate, null, args);
	}
}

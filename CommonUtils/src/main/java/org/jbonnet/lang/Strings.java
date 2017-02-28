package org.jbonnet.lang;

import java.lang.reflect.Method;
import java.util.function.Function;

public class Strings {
	/**
	 * Créer une chaine contenant les élément du tableau séparé par sep.
	 * 
	 * @return
	 */
	public static String implode(String[] strs, String sep) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			builder.append(strs[i]);
			if (i != strs.length - 1) {
				builder.append(sep);
			}
		}
		return builder.toString();
	}
	
	/**
	 * Créer une chaine contenant les élément du tableau séparé par sep.
	 * 
	 * @return
	 */
	public static<T> String implode(T[] strs , Function<T,String> toString, String sep) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			builder.append(toString.apply(strs[i]));
			if (i != strs.length - 1) {
				builder.append(sep);
			}
		}
		return builder.toString();
	}

	/**
	 * Créer une chaine contenant a et b de la forme ababa avec nb a.
	 * 
	 * @return
	 */
	public static String implode(String a, String b, int nb) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < nb; i++) {
			builder.append(a);
			if (i != nb - 1) {
				builder.append(b);
			}
		}
		return builder.toString();
	}

	public static Object convertTo(Class<?> type, String text) {
		if( text == null) {
			return null;
		}
		if(type == String.class){
			return text;
		}
		try {
			Method method = type.getMethod("valueOf", String.class);
			return method.invoke(null, text);
		} catch (ReflectiveOperationException e) {
			
		}
		return null;
	}
}

package org.nanaki.util;

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
}

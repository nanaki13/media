package org.jbonnet.server;

public interface HandleError {
	public void catchMe(String message, Exception e,Class<?> c);
}

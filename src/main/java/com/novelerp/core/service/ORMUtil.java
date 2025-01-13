package com.novelerp.core.service;

public interface ORMUtil {
	
	public <T> T deepDeproxy(final Object maybeProxy) throws ClassCastException;
	/**
	 * De-proxy the potential proxy object.
	 * @param potentialProxy
	 * @return deproxied Object
	 */
	public <T> T deProxy(Object potentialProxy);
}

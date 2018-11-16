/**
 * 
 */
package com.teamsec.server.demo.config;

/**
 * @author admin
 *
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<>();

	public static void setDataSource(String type) {
		holder.set(type);
	}

	public static String getDataSource() {
		String lookUpKey = holder.get();
		return lookUpKey == null ? "masterDataSource" : lookUpKey;
	}

	public static void clear() {
		holder.remove();
	}
}

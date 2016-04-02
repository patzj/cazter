package org.cazter.api.dao;

/**
 * An interface that contains connection configuration constants for Hibernate.
 * @author patzj
 */
public interface HibernateCfgConstants {
	
	public final static String DB_DIALECT
			= "org.hibernate.dialect.MySQLDialect";
	
	public final static String ONLINE_DB_CONNECTION_URL = "jdbc:mysql://" 
			+ System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
			+ System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/"
			+ System.getenv("OPENSHIFT_APP_NAME");
	
	public final static String ONLINE_DB_CONNECTION_USERNAME 
			= System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
	
	public final static String ONLINE_DB_CONNECTION_PASSWORD
			= System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
	
	public final static String OFFLINE_DB_CONNECTION_URL = "jdbc:mysql://"
			+ "localhost:3306/cazter";
	
	public final static String OFFLINE_DB_CONNECTION_USERNAME = "root";
	
	public final static String OFFLINE_DB_CONNECTION_PASSWORD = "sesame";
	
	public final static int C3PO_MIN_SIZE = 5;
	
	public final static int C3PO_MAX_SIZE = 20;
	
	public final static int C3PO_TIMEOUT = 1800;
	
	public final static int C3PO_MAX_STATEMENTS = 50;
}

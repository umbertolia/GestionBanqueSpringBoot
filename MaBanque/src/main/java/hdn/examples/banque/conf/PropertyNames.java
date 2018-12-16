package hdn.examples.banque.conf;

public final class PropertyNames {

	private PropertyNames() {
	}

	public static final String APPLICATION_NAME = "${appli.nom}";

	public static final String APPLICATION_CONTEXT_FILE = "banqueContext.xml";

	public static final String APPLICATION_PROPERTIES_FILE = "banque.properties";
	
	public static final String APPLICATION_PROPERTIES_FILE_TEST = "banque-test.properties";
	
	// CONSTANTES DATABASE BANQUE

	public static final String DATABASE_BANQUE_PROPERTIES_PREFIX = "${datasource1_prefix}";

	public static final String DATABASE_BANQUE_URL = "${datasource1.jdbc-url}";

	public static final String DATABASE_BANQUE_USER = "${datasource1.username}";

	public static final String DATABASE_BANQUE_PWD = "${datasource1.password}";

	public static final String DATABASE_BANQUE_DRIVER_CLASS_NAME = "${com.mysql.jdbc.Driver}";

	// CONSTANTES DATABASE USER

	public static final String DATABASE_USERS_PROPERTIES_PREFIX = "${datasource2_prefix}";

	public static final String DATABASE_USERS_URL = "${datasource2.jdbc-url}";
	
	public static final String DATABASE_USERS_USER = "${datasource2.username}";

	public static final String DATABASE_USERS_PWD = "${datasource2.password}";

	public static final String DATABASE_USERS_DRIVER_CLASS_NAME = "${com.mysql.jdbc.Driver}";

}
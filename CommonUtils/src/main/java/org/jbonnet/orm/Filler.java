package org.jbonnet.orm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.jbonnet.bean.ObjectIOInterface;
import org.jbonnet.bean.csv.CsvToObject;

public class Filler {
	private Connection connection;

	private String sqlBase;
	private String sqlCondition = "";
	private PreparedStatement preparedStatement;

	private TypeMapping mapping;

	private Collection<String> currentFields;

	private ObjectIOInterface instance;
	private Map<String, String[]> idsMap = new HashMap();

	public Filler(Connection connection) {
		this.connection = connection;
	}

	private void initSatement() throws SQLException {
		if (preparedStatement != null && !preparedStatement.isClosed()) {
			preparedStatement.close();
		}
		preparedStatement = connection.prepareStatement(sqlBase + sqlCondition);
	}

	/**
	 * @return the preparedStatement
	 */
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public <T> List<T> getResultList(Class<T> clazz, Supplier<T> newT) throws SQLException {
		instance = ObjectIOInterface.Factory.getInstance(clazz);
		prepareQueryBase(instance);
		initSatement();
		ResultSet executeQuery = preparedStatement.executeQuery();
		List<T> ret = new ArrayList<>();
		while (executeQuery.next()) {

			T t = newT.get();
			for (String s : instance.getFields()) {
				instance.setTo(s, t, executeQuery.getObject(s));
			}
			ret.add(t);

		}
		preparedStatement.close();
		return ret;
	}

	private void prepareQueryBase(ObjectIOInterface instance) {
		sqlBase = instance.getFields().stream()
				.collect(Collectors.joining(", ", "SELECT ", " FROM " + instance.getClassName()));
		System.out.println(sqlBase);
	}

	public boolean entityTableExists(Class<?> instance) throws SQLException {
		try (ResultSet tables = connection.getMetaData().getTables(null, null, instance.getSimpleName(), null);) {
			return tables.next();

		}

	}

	public void createentityTable(Class<?> class1, TypeMapping typeMapping) throws SQLException {
		createEntityTable(class1, typeMapping, (String[]) null);

	}

	public void insertQuery() {
		Collection<String> fields = currentFields;
		int pCount = 1;
		StringBuilder p = new StringBuilder();

		for (String f : fields) {
			if (pCount != 1) {
				p.append(",$" + pCount);
			} else {
				p.append("$" + pCount);
			}

			pCount++;
		}

		sqlBase = instance.getFields().stream().collect(Collectors.joining(", ",
				"INSERT INTO " + instance.getClassName() + " (", ") values (" + p.toString() + ")"));

		System.out.println(sqlBase);
	}

	public void createEntityTable(Class<?> class1, TypeMapping typeMapping, String... ids) throws SQLException {
		StringBuilder builder = new StringBuilder();
		instance = ObjectIOInterface.Factory.getInstance(class1);
		builder.append("CREATE TABLE ").append(instance.getClassName()).append("( \n\t");
		Iterator<String> iterator = instance.getFields().iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			Class<?> type = instance.getType(next);
			String apply = typeMapping.apply(instance.getType(next));
			if (apply == null) {
				throw new SQLException("no type found for field : " + next + " class : " + type);
			}
			builder.append(next).append(" ").append(typeMapping.apply(type));
			if (iterator.hasNext()) {
				builder.append(",\n\t");
			}

		}
		if (ids != null) {

			builder.append(Arrays.asList(ids).stream().collect(Collectors.joining(",", ",\n\tPRIMARY KEY  (", ")\n")));
		}
		builder.append(");");
		System.out.println(builder.toString());
		try (Statement s = connection.createStatement();) {
			s.execute(builder.toString());
		}

	}

	public <T> void save(List<T> list, Class<T> class1) throws SQLException {
		instance = ObjectIOInterface.Factory.getInstance(class1);
		currentFields = instance.getFields();
		insertQuery();
		initSatement();
		int bachSize = 1000;
		int bachCount = 0;
		for (T t : list) {
			int ind = 1;
			for (String f : currentFields) {
				preparedStatement.setObject(ind, instance.getFrom(f, t));
				ind++;
			}
			preparedStatement.addBatch();
			bachCount++;
			if (bachSize == bachCount) {
				preparedStatement.executeBatch();
				bachCount = 0;
			}
		}
		if (bachSize != 0) {
			preparedStatement.executeBatch();
		}

	}

	public <T> List<T> getResultList(Class<T> class1) throws SQLException {
		return getResultList(class1, () -> {
			try {
				return class1.getConstructor().newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		});
	}

	public void toCsv(Class<?> clas, String filepath) throws SQLException, IOException {
		List<?> resultList = getResultList(clas);
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))){
			Collection<String> fields = instance.getFields();
			bufferedWriter.write(fields.stream().collect(Collectors.joining(";")));
			bufferedWriter.write('\n');
			
			for(Object o : resultList){
				boolean first = true;
				for (String f : fields) {
					if(!first){
						bufferedWriter.append(';');
					}
					first=false;
					Object from = instance.getFrom(f, o);
					if(from instanceof String){
						from = formatStringCsv((String) from);
					}
					bufferedWriter.write(String.valueOf(from));
				}
				bufferedWriter.write('\n');
			}
			
		}
		
	}

	private static String formatStringCsv(String from) {
		String ret = null;
		if(from != null){
			ret = from.replace("\"", "\\\"");
		}
		return ret;
	}

	public void drop(Class<?> drop) throws SQLException {
		sqlBase = "drop table " + drop.getSimpleName();
		initSatement();
		preparedStatement.execute();
		preparedStatement.close();
	}

	public void dropIfExists(Class<?> drop) throws SQLException {
		if (entityTableExists(drop)) {
			drop(drop);
		}
	}

	public <T> void loadDataFromCsv(String className, InputStreamReader resource)
			throws ClassNotFoundException, SQLException, IOException {
		Class<T> forName = (Class<T>) Class.forName(className);
		String lowerCase = forName.getSimpleName().toLowerCase();
		try (CsvToObject<T> csvToObject = new CsvToObject<>(resource, forName);) {
			List<T> list = csvToObject.toList();
			dropIfExists(forName);
			createEntityTable(forName, new SqlLiteTypeMapping(), idsMap.get(forName.getName()));
			save(list, forName);
		}

	}

	public void loadAllFromCsv(String basePackage) throws ClassNotFoundException, SQLException, IOException {
		ArrayList<Class> classes = getClasses(basePackage);
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		fillIdMap();
		if (cld == null) {
			throw new ClassNotFoundException("Can't get class loader.");
		}
		for (Class c : classes) {
			String path = "/data/";
			InputStream is = Filler.class.getResourceAsStream("/data/" + c.getSimpleName().toLowerCase() + ".csv");
			if (is != null) {
				InputStreamReader resource = new InputStreamReader(is);

				loadDataFromCsv(c.getName(), resource);
			}
		}

	}

	private void fillIdMap() throws IOException {
		idsMap.clear();
		try (InputStream is = Filler.class.getResourceAsStream("/data/data.properties");) {
			Properties properties = new Properties();
			properties.load(is);
			properties.forEach((k, v) -> {
				idsMap.put((String) k, ((String) v).split(","));
			});
		}

	}

	public static ArrayList<Class> getClasses(String pckgname) throws ClassNotFoundException {
		ArrayList<Class> classes = new ArrayList<Class>();
		// Get a File object for the package
		File directory = null;
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			String path = pckgname.replace('.', '/');
			URL resource = cld.getResource(path);
			if (resource == null) {
				throw new ClassNotFoundException("No resource for " + path);
			}
			directory = new File(resource.getFile());
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pckgname + " (" + directory + ") does not appear to be a valid package");
		}
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(pckgname + '.' + files[i].substring(0, files[i].length() - 6)));
				}
			}
		} else {
			throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
		}

		return classes;
	}

}

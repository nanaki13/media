/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import net.badway.db.test.User;

/**
 *
 * @author jonathan
 */
public class DataBase {

 
    private Connection connection;
    private String url;
    private String user;
    private String password;
//    private PreparedStatement preparedStatement;
    private HashMap<Class<?>, ObjectConvertor> cache = new HashMap<>();
//    private Statement statement;
    private Statement statment;

    public DataBase(String driverClass) throws ClassNotFoundException {
        
        Class.forName(driverClass);

    }

    public Connection getConnecion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public DataBase(String driverClass, String url, String user, String password) throws ClassNotFoundException {
        this(driverClass);
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Statement getStatment() throws SQLException {
        return getConnecion().createStatement();

    }

    public void closeConnection() throws SQLException {
        if(connection!=null && !connection.isClosed())
            connection.close();
    }

    public ResultSet executeQuery(String execute) throws SQLException {
        statment = getStatment();
        ResultSet executeQuery = statment.executeQuery(execute);
        return executeQuery;
    }

    public ResultSet executeQuesry(String sql, Object... param) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }
        return preparedStatement.executeQuery();
    }

    public int executeUpdate(String sql, Object... param) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }
        int executeUpdate = preparedStatement.executeUpdate();
        preparedStatement.close();
        return executeUpdate;
    }

    public int createTable(Class<?> clazz) throws SQLException, IncompleteObjectException {
        ObjectConvertor get = cache.get(clazz);
        if (get == null) {
            get = new ObjectConvertor(clazz, cache);
        }
        List<ObjectConvertor> creates = get.getListCreate();
        for (ObjectConvertor occ : creates) {
            executeUpdate(occ.getCreateTable());
        }
        String createTable = get.getCreateTable();
        return executeUpdate(createTable);
    }

    public int createDataBase(String database) throws SQLException {
        return executeUpdate("create database IF NOT EXISTS " + database);
    }

    public void use(String site_perso) throws SQLException {
        executeUpdate("use " + site_perso);
    }

    PreparedStatement getPreparedStatement(String sql) throws SQLException {

        return getConnecion().prepareStatement(sql);
    }

    public int save(Object p) throws IllegalArgumentException, IllegalAccessException, SQLException, IncompleteObjectException, InvocationTargetException {
        ObjectConvertor get = ckeckCache(p.getClass());
        PreparedStatement prepareInsert = get.prepareInsert(p, this);
        return prepareInsert.executeUpdate();

    }

    boolean exists(Object p) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return exists(p, p.getClass());
    }

    private boolean exists(Object p, Class<? extends Object> aClass) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ObjectConvertor oc = ckeckCache(aClass);
        PreparedStatement prepareSelectId = oc.prepareSelectId(p, this);

        ResultSet resultSet = prepareSelectId.executeQuery();
        return resultSet.next();
    }

    public <T, U> T select(Class<T> aClass, U id) throws IncompleteObjectException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ObjectConvertor oc = ckeckCache(aClass);
        T newInstance;
        PreparedStatement p = oc.prepareSelect(id, this);
        ResultSet executeQuery = p.executeQuery();
        newInstance = null;
        if (executeQuery.next()) {
            newInstance = aClass.newInstance();
            oc.fillObject(newInstance, executeQuery);

        }
        p.close();
        return newInstance;
    }

    public int dropTable(Class<?> aClass) throws SQLException, IncompleteObjectException {
        ObjectConvertor ckeckCache = ckeckCache(aClass);
        Statement statment = getStatment();
        int executeUpdate = statment.executeUpdate("DROP TABLE IF EXISTS " + ckeckCache.getTableName());
        statment.close();
        return executeUpdate;
    }

    private ObjectConvertor ckeckCache(Class<? extends Object> aClass) throws IncompleteObjectException {
        return ObjectConvertor.checkCache(aClass, cache);
    }

    public void update(Object select1) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ObjectConvertor ckeckCache = ckeckCache(select1.getClass());
        ckeckCache.update(select1, this);
    }

    public <T> void saveOrUpdate(T t) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        boolean exists = this.exists(t);
        if (exists) {
            update(t);
        } else {
            save(t);
        }
    }

    public<T> Collection<T> selectField(Class<T> select1, String field,Object value) throws IncompleteObjectException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ObjectConvertor ckeckCache = ckeckCache(select1);
        return ckeckCache.selectField(field,value,this);
    }
    
    

}

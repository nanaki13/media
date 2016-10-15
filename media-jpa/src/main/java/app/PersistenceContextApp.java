//package app;
//
//import java.util.Properties;
//
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContextType;
//import javax.sql.DataSource;
//
//import org.hibernate.SessionFactory;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//@Configuration()
//@EnableTransactionManagement
////@PropertySource("classpath:app.properties")
//public class PersistenceContextApp {
//
//    public PersistenceContextApp() {
//  
//    }
//
//    /**
//     * The method that configures the datasource bean
//     * */
//
////    @Resource
////    private Environment env;
////
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    DataSource dataSource(DataSourceProperties properties) {
////    	<property name="javax.persistence.jdbc.driver" value="org.sqlite.JDBC" />
////        <property name="javax.persistence.jdbc.url" value="jdbc:sqlite:/home/jonathan/database/media" />
////        <property name="javax.persistence.jdbc.user" value="" />
////        <property name="javax.persistence.jdbc.password" value="" />
//    return	properties.initializeDataSourceBuilder().build();
//    }
//    
//
//    /**
//     * The method that configures the entity manager factory
//     * @param datasource 
//     * */
//    @Bean
//    @PersistenceContext(type= PersistenceContextType.EXTENDED)
//    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource,EntityManagerFactoryBuilder builder) {
//       
//    	builder.dataSource(datasource).packages("model").
//    	
//    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(datasource);
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPackagesToScan("model");
////        entityManagerFactoryBean.setPersistenceXmlLocation("persistence.xml");
//        Properties jpaProperties = new Properties();
//
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
////        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
////        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
////        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//
//        entityManagerFactoryBean.setJpaProperties(jpaProperties);
//        entityManagerFactoryBean.afterPropertiesSet();
//    	
//        return entityManagerFactoryBean;
//    }
////    @Bean
////    public EntityManager entityManger(LocalContainerEntityManagerFactoryBean bean){
////    	
////    	return bean.getObject().createEntityManager();
////    }
//    @Bean
//    SessionFactory getSessionFactory(DataSource dataSource){
//    	 Properties jpaProperties = new Properties();
//
//         jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
//         jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
//    	return new LocalSessionFactoryBuilder( dataSource).addProperties(jpaProperties) .buildSessionFactory();
//    }
////    @Bean
////    HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
////    	HibernateTransactionManager hib= new HibernateTransactionManager(sessionFactory);
////
////
////    	return new HibernateTransactionManager(sessionFactory); 
////    }
//    
//    
//   
//}
package org.jbonnet;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {
	@Bean
	public int lenth() {
		return 15;
	}

	@Bean
	public int height() {
		return 15;
	}

	@Bean(initMethod="init")
	public FishEngine fishEngine(DbConnection con, @Qualifier("lenth") int length, @Qualifier("height") int height) {
		FishEngine fe = new FishEngine(length, height);
		fe.setDbConnection(con);
		return fe;
	}
	
	@Bean
	public DbConnection dbConnection() throws IOException, SQLException{
		return new DbConnection();
	}
}

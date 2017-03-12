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

	@Bean
	public FishEngine fishEngine(@Qualifier("lenth") int length, @Qualifier("height") int height) {
		return new FishEngine(length, height);
	}
	
	@Bean
	public DbConnection dbConnection() throws IOException, SQLException{
		return new DbConnection();
	}
}

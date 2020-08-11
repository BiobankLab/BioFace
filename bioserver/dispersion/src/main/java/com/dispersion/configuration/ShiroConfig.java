package com.dispersion.configuration;

import javax.sql.DataSource;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Autowired
	private DataSource ds;

	@Bean
	public Realm getRelm() {
		JdbcRealm realm = new JdbcRealm();
		realm.setPermissionsLookupEnabled(true);
		realm.setDataSource(ds);
		return realm;
	}

	@Bean
	protected CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}
}

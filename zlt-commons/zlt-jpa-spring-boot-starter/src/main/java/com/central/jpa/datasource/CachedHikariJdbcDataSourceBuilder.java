package com.central.jpa.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description com.inspur.edp.epp.dataengine.providers.Jdbc
 * Created by chengch
 * date on 2020/7/17
 */
public class CachedHikariJdbcDataSourceBuilder {
    private final static ConcurrentHashMap<String, DataSource> cache = new ConcurrentHashMap<>();
    /**
     * 默认datasource 连接池最大连接数量
     */
    private final static int DEFAULT_DATASOURCE_POOL_SIZE = 1;

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public CachedHikariJdbcDataSourceBuilder url(String url){
        this.url = url;
        return this;
    }
    public CachedHikariJdbcDataSourceBuilder username(String username){
        this.username = username;
        return this;
    }
    public CachedHikariJdbcDataSourceBuilder password(String password){
        this.password = password;
        return this;
    }
    public CachedHikariJdbcDataSourceBuilder driverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public DataSource build(){
        String cacheKey = this.url + "|"+this.username;
        if(cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        HikariConfig config = getHikariConfig();
        DataSource dataSource = new HikariDataSource(config);
        cache.put(cacheKey,dataSource);
        return dataSource;
    }

    private HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(this.driverClassName);
        config.setJdbcUrl(this.url);
        config.setUsername(this.username);
        config.setPassword(this.password);
        //TODO:后续需从配置文件里面进行配置
        config.setMaximumPoolSize(DEFAULT_DATASOURCE_POOL_SIZE);
        return config;
    }

    public static CachedHikariJdbcDataSourceBuilder create(){
        return new CachedHikariJdbcDataSourceBuilder();
    }



}

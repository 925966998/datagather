package com.ky.dbbak.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
@RefreshScope
@Configuration
@MapperScan(basePackages = "com.ky.dbbak.sourcemapper", sqlSessionTemplateRef = "sourceSqlSessionTemplate")
public class SourceDataSource {

    @Bean(name = "sourceData")
    @ConfigurationProperties(prefix = "spring.source.datasource") // application.properties中对应属性的前缀
    public DataSource outData() {
        return new DruidDataSource();
    }

    @Bean(name = "sourceSqlSessionFactory")
    public SqlSessionFactory sourceSqlSessionFactory(@Qualifier("sourceData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/source/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "sourceTransactionManager")
    public DataSourceTransactionManager sourceTransactionManager(@Qualifier("sourceData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sourceSqlSessionTemplate")
    public SqlSessionTemplate sourceSqlSessionTemplate(@Qualifier("sourceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
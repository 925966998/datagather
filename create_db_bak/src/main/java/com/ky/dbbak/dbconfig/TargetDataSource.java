package com.ky.dbbak.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ky.dbbak.targetmapper", sqlSessionTemplateRef = "targetSqlSessionTemplate")
public class TargetDataSource {

    @Bean(name = "targetData")
    @ConfigurationProperties(prefix = "spring.target.datasource") // application.properties中对应属性的前缀
    public DataSource outData() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "targetSqlSessionFactory")
    public SqlSessionFactory targetSqlSessionFactory(@Qualifier("targetData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/target/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "targetTransactionManager")
    public DataSourceTransactionManager targetTransactionManager(@Qualifier("targetData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "targetSqlSessionTemplate")
    public SqlSessionTemplate targetSqlSessionTemplate(@Qualifier("targetSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
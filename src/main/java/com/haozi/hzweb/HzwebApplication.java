package com.haozi.hzweb;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.haozi.hzweb.bean.*")
@ServletComponentScan
@EnableTransactionManagement
public class HzwebApplication{

	public static void main(String[] args) {
		SpringApplication.run(HzwebApplication.class, args);
	}

}

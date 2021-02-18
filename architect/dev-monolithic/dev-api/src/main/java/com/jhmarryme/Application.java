package com.jhmarryme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * description: 
 * @author JiaHao Wang
 * @date 2021/1/26 22:57
 */
@SpringBootApplication
// 扫描mybatis 通用mapper 所在的包
@MapperScan(basePackages = "com.jhmarryme.mapper")
@ComponentScan(basePackages = {"com.jhmarryme", "org.n3r.idworker"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

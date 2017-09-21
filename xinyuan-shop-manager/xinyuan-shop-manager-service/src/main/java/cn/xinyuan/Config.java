package cn.xinyuan;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.xinyuan.shop.manager.config.MyBatisConfig;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Created by josli on 17/9/21.
 */
//@Configuration
//@AutoConfigureAfter(MyBatisConfig.class) //保证在MyBatisConfig实例化之后再实例化该类
//@ComponentScan(basePackages="cn.xinyuan")
public class Config {

//    // mapper接口的扫描器
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("cn.xinyuan.shop.dao");
//        return mapperScannerConfigurer;
//    }

}

package cn.xinyuan.shop.manager;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;


@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "cn.xinyuan.shop.manager")
public class SpringBootMybatiesDemo1ApplicationTests {
	private Logger logger = LoggerFactory.getLogger(SpringBootMybatiesDemo1ApplicationTests.class);

	@Autowired
	private DataSource dataSource;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testDatas(){
		Assert.assertNotNull(dataSource);
		logger.info("begin");
	}
}

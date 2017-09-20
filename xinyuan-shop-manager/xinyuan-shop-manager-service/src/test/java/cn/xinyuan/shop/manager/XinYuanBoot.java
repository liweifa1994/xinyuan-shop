package cn.xinyuan.shop.manager;


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
@SpringBootTest()
@ComponentScan("cn.xinyuan.shop.manager")
public class XinYuanBoot {
	private Logger logger = LoggerFactory.getLogger(XinYuanBoot.class);

	@Autowired
	private DataSource dataSource;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testDatas(){
		//Assert.assertNotNull(dataSource);
		logger.info("begin");
	}
}

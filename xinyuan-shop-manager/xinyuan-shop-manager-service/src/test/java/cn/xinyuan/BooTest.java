package cn.xinyuan;

import cn.xinyuan.shop.manager.config.XinYuanShopBusinessProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xinyuan.shop.dao.mapper.TbItemMapper;
import cn.xinyuan.shop.model.common.util.JsonUtils;

/**
 * Created by josli on 17/9/21.
 */
@RunWith(SpringRunner.class) //需要使用该注解来加载spring容器
@SpringBootTest
//@ContextConfiguration(classes=Config.class)
@ComponentScan("cn.xinyuan")
public class BooTest {
    private Logger logger = LoggerFactory.getLogger(BooTest.class);
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private XinYuanShopBusinessProperties xinYuanProperties;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    public void testItem() {
        logger.info(JsonUtils.toJSONString(itemMapper.selectByPrimaryKey(536563l)));
    }


    @Test
    public void redisTest(){
        logger.info(redisTemplate.opsForValue().get("baidu"));
    }


    @Test
    public void testProperties(){
        System.out.println(xinYuanProperties.getItemKey());
        System.out.println(xinYuanProperties.getItemDesc());
        System.out.println(xinYuanProperties.getItemInfo());
        System.out.println(xinYuanProperties.getItemExpire());
    }
}

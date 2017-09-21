package cn.xinyuan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xinyuan.shop.dao.mapper.TbItemMapper;
import cn.xinyuan.shop.model.common.util.JsonUtils;

/**
 * Created by josli on 17/9/21.
 */
@RunWith(SpringRunner.class) //需要使用该注解来加载spring容器
@SpringBootTest
//@ContextConfiguration(classes=Config.class)
public class BooTest {
    private Logger logger = LoggerFactory.getLogger(BooTest.class);
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    public void testItem() {
        logger.info(JsonUtils.toJSONString(itemMapper.selectByPrimaryKey(536563l)));
    }


    @Test
    public void redisTest(){

        System.out.println(redisTemplate.opsForValue().get("baidu"));
    }
}

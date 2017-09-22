package cn.xinyuan.shop.manager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by josli on 17/9/22.
 */
@PropertySource("classpath:config/xinyuan_shop_redis.properties")
@Configuration //表明配置文件
@ConfigurationProperties(prefix = "xinyuan.shop.redis.key")
@Getter
@Setter
public class XinYuanShopBusinessProperties {
    private String itemKey;
    private String itemInfo;
    private String itemDesc;
    private Long itemExpire;

}

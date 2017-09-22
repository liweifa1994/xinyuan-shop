package cn.xinyuan.shop.manager.service.impl;

import cn.xinyuan.shop.dao.mapper.TbItemDescMapper;
import cn.xinyuan.shop.dao.mapper.TbItemMapper;
import cn.xinyuan.shop.manager.config.XinYuanShopBusinessProperties;
import cn.xinyuan.shop.manager.service.ItemService;
import cn.xinyuan.shop.model.common.util.Constent;
import cn.xinyuan.shop.model.common.util.EasyUIDataGridResult;
import cn.xinyuan.shop.model.common.util.XinYuanResult;
import cn.xinyuan.shop.model.common.util.exception.ExceptionFactoryUtil;
import cn.xinyuan.shop.model.pojo.TbItem;
import cn.xinyuan.shop.model.pojo.TbItemDesc;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by josli on 17/9/21.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(ItemCatServiceImpl.class);
    @Autowired
    private XinYuanShopBusinessProperties shopBusinessProperties;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;
    /**
     * 实现逻辑如下
     * 1、先去缓存系统中查询是否由该商品的信息 如果有直接返回
     * 2、如果缓存系统中没有该商品信息，则直接查询数据
     * 3、查询到商品信息之后，将该商品添加到Redis缓存系统中 并且设置过期时间
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(long itemId) {
        //通用的校验
        //1、先去缓存系统中查询是否由该商品的信息 如果有直接返回
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(shopBusinessProperties.getItemKey());
        stringBuffer.append(Constent.fenHao);
        stringBuffer.append(itemId);
        String key = stringBuffer.toString();
        TbItem tbItem = (TbItem) opsForValue.get(key);
        if(tbItem != null){
            return tbItem;
        }
        //2、如果缓存系统中没有该商品信息，则直接查询数据
        tbItem = itemMapper.selectByPrimaryKey(itemId);
        //3、查询到商品信息之后，将该商品添加到Redis缓存系统中 并且设置过期时间
        if(tbItem != null){
            opsForValue.set(key,tbItem);
            //设置过期时间
            redisTemplate.expire(key,shopBusinessProperties.getItemExpire(), TimeUnit.SECONDS);
            return tbItem;
        }
        return null;
    }

    @Override
    public EasyUIDataGridResult itemList(int page, int rows) {
        // TODO Auto-generated method stub
        PageHelper.startPage(page, rows);
        //查询数据库
        // 1.1 生成 tbitemexample
        //1.2 使用itemmapper查询
        List<TbItem> list = itemMapper.selectAll();
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getSize());
        result.setRows(pageInfo.getList());
        return result;
    }

    @Override
    public XinYuanResult addItem(TbItem item, String desc) {
        long itemId = Long.parseLong("");
        item.setId(itemId);
        //1 正常 2 下架 3 删除
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //添加商品
        itemMapper.insert(item);
        //添加商品描述信息
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setUpdated(new Date());
        itemDesc.setCreated(new Date());
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDescMapper.insert(itemDesc);
        //发送消息给队列 让商品信息导入到索引库中去
       // searchItemSolrRabbitmq.convertAndSend(rabbitmqQueue,item);
        return XinYuanResult.ok();
    }

    /**
     * 实现逻辑如下
     * 1、先去缓存系统中查询是否由该商品的描述信息 如果有直接返回
     * 2、如果缓存系统中没有该商品描述信息，则直接查询数据库
     * 3、查询到商品描述信息之后，将该商品描述信息添加到Redis缓存系统中 并且设置过期时间
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc getItemDescById(long itemId) {
        //通用的校验

        //1、先去缓存系统中查询是否由该商品的信息 如果有直接返回
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(shopBusinessProperties.getItemDesc());
        stringBuffer.append(Constent.fenHao);
        stringBuffer.append(itemId);
        String key = stringBuffer.toString();
        TbItemDesc tbItemDesc = null;
        try {
            tbItemDesc = (TbItemDesc) opsForValue.get(key);
            if(tbItemDesc != null){
                return tbItemDesc;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            ExceptionFactoryUtil.createResultException("从redis中查询数据失败");
        }
        //2、如果缓存系统中没有该商品信息，则直接查询数据
        tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        //3、查询到商品信息之后，将该商品添加到Redis缓存系统中 并且设置过期时间
        if(tbItemDesc != null){
            opsForValue.set(key,tbItemDesc);
            //设置过期时间
            redisTemplate.expire(key,shopBusinessProperties.getItemExpire(),TimeUnit.SECONDS);
            return tbItemDesc;
        }
        return null;
    }
}

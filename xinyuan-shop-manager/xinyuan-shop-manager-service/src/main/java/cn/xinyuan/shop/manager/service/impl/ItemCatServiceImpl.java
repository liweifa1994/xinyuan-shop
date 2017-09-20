package cn.xinyuan.shop.manager.service.impl;

import cn.xinyuan.shop.dao.mapper.TbItemCatMapper;
import cn.xinyuan.shop.manager.service.ItemCatService;
import cn.xinyuan.shop.model.common.util.Constent;
import cn.xinyuan.shop.model.common.util.EasyUITreeNode;
import cn.xinyuan.shop.model.common.util.exception.ExceptionFactoryUtil;
import cn.xinyuan.shop.model.pojo.TbItemCat;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josli on 17/9/20.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    private Logger logger = LoggerFactory.getLogger(ItemCatServiceImpl.class);
    @Autowired
    private TbItemCatMapper itemCatMapper;
    public List<EasyUITreeNode> findItemCatList(Long parentId) {
        logger.info("{ param  parentId }"+parentId);
        if (parentId == null){
            logger.error("父级的id为空");
            ExceptionFactoryUtil.createDataNullException("父级的id 不能为空");
        }
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setParentId(parentId);
        List<TbItemCat> tbItemCatList = itemCatMapper.select(tbItemCat);
        if (tbItemCatList != null && tbItemCatList.size()>0){
            List<EasyUITreeNode>  result = Lists.newArrayList();
            for (TbItemCat itemCat :tbItemCatList){
                EasyUITreeNode treeNode = new EasyUITreeNode();
                //判断是否是父亲节点
                treeNode.setState(itemCat.getIsParent()? Constent.closed:Constent.open);
                treeNode.setText(itemCat.getName());
                treeNode.setId(itemCat.getId());
                result.add(treeNode);
            }
            //
            logger.info(result.toString());
            return result;
        }else{
            logger.warn("数据在库中不能存在 ");
            ExceptionFactoryUtil.createResultException("数据不存在");
        }
        return null;
    }
}

package cn.xinyuan.shop.manager.service;

import cn.xinyuan.shop.model.common.util.EasyUITreeNode;

import java.util.List;

/**
 * Created by josli on 17/9/20.
 */
public interface ItemCatService {
    /**
     * 根据父节点获取子类目
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> findItemCatList(Long parentId);

}

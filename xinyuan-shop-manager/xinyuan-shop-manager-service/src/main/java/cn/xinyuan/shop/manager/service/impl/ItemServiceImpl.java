package cn.xinyuan.shop.manager.service.impl;

import cn.xinyuan.shop.manager.service.ItemService;
import cn.xinyuan.shop.model.common.util.EasyUIDataGridResult;
import cn.xinyuan.shop.model.common.util.XinYuanResult;
import cn.xinyuan.shop.model.pojo.TbItem;
import cn.xinyuan.shop.model.pojo.TbItemDesc;
import org.springframework.stereotype.Service;

/**
 * Created by josli on 17/9/21.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public TbItem getItemById(long itemId) {
        return null;
    }

    @Override
    public EasyUIDataGridResult itemList(int page, int rows) {
        return null;
    }

    @Override
    public XinYuanResult addItem(TbItem item, String desc) {
        return null;
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        return null;
    }
}

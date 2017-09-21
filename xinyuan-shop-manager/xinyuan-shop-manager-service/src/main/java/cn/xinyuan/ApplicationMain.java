package cn.xinyuan;

import cn.xinyuan.shop.dao.mapper.TbItemMapper;
import cn.xinyuan.shop.manager.service.ItemCatService;
import cn.xinyuan.shop.model.common.util.EasyUITreeNode;
import cn.xinyuan.shop.model.pojo.TbItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by josli on 17/9/21.
 */
@SpringBootApplication
@RestController
public class ApplicationMain {
    @Autowired
    private ItemCatService itemCatService;
    
    @Autowired
    private TbItemMapper itemMapper;
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class,args);
    }

    @RequestMapping("itemCats")
    public List<EasyUITreeNode> get(){
        return itemCatService.findItemCatList(0l);
    }
    
    @RequestMapping("items")
    public List<TbItem> getItem(){
        return itemMapper.selectAll();
    }
}

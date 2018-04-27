package com.kenya.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kenya.bean.Goods;
import com.kenya.bean.GoodsCustom;
import com.kenya.bean.User;
import com.kenya.service.GoodsService;
import com.kenya.service.UserService;

/**
 * 商品控制器
 * @author Administrator
 *
 */
@Controller  
@RequestMapping("/Goods") 
public class GoodsController {
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	@Autowired
	GoodsService goodsService;
	@RequestMapping("/selectByFile")
	//
	@ResponseBody
	public  HashMap<String, Object> SelectByFile(Integer id,@RequestParam(value="goodsName",defaultValue="") String goodsName,@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) throws Exception  {
		Map<String, Object> map = new HashMap<String, Object>();
        if(goodsName!=null&&!goodsName.equals("")){  
                map.put("goodsName", goodsName);     
        }
        if(id!=null&&!id.equals("0")){
            map.put("goodsId", id);
        }
        List<GoodsCustom> goods = goodsService.selectByfild(map,pn);
        HashMap<String, Object> goodes = new HashMap<String, Object>();
        if(goodsService.selectByFildCount(map)%7==0  ? goodsService.selectByFildCount(map)/7<=pn : goodsService.selectByFildCount(map)/7<pn) {
        	goodes.put("code","040");
        }else {
        	goodes.put("code","000");
        }
        goodes.put("result",goods);
        return goodes; 
	}

	
}

package com.skycloud.biz;

import com.alibaba.fastjson.JSONObject;
import com.skycloud.entity.Xici;
import com.skycloud.mapper.XiciMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.DriverManager;
import java.util.List;

/**
 * Created by angel on 2019/1/4.
 */
@Service
public class XiciBiz {

    @Resource
    private XiciMapper xiciMapper;


    public JSONObject getXiciList(String ip){

        JSONObject jsonObject = new JSONObject();
        List<Xici> xiciLest = xiciMapper.getXiciList(ip);
        jsonObject.put("list", xiciLest);
        jsonObject.put("size", xiciLest.size());
        return jsonObject;
    }

}

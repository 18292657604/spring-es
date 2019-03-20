package com.skycloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.skycloud.biz.ReportBiz;
import com.skycloud.biz.XiciBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 西刺
 * @author angel
 * @date 2019/1/4
 */
@RestController
@RequestMapping("xici")
public class XiciController {

    @Autowired
    XiciBiz xiciBiz;

    @GetMapping("getXiciList")
    public JSONObject getXiciList(String ip){
        return xiciBiz.getXiciList(ip);
    }

    @CrossOrigin(origins = {"http://localhost:63342", "null"})
    @GetMapping("saveHtml")
    public JSONObject getUrl(String ip, Integer resourceType){

        JSONObject jsonObject = new JSONObject();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("IP", ip);
        paramMap.put("RESOURCE_TYPE", resourceType==null?0:resourceType);

        try {
            ReportBiz.saveReport(paramMap);
            jsonObject.put("success", 1);
        }catch (Exception e){
            jsonObject.put("error", 0);
        }
        return jsonObject;

    }

}

package com.skycloud.biz;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author angel
 * @date 2019/3/14
 */
public class ReportBiz {


    private static String realPath = "C:/apache-tomcat-8.0.47/webapps/ROOT/report8.jasper";
    private static String url = "jdbc:mysql://localhost:3306/unified_portal";

    /**
     * 生成报表
     * @return
     */
    public static void saveReport(Map<String, Object> paramMap) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, "root", "root");

        JasperRunManager.runReportToHtmlFile(realPath, paramMap, connection);

        System.out.println("=======html生成完毕========");

    }

    public static void main(String[] args) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("IP", "172.18.16.100");
        paramMap.put("RESOURCE_TYPE", 1);
        saveReport(paramMap);
    }



}

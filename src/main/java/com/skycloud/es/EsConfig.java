package com.skycloud.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregator;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentiles;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentilesAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * es 操作
 * @author lsy
 * @date 2019/1/5
 */
public class EsConfig {

    /**
     * es初始化
     */
    public static TransportClient getClient(String index, String type) throws UnknownHostException {

        System.out.println("=====es init=====");

        Settings settings = Settings.builder()
                .put("cluster.name","lsy_elastic_cluster").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.250.101"),9300));


        System.out.println("=====es init finishs=====");


        return client;
    }

    private static SearchResponse getSearch() throws UnknownHostException {

        //初始化es客户端
        TransportClient client = getClient("database", "db2_space");
        // 求出最小的值
        //MinAggregationBuilder aggregationBuilder = AggregationBuilders.min("min_rows").field("rows");
        // 求出最大的值
        //MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("min_rows").field("rows");
        // 求出总数
//        SumAggregationBuilder aggregationBuilder = AggregationBuilders.sum("min_rows").field("week");
        //求出平均值
//        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg("min_rows").field("rows");
        //聚合统计
//        StatsAggregationBuilder aggregationBuilder = AggregationBuilders.stats("temp_rows").field("rows");
        //可扩展的聚合统计
//        ExtendedStatsAggregationBuilder aggregationBuilder = AggregationBuilders.extendedStats("temp_rows").field("rows");

        PercentilesAggregationBuilder aggregationBuilder = AggregationBuilders.percentiles("tem_rows").field("rows")
                .percentiles(50.0, 80.0);

        SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregationBuilder).execute().actionGet();

        return searchResponse;
    }


    public static void main(String[] args) {
        try{
            //搜素语句
            SearchResponse searchResponse = getSearch();

            Percentiles percentiles = searchResponse.getAggregations().get("temp_rows");

            for (Percentile entry: percentiles) {
                double perccent = entry.getPercent();
                double value = entry.getValue();
                System.out.println("percent[{}]"+ perccent);
                System.out.println("value[]:"+value);
            }



            /*
            String max = stats.getMaxAsString();
            String min = stats.getMinAsString();
            String sum = stats.getSumAsString();
            String avg = stats.getAvgAsString();

            System.out.println(stats.getStdDeviation());
            System.out.println(stats.getSumOfSquares());
            System.out.println(stats.getVariance());


            System.out.println("最大数是：" +max);
            System.out.println("最小数是：" +min);
            System.out.println("总数是：" +sum);
            System.out.println("平均数是：" +avg);
            */
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }


}

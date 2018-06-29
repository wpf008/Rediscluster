package com.wpf.rediscluster.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:pengfei
 * @Description redsi config
 * @Date:Created in 14:33 2018/6/29
 * @Modified by
 */
@Configuration
public class RedisClusterConfig {
    @Value("${spring.redis.expireSeconds}")
    private int expireSeconds;
    @Value("${spring.redis.clusterNodes}")
    private String clusterNodes;
    @Value("${spring.redis.commandTimeout}")
    private int commandTimeout;
    /**
     * 注意:这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
     * @return JedisCluster
     */
    @Bean
    public JedisCluster getJedisCluster() {
        String[] serverArray = clusterNodes.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        return new JedisCluster(nodes, commandTimeout, 1000, 1, new GenericObjectPoolConfig());//需要密码连接的创建对象方式
    }
}

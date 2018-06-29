package com.wpf.rediscluster.controller;

import com.alibaba.fastjson.JSON;
import com.wpf.rediscluster.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @Author:pengfei
 * @Description 测试redis集群
 * @Date:Created in 14:39 2018/6/29
 * @Modified by
 */
@RestController
public class HelloController {
    @Autowired
    RedisClient redisClient;
    @GetMapping("/")
    public String hello(){
        redisClient.set("person", JSON.toJSONString(new Person(1,"wpf","11111@qq.com")));
        return redisClient.get("person");
    }

    class  Person implements Serializable{
        private int id;
        private String name;
        private String email;

        public Person(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }


}

package com.wpf.rediscluster;

import com.wpf.rediscluster.redis.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisclusterApplicationTests {
	@Autowired
	RedisClient redisClient;

	@Test
	public void TestRedisClient() {
		System.out.println(redisClient.get("person"));
	}

}

package com.hdm.springbootdemo;

import com.hdm.springbootdemo.model.AyMood;
import com.hdm.springbootdemo.model.AyUser;
import com.hdm.springbootdemo.service.AyMoodProducer;
import com.hdm.springbootdemo.service.AyMoodService;
import com.hdm.springbootdemo.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootdemoApplicationTests {

	@Test
	void contextLoads() {
	}


	@Resource
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AyUserService ayUserService;

	/**
	 * Mysql集成Spring Boot简单测试
	 */
	@Test
	public void mySqlTest() {
		String sql = "select id,name,password from ay_user";
		List<AyUser> userList =
				(List<AyUser>) jdbcTemplate.query(sql, new RowMapper<AyUser>() {
					@Override
					public AyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
						AyUser user = new AyUser();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
		System.out.println("查询成功：");
		for (AyUser user : userList) {
			System.out.println("【id】: " + user.getId() + "；【name】: " + user.getName());
		}
	}



	@Test
	public void testRepository() {
		//查询所有数据
		List<AyUser> userList = ayUserService.findAll();
		System.out.println("findAll() :" + userList.size());
		//通过name查询数据
		List<AyUser> userList2 = ayUserService.findByName("阿毅");
		System.out.println("findByName() :" + userList2.size());
		Assert.isTrue(userList2.get(0).getName().equals("阿毅"), "data error!");
		//通过name模糊查询数据
		List<AyUser> userList3 = ayUserService.findByNameLike("%毅%");
		System.out.println("findByNameLike() :" + userList3.size());
		Assert.isTrue(userList3.get(0).getName().equals("阿毅"), "data error!");
		//通过id列表查询数据
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		List<AyUser> userList4 = ayUserService.findByIdIn(ids);
		System.out.println("findByIdIn() :" + userList4.size());
		//分页查询数据
		PageRequest pageRequest = PageRequest.of(0, 10);
		Page<AyUser> userList5 = ayUserService.findAll(pageRequest);
		System.out.println("page findAll():" + userList5.getTotalPages() + "/" + userList5.getSize());
		//新增数据
		AyUser ayUser = new AyUser();
		ayUser.setId("3");
		ayUser.setName("test");
		ayUser.setPassword("123");
		ayUserService.save(ayUser);
		//删除数据
		ayUserService.delete("3");

	}

    @Test
    public void testTransaction(){
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("阿明");
        ayUser.setPassword("123");
        ayUserService.save(ayUser);
    }

    @Resource
	RedisTemplate redisTemplate;

	@Test
	public void testRedis() {
		redisTemplate.opsForValue().set("name", "ay");
		String name = (String)redisTemplate.opsForValue().get("name");
		System.out.println(name);
		//删除
		redisTemplate.delete("name");
		//更新
		redisTemplate.opsForValue().set("name", "ming");
		//查询
		String name1 = (String)redisTemplate.opsForValue().get("name");
	}

	@Test
	public void testRedis2() {
		AyUser ayUser = ayUserService.findById("1");
		System.out.println(ayUser);
	}


	@Test
	public void testMybatis() {
		AyUser user = ayUserService.findByNameAndPassword("阿华", "123");
		System.out.println(user);
	}

	@Resource
	private AyMoodService ayMoodService ;

	@Test
	public void testAyMood() {
		AyMood ayMood = new AyMood();
		ayMood.setId("1");
		ayMood.setContent("这是我第一条微信说说");
		ayMood.setUserId("1");
		ayMood.setPraiseNum(0);
		ayMood.setPublishTime(new Date());
		ayMoodService.save(ayMood);
	}


	@Resource
	private AyMoodProducer ayMoodProducer ;

	@Test
	public void testActiveMQ() {
		ActiveMQQueue activeMQQueue = new ActiveMQQueue("ay.queue");
		ayMoodProducer.sendMessage(activeMQQueue,"我生气了，这键盘太烂了！！！");
	}

	@Test
	public void testActiveMQAsynSave() {

		AyMood ayMood = new AyMood();
		ayMood.setId("2");
		ayMood.setContent("垃圾键盘");
		ayMood.setUserId("1");
		ayMood.setPraiseNum(0);
		ayMood.setPublishTime(new Date());
		ayMoodService.asynSave(ayMood);
		System.out.println("异步发表说说");

	}


}

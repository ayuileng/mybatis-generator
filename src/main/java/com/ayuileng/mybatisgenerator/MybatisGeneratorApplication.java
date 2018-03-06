package com.ayuileng.mybatisgenerator;

import com.ayuileng.mybatisgenerator.dao.UserMapper;
import com.ayuileng.mybatisgenerator.model.User;
import com.ayuileng.mybatisgenerator.model.UserExample;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = {"com.ayuileng.mybatisgenerator.mapper"})
public class MybatisGeneratorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
    }

    @Autowired
    UserMapper userMapper;

    @Override
    public void run(String... args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setName("name"+i);
//            user.setAge(12+i);
//            user.setDescription("desc"+i);
//            user.setGrade(100+i);
//            user.setSalary(199+i);
//            userMapper.insert(user);
//        }
//        UserExample userExample = new UserExample();
//        System.out.println(userMapper.countByExample(userExample));
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAgeGreaterThanOrEqualTo(20);
        List<User> users = userMapper.selectByExample(userExample);
        users.forEach(System.out::println);
        UserExample userExample1 = new UserExample();
        //between 是闭区间
        userExample1.createCriteria().andGradeLessThan(104).andSalaryBetween(199,201);
        userMapper.selectByExample(userExample1).forEach(System.out::println);

    }
}

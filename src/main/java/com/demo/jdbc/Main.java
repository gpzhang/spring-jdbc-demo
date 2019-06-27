package com.demo.jdbc;

import com.demo.jdbc.entity.User;
import com.demo.jdbc.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author haishen
 * @date 2019/3/30
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-jdbc.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        User user = userService.selectUserById(1);
        System.out.println(user.getName());
        User newUser = new User();
        newUser.setName("jdbc");
        newUser.setAge(1000);
        boolean result = userService.insertUser(newUser);
        System.out.println(result);
    }
}

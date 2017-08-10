package com.yyc.rxjava2_retrofit2demo;

/**
 * @author: Page
 * @time: 17-8-10
 * @description: User实体类.
 */

public class User {
    /**
     * name : zhangsan
     * age : 12
     */

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

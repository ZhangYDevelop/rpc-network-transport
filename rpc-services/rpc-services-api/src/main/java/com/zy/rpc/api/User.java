
package com.zy.rpc.api;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2020-03-30  22:05
 */
public class User implements Serializable {

    private  String name;

    private  String age;

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

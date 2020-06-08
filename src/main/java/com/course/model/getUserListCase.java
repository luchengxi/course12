package com.course.model;

import lombok.Data;

@Data
public class getUserListCase {
    private int id;
    private String userName;
    private String age;
    private String sex;
    private String expected;

    public String toString(){
        return (
                "{id:"+id+","+
                        "userName:"+userName+","+
                        "expected:"+expected+"}"
        );
    }
}

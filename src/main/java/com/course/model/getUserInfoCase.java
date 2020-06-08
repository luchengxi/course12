package com.course.model;

import lombok.Data;

@Data
public class getUserInfoCase {
    private int id;
    private String userid;
    private String expected;

    public String toString(){
        return (
                "{id:"+id+","+ "expected:"+expected+"}"
        );
    }
}

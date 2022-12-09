package edu.global.ex.Dto;

public class ApplyDto {

    private String c_name;
    private String class_area;

    public ApplyDto() {}


    public ApplyDto(String c_name, String class_area) {
        this.c_name = c_name;
        this.class_area = class_area;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getClass_area() {
        return class_area;
    }

    public void setClass_area(String class_area) {
        this.class_area = class_area;
    }

}

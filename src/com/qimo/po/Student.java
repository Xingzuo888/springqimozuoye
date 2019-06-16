package com.qimo.po;

import org.hibernate.validator.constraints.NotEmpty;

public class Student {
    /**
     * 
     * 表字段 : student.id
     */
    private Byte id;

    /**
     * 
     * 表字段 : student.sno
     */
    @NotEmpty(message = "学号必须填写！")
    private String sno;

    /**
     * 
     * 表字段 : student.sname
     */
    @NotEmpty(message = "姓名必须填写！")
    private String sname;

    /**
     * 
     * 表字段 : student.sex
     */
    @NotEmpty(message = "性别必须选择！")
    private String sex;


    /**
     * 获取  字段:student.id
     *
     * @return student.id, 
     */
    public Byte getId() {
        return id;
    }

    /**
     * 设置  字段:student.id
     *
     * @param id the value for student.id, 
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * 获取  字段:student.sno
     *
     * @return student.sno, 
     */
    public String getSno() {
        return sno;
    }

    /**
     * 设置  字段:student.sno
     *
     * @param sno the value for student.sno, 
     */
    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    /**
     * 获取  字段:student.sname
     *
     * @return student.sname, 
     */
    public String getSname() {
        return sname;
    }

    /**
     * 设置  字段:student.sname
     *
     * @param sname the value for student.sname, 
     */
    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    /**
     * 获取  字段:student.sex
     *
     * @return student.sex, 
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置  字段:student.sex
     *
     * @param sex the value for student.sex, 
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }


}
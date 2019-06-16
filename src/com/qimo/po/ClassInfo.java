package com.qimo.po;

import java.util.List;

public class ClassInfo {
    /**
     * 
     * 表字段 : classinfo.classid
     */
    private Byte classid;

    /**
     * 
     * 表字段 : classinfo.classname
     */
    private String classname;

    /**
     * 
     * 表字段 : classinfo.depart
     */
    private String depart;

    private List<Student> students;

    /**
     * 获取  字段:classinfo.classid
     *
     * @return classinfo.classid, 
     */
    public Byte getClassid() {
        return classid;
    }

    /**
     * 设置  字段:classinfo.classid
     *
     * @param classid the value for classinfo.classid, 
     */
    public void setClassid(Byte classid) {
        this.classid = classid;
    }

    /**
     * 获取  字段:classinfo.classname
     *
     * @return classinfo.classname, 
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 设置  字段:classinfo.classname
     *
     * @param classname the value for classinfo.classname, 
     */
    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    /**
     * 获取  字段:classinfo.depart
     *
     * @return classinfo.depart, 
     */
    public String getDepart() {
        return depart;
    }

    /**
     * 设置  字段:classinfo.depart
     *
     * @param depart the value for classinfo.depart, 
     */
    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students=students;
    }
}
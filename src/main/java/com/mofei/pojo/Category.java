package com.mofei.pojo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Category implements Serializable {


    private Integer id;

    private String name;

    private Date createDate;


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setId(Integer id){this.id = id;}

    public Integer getId(){return this.id;}

    public void setName(String name){this.name = name;}

    public String getName(){return this.name;}

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }


}

package com.sandy.blog.greendaodemo.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sandy Luo on 2017/1/12.
 */

@Entity
public class Singer {
    @Id
    private Long id;
    @Property
    private String name;
    @Property
    private String sex;
    @Property
    private String country;
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1530037414)
    public Singer(Long id, String name, String sex, String country) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.country = country;
    }
    @Generated(hash = 242898301)
    public Singer() {
    }
}

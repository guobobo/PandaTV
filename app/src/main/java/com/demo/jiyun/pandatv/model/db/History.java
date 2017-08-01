package com.demo.jiyun.pandatv.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class History {
    @Id
    private Long id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "data")
    private String data;

    @Property(nameInDb = "duration")
    private String duration;

    @Property(nameInDb = "image")
    private String image;

    @Property(nameInDb = "url")
    private String url;
    
    @Property(nameInDb = "flag")
    private boolean flag;

    @Property(nameInDb = "visibility")
    private boolean visibility;

    @Generated(hash = 1316018874)
    public History(Long id, String title, String data, String duration,
            String image, String url, boolean flag, boolean visibility) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.duration = duration;
        this.image = image;
        this.url = url;
        this.flag = flag;
        this.visibility = visibility;
    }

    @Generated(hash = 869423138)
    public History() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

}

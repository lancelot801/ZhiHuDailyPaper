package com.xzit.zhihu.model;


import java.io.Serializable;
import java.util.List;

public class StoriesEntity implements Serializable {
    //序列化
    /**
     * id : 7047795
     * title : 央视说要干预男男性行为，具体是怎么干预法？
     * ga_prefix : 081310
     * images : ["http://pic3.zhimg.com/fe27abc8f094510f2d3b4f3706108b56.jpg"]
     * type : 0
     */
    private int id;
    private String title;
    private List<String> images;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String toString() {
        return "StoriesEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                /*", ga_prefix='" + ga_prefix + '\'' +*/
                ", images=" + images +
                ", type=" + type +
                '}';
    }
}

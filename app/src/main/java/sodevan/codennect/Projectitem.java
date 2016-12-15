package sodevan.codennect;

/**
 * Created by ravipiyush on 22/10/16.
 */

public class Projectitem {
    String name  ;
    String desc  ;
    String url ;
    String college  ;
    public Projectitem() {
    }

    public Projectitem(String name, String desc, String url , String college) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.college = college ;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getCollege() {
        return college;
    }
}

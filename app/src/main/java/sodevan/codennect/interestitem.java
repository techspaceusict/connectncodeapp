package sodevan.codennect;

import java.util.HashMap;

/**
 * Created by ravipiyush on 22/10/16.
 */

public class interestitem {

    String name  ;
    HashMap<String , Projectitem>  project ;


    public interestitem() {
    }

    public interestitem(String name, HashMap<String, Projectitem> project) {
        this.name = name;
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Projectitem> getProject() {
        return project;
    }
}

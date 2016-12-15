package sodevan.codennect;

/**
 * Created by ravipiyush on 10/10/16.
 */

public class Answerchild {

    String answer ;
    Long date  ;
    String month  ;
    String name ;




    public  Answerchild(){}

    public  Answerchild(String Answer ,Long Date ,String Month ,String Name){

        this.answer =Answer  ;
        this.date =Date  ;
        this.month = Month  ;
        this.name  = Name   ;
    }


    public String getAnswer() {
        return answer;
    }

    public Long getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getName() {
        return name;
    }


}

package sodevan.codennect;


import java.util.HashMap;

/**
 * Created by ravipiyush on 10/10/16.
 */

public class Questionchild {



    HashMap<String , Answerchild> answers  ;
    String by  ;
    Long date ;
    String month ;
    String title ;
    String bestAnswer ;
    String qid ;
    String status ;


    public Questionchild() {
    }



    public Questionchild( HashMap<String , Answerchild> answers ,String By ,Long Date , String Description ,String Month , String Title ,String bestAnswer ,String qid , String status) {

        this.by =By ;
        this.answers =answers ;
        this.date =Date ;
        this.month =Month ;
        this.title = Title ;
        this.bestAnswer = bestAnswer ;
        this.qid = qid ;
        this.status = status ;


    }


    public HashMap<String, Answerchild> getAnswers() {
        return answers;
    }

    public String getBy() {
        return by;
    }

    public Long getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getTitle() {
        return title;
    }

    public String getBestAnswer() {
        return bestAnswer;
    }

    public String getQid() { return qid; }

    public String getStatus() {
        return status;
    }


}

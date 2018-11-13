package fragebogen;


import java.io.Serializable;
import javax.persistence.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author micha
 */

@Entity
public class Questions implements Serializable {
    
    @Id
    private int question_ID;
    private String question;
    private String created_at;
    
    public void setQuestion_ID(int question_ID) {
        this.question_ID = question_ID;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    public int getQuestions_ID() {
        return this.question_ID;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public String getCreated_at() {
        return this.created_at;
    }
    
}

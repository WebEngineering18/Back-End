/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fragebogen;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author micha
 */
@Entity
public class Answer_Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)  
    private int answer_question_ID;
    
    @ManyToOne
    @Basic(optional = false)
    @Column(nullable = false)  
    private Answers answer_fk;
    
    @ManyToOne
    @Basic(optional = false)
    @Column(nullable = false)  
    private Questions question_fk;

    public void setAnswer_Question_ID(int id) {
        this.answer_question_ID = id;
    }

    public void setAnswer_fk(Answers fk) {
        this.answer_fk = fk;
    }

    public void setQuestion_fk(Questions fk) {
        this.question_fk = fk;
    }

    public int getAnswer_Question_ID() {
        return this.answer_question_ID;
    }

    public Questions getQuestion_fk() {
        return this.question_fk;
    }

    public Answers getAnswer_fk() {
        return this.answer_fk;
    }
}

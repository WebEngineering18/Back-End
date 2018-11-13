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
public class Answers implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private int answers_ID;
    
    @Basic(optional = false)
    @Column(nullable = false) 
    private String answer;
    
    @Basic(optional = false)
    @Column(nullable = false) 
    private String created_at;
    
    public void setAnswers_ID(int answers_ID) {
        this.answers_ID = answers_ID;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    public int getAnswers_ID() {
        return this.answers_ID;
    }
    
    public String getAnswer() {
        return this.answer;
    }
    
    public String getCreated_at() {
        return this.created_at;
    }
    
}

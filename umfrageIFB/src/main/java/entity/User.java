package entity;

import javax.persistence.*;

/**
 * Entität für die User Tabelle.
 * 
 * @author Frank Köhn
 */
@Entity
@Table(name = "users")
@SequenceGenerator(
        name = "UserSequence",
        allocationSize = 1,
        initialValue = 1)
public class User extends Created_At {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @Column(name = "user_id")
    private int id;
    
    @Column(name = "sessionId")
    private String sessionId;

    public User() {
        super();
    }

    public User(String sessionId) {
        super();
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId (String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", sessionId=" + sessionId + ", time=" + getCreatedAt() + "]";
    }


}

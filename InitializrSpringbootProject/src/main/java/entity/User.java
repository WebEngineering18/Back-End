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

    @Column(name = "ip")
    private String ip;

    public User() {
        super();
    }

    public User(String ip) {
        super();
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", ip=" + ip + ", time=" + getCreatedAt() + "]";
    }


}

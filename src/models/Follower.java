package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "followers")
@Entity
public class Follower {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "follow_id", nullable = false)
    private Integer follow_id;

    @Column(name = "follower_id", nullable = false)
    private Integer follower_id;

    @Column(name = "follow_at", nullable = false)
    private Timestamp follow_at;

    public Integer getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(Integer follow_id) {
        this.follow_id = follow_id;
    }

    public Integer getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Integer follower_id) {
        this.follower_id = follower_id;
    }

    public Timestamp getFollow_at() {
        return follow_at;
    }

    public void setFollow_at(Timestamp follow_at) {
        this.follow_at = follow_at;
    }
}

package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "followers")
@NamedQueries({
    @NamedQuery(
            name = "getFollow_id",
            query = "SELECT f.follow_id FROM Follower AS f where f.follower_id = :follower_id "
            ),
    @NamedQuery(
            name = "getFollowerRecord",
            query = "SELECT f FROM Follower AS f where f.follower_id = :follower_id and f.follow_id = :follow_id"
            )
})
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

    @Column(name = "create_at", nullable = false)
    private Timestamp create_at;

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

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }
}

package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "followers")
@NamedQueries({
    @NamedQuery(
            name = "getFollowEmployee",
            query = "SELECT f.follow_employee FROM Follower AS f where f.follower_employee.id = :follower_id "
            ),
    @NamedQuery(
            name = "getFollowAllReports",
            query = "SELECT r FROM Report AS r WHERE r.employee in "
                    + "(SELECT f.follow_employee FROM Follower AS f where f.follower_employee.id = :follower_id)"
            ),
    @NamedQuery(
            name = "getFollower",
            query = "SELECT f FROM Follower AS f where f.follow_employee.id = :follow_id and f.follower_employee.id = :follower_id"
            )
})
@Entity
public class Follower {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_employee_id", nullable = false)
    private Employee follow_employee;

    @ManyToOne
    @JoinColumn(name = "follower_employee_id", nullable = false)
    private Employee follower_employee;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFollow_employee() {
        return follow_employee;
    }

    public void setFollow_employee(Employee follow_employee) {
        this.follow_employee = follow_employee;
    }

    public Employee getFollower_employee() {
        return follower_employee;
    }

    public void setFollower_employee(Employee follower_employee) {
        this.follower_employee = follower_employee;
    }

    public Timestamp getCreate_at() {
        return created_at;
    }

    public void setCreate_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}

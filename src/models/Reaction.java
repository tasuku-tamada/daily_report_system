package models;

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

@Table(name = "reactions")
@NamedQueries({
    @NamedQuery(
            name = "getReactionCount",
            query = "SELECT COUNT(r) FROM Reaction AS r WHERE r.report = :report and r.type = :type"
            ),
    @NamedQuery(
            name = "getMyReaction",
            query = "SELECT COUNT(r) FROM Reaction AS r WHERE r.employee = :employee and r.report = :report and r.type = :type"
            ),
    @NamedQuery(
            name = "getReaction",
            query = "SELECT r FROM Reaction AS r WHERE r.employee = :employee and r.report = :report and r.type = :type"
            )
})
@Entity
public class Reaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    //リアクションの種類を増やすことを見越して作成
    //0:いいね
    @Column(name = "type",nullable = false)
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}

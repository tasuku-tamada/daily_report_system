package models;

import java.sql.Date;

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

@Entity
@Table(name ="attendances")
@NamedQueries({
    @NamedQuery(
            name = "getAllAttendances",
            query = "SELECT a FROM Attendance AS a ORDER BY a.date DESC"
            ),
    @NamedQuery(
            name = "getAttendancesCount",
            query = "SELECT COUNT(a) FROM Attendance AS a ORDER BY a.date DESC"
            ),
    @NamedQuery(
            name = "getDateAttendances",
            query = "SELECT a FROM Attendance AS a WHERE a.date BETWEEN :dateBegin and :dateEnd"
            ),
    @NamedQuery(
            name = "checkAttendanceDate",
            query = "SELECT COUNT(a) FROM Attendance AS a WHERE a.employee = :employee and a.date = :dateBegin"
            )
})
public class Attendance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date",nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "attendance_time",nullable = false)
    private String attendance_time;

    @Column(name = "leave_time",nullable =false)
    private String leave_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getAttendance_time() {
        return attendance_time;
    }

    public void setAttendance_time(String attendance_time) {
        this.attendance_time = attendance_time;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }


}

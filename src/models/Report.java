package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reports")
@NamedQueries({
    @NamedQuery(
            name = "getAllReports",
            query = "SELECT r FROM Report AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getAllApprovedReports",
            query = "SELECT r FROM Report AS r WHERE r.approval_flag = 1 ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r"
            ),
    @NamedQuery(
            name = "getApprovedReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.approval_flag = 1"
            ),
    @NamedQuery(
            name = "getMyAllReports",
            query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getMyReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"
            ),
    @NamedQuery(
            name = "getUnapprovedReports",
            query = "SELECT r FROM Report AS r WHERE r.approval_flag = 0 and r.employee.group = :group and r.employee.position.level < :position_level"
            ),
    @NamedQuery(
            name = "getCustomerReports",
            query = "SELECT r FROM Report AS r WHERE r.approval_flag = 1 and r.customer.id = :customer_id"
            ),
    @NamedQuery(
            name = "getCustomerReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.approval_flag = 1 and r.customer.id = :customer_id"
            )
})
@Entity
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "report_date", nullable = false)
    private Date report_date;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    //承認済み:1 未承認:0
    @Column(name = "approval_flag", nullable = false)
    private Integer approval_flag;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @Column(name = "business_status", nullable = true)
    private String business_status;

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

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getApproval_flag() {
        return approval_flag;
    }

    public void setApproval_flag(Integer approval_flag) {
        this.approval_flag = approval_flag;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }


}
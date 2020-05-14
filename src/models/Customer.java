package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "customers")
@NamedQueries({
    @NamedQuery(
            name = "getAllCustomers",
            query="SELECT c FROM Customer AS c ORDER BY c.id ASC"
            ),
    @NamedQuery(
            name = "getCustomersCount",
            query="SELECT COUNT(c) FROM Customer AS c"
            )

})
@Entity
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name" ,nullable = false)
    private String name;
}
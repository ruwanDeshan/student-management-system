package lk.developpersstack.lms.entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;
    private String name;
    private String contact;

    //------------mapping------------------
    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "student",
            fetch = FetchType.EAGER
    )
    private  Laptop laptop;
    //------------mapping------------------

    public Student() {
    }

    public Student(long id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

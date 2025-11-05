package iuh.fit.se.ontapth02.model;

import jakarta.persistence.*;

import java.util.List;

/*
 * @description: Teacher
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private ChucVu chucvu;

    @OneToMany(mappedBy = "teacher")
    private List<Clazz> clazzes;

    public Teacher() {}

    public Teacher(Integer id, String name, String address, ChucVu chucvu, List<Clazz> clazzes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.chucvu = chucvu;
        this.clazzes = clazzes;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ChucVu getChucvu() {
        return chucvu;
    }

    public void setChucvu(ChucVu chucvu) {
        this.chucvu = chucvu;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}



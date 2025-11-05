package iuh.fit.se.ontapth02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

/*
 * @description: Clazz
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Entity
@Table(name = "clazzes")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @PastOrPresent(message = "Ngày bắt đầu phải sau hoặc bằng ngày hiện tại")
    @Temporal(TemporalType.DATE)
    private LocalDate startdate;

    @PositiveOrZero(message = "Số lượng > 0")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "teacherid")
    private Teacher teacher;

    public Clazz() {}

    public Clazz(Integer id, String name, LocalDate startdate, int quantity, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.quantity = quantity;
        this.teacher = teacher;
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

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}


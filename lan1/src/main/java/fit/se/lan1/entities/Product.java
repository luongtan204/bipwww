package fit.se.lan1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Table
public class Product {
    @Id
    private String id;
    private String name;
    private String price;
    private int quantity;
    private String image;
    private Date createAt;

}

package com.example.demo.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="city")
public class City {
    @Id
    private String id;

    private String name;

    private Date createDate = new Date();
}

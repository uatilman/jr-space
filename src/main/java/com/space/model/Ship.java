package com.space.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "ship")
@Getter
@Setter
@ToString
public class Ship {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column
    private String name;

    @Size(max = 50)
    @Column
    private String planet;

    @Column
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column
    private Double speed;
    @Column
    private Integer crewSize;

    @Column
    private Double rating;

}

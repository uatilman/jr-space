package com.space.model;

import com.space.validators.ProdDateBetween;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String planet;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column
    @ProdDateBetween(after = "2800")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column
    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("0.99")
    private Double speed;

    @Column
    @NotNull
    @Min(1)
    @Max(9999)
    private Integer crewSize;

    @Column
    private Double rating;

}

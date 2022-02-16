package com.example.APILearn.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private int city_id;

    @Column(name = "city", nullable = false)
    private String city;





    @Column(name = "last_update")
    private Timestamp last_update;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="country_id", referencedColumnName ="country_id")
    private Country country;




}

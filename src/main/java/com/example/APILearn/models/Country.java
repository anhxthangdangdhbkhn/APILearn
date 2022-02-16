package com.example.APILearn.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private int country_id;

    @Column(name="country", nullable = false)
    private String country;

    private Timestamp last_update;

}

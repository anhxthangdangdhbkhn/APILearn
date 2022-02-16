package com.example.APILearn.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name ="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private int address_id;

    @Column(name="address")
    private String address;

    @Column(name="address2")
    private String address2;

    @Column(name="district")
    private String district;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName =  "city_id" )
    private City city;

    @Column(name="postal_code")
    private String postal_code;

    @Column(name="phone")
    private String phone;

    @Column(name="last_update")
    private Timestamp last_update;


}

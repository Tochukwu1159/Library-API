package com.codewithchristian.libraryapi.models.users;

import com.codewithchristian.libraryapi.models.audit.DateAudit;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address extends DateAudit {

    @Column
    private String addresss;

    //    @Field("city")
//    private String city;

//    private UserEntity user;
}

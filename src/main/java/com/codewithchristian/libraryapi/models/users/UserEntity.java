package com.codewithchristian.libraryapi.models.users;

import com.codewithchristian.libraryapi.models.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


//@Entity morphia
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends DateAudit {

    //@field is for customizing the field name
    @Column
    private String firstName;

    @Column
    private String lastName;

//    for making unique elements
    @Column
    private String email;

//    @JsonIgnore
//    @Field("phone_number")
//    @Indexed(unique = true)
//    private String phoneNumber; //todo : for later

    @JsonIgnore
    @Column
    private boolean isActive = true;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany
    private Collection<Role> roles;

    public UserEntity(String firstName, String lastName, String email, String password, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

package com.codewithchristian.libraryapi.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class BaseModel implements Serializable {

    @Id
    private String id;

    @CreatedDate
    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    @LastModifiedDate
    @JsonIgnore
    private Date updatedAt;

//    @JsonIgnore
//    private boolean deleted = false;
}

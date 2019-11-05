package com.md.producer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hello implements Serializable {

    @JsonIgnore
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String uuid;

    private Date created;
}

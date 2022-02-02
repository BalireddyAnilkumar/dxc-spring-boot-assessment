package com.dxc.learning.demo1.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "DOB cannot be null")
    private LocalDate dob;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "id must be given")
    private Integer id;

}
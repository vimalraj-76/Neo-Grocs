package io.grocery.backend.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String name;
    private Double salary;
    private Date joinedDate;
    private String role;
    private String email;
    private String mobile;

    // Constructors, getters, setters, and other methods
}

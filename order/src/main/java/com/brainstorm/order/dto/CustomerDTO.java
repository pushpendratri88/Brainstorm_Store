package com.brainstorm.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotNull
    private Long mobileNumber;
}

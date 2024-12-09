package com.brainstorm.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String id;
    private String name;
    private String email;
    private Long mobileNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private Set<Long> addressIds;
    private MultipartFile file;
    private Set<AddressDTO> customerAddress;
}

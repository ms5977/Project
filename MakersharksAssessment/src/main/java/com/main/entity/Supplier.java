package com.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name should not exceed 100 characters")
    @Column(name = "Company_Name")
    private String companyName;

    @NotBlank(message = "Website is required")
    @Pattern(regexp = "^(https?:\\/\\/)?([\\w.-]+)\\.([a-z\\.]{2,6})([\\/\\w.-]*)*\\/?$", message = "Invalid website URL")
    @Column(name = "Website")
    private String website;

    @NotBlank(message = "Location is required")
    @Size(max = 50, message = "Location should not exceed 50 characters")
    @Column(name = "Location")
    private String location;

    @NotBlank(message = "Nature of business is required")
    @Pattern(regexp = "small_scale|medium_scale|large_scale", message = "Invalid nature of business")
    @Column(name = "Nature_Of_Business")
    private String natureOfBusiness;

    @NotBlank(message = "Manufacturing process is required")
    @Pattern(regexp = "moulding|3d_printing|casting|coating", message = "Invalid manufacturing process")
    @Column(name = "Manufacturing_Process")
    private String manufacturingProcesses;
}

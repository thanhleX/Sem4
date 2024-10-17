package org.example.project4.WebPacket.Domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String imageUrl;
}

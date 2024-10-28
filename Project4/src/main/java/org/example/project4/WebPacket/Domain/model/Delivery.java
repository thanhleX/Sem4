package org.example.project4.WebPacket.Domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends Entities {

    private String trackingNumber;
    private String carrier;
    private String address;
    private String shippingStatus;

    public void updateStatus(String status) {
        this.shippingStatus = status;
    }

}


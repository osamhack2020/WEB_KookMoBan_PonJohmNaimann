package dev.riyenas.osam.domain.device;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@ToString
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;

    private String uuid;

    @Builder
    public Device(Long id, String serialNumber, String type, String manufacturer, String phoneNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.type = type;
        this.manufacturer = manufacturer;
        this.phoneNumber = phoneNumber;
        this.uuid = String.format("%040d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)
        );
    }
}
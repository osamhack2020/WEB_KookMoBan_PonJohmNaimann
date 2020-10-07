package dev.riyenas.osam.domain.device;

import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soldier_id")
    private Soldier soldier;

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

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }
}
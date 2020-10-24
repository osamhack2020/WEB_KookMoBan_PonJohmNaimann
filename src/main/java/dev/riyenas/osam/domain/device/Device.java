package dev.riyenas.osam.domain.device;

import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String manufacturer;
    private String guid;
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soldier_id")
    private Soldier soldier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", orphanRemoval = true)
    private final List<ReturnLog> returnLogs = new ArrayList<>();

    @Builder
    public Device(Long id, String guid, String type, String manufacturer) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.guid = guid;
        this.uuid = String.format("%040d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)
        );
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }
}
package dev.riyenas.osam.domain.soldier;

import dev.riyenas.osam.domain.device.Device;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Soldier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceNumber;
    private String name;
    private String rank;
    private String unit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soldier", orphanRemoval = true)
    private final List<Device> devices = new ArrayList<>();

    @Builder
    public Soldier(Long id, String name, String rank, String unit, String serviceNumber) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.name = name;
        this.rank = rank;
        this.unit = unit;
    }

    public void addDevice(Device device) {
        devices.add(device);
        device.setSoldier(this);
    }
}
package dev.riyenas.osam.domain.admin;

import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceNumber;
    private String name;
    private String password;
    private String signUpCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin", orphanRemoval = true)
    private final List<Soldier> soldiers = new ArrayList<>();

    public void addSoldier(Soldier soldier) {
        soldiers.add(soldier);
        soldier.setAdmin(this);
    }

    @Builder
    public Admin(Long id, String serviceNumber, String password, String signUpCode, String name) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.password = password;
        this.signUpCode = signUpCode;
        this.name = name;
    }
}

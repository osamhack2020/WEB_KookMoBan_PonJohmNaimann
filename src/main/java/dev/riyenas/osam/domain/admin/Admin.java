package dev.riyenas.osam.domain.admin;

import dev.riyenas.osam.domain.rule.Rule;
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
    private String unit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin", orphanRemoval = true)
    private final List<Soldier> soldiers = new ArrayList<>();

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Rule rule;

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Builder
    public Admin(Long id, String serviceNumber, String name, String password, String signUpCode, String unit, Rule rule) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.name = name;
        this.password = password;
        this.signUpCode = signUpCode;
        this.unit = unit;
        this.rule = rule;
    }
}

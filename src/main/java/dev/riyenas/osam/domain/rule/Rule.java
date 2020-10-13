package dev.riyenas.osam.domain.rule;

import dev.riyenas.osam.domain.admin.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date returnTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date dispensingTime;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Builder
    public Rule(Long id, Date returnTime, Date dispensingTime, Admin admin) {
        this.id = id;
        this.returnTime = returnTime;
        this.dispensingTime = dispensingTime;
        this.admin = admin;
    }
}

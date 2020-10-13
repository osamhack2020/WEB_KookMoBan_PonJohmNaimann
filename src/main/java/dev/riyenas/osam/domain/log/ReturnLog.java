package dev.riyenas.osam.domain.log;

import dev.riyenas.osam.domain.device.Device;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
public class ReturnLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date returnTime;

    private Double weight;

    @Lob
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    @Builder
    public ReturnLog(Long id, Date returnTime, Double weight, String photo, Device device) {
        this.id = id;
        this.returnTime = returnTime;
        this.weight = weight;
        this.photo = photo;
        this.device = device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}

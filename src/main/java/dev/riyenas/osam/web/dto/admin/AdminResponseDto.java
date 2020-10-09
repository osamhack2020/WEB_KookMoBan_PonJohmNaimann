package dev.riyenas.osam.web.dto.admin;


import dev.riyenas.osam.domain.admin.Admin;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AdminResponseDto {
    private Long id;
    private String serviceNumber;
    private String name;
    private String signUpCode;

    public AdminResponseDto(Admin entity) {
        this.id = entity.getId();
        this.serviceNumber = entity.getServiceNumber();
        this.signUpCode = entity.getSignUpCode();
        this.name = entity.getName();
    }
}

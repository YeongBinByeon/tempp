package com.yeongbin.stock.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
public class UserRegisterResponseDto {

    private Long id;

    @NotBlank(message = "이름은 null이거나 빈 값일 수 없습니다.")
    private String name;

    @Min(value = 0, message = "나이는 음수일 수 없습니다.")
    private int age;

    private LocalDate registeredDate;

}

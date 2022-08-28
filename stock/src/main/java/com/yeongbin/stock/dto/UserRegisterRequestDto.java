package com.yeongbin.stock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    @ApiModelProperty(value = "이름", example = "변영빈", required = true)
    @NotBlank(message = "이름은 null이거나 빈 값일 수 없습니다.")
    private String name;

    @ApiModelProperty(value = "나이", example = "29", required = true)
    @Min(value = 0, message = "나이는 음수일 수 없습니다.")
    @Max(value = 150, message = "150 초과 값은 입력할 수 없습니다.")
    private int age;
}

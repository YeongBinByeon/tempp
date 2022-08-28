package com.yeongbin.stock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterDto {
    @ApiModelProperty(value = "계좌번호", example = "1000-51", required = true)
    @NotBlank
    private String accountNumber;

    @ApiModelProperty(value = "사용자 ID", example = "1", required = true)
    @Min(value = 0)
    private Long userId;
}

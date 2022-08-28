package com.yeongbin.stock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionDetailRegisterDto {
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "계좌번호", example = "1000-51", required = true)
    private String accountId;

    @Pattern(regexp = "[Y|N]")
    @ApiModelProperty(value = "입금Y, 출금N", example = "Y", required = true)
    private String isDepositOperation;

    @ApiModelProperty(value = "금액", example = "500000", required = true)
    private Long depositMoney;

    @ApiModelProperty(hidden = true)
    private LocalDate depositDate;
}

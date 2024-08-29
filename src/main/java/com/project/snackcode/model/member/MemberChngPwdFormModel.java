package com.project.snackcode.model.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberChngPwdFormModel {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String changedPassword;

    @NotBlank
    private String confirmPassword;

}

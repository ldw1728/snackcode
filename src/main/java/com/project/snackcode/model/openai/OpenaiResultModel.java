package com.project.snackcode.model.openai;

import com.project.snackcode.enums.OpenType;
import com.project.snackcode.model.post.PostFormModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpenaiResultModel {
    private String title;
    private String desc;
    private String code;
    private String cntnsType;

    @Builder
    public OpenaiResultModel(String title, String desc, String code, String cntnsType) {
        this.title = title;
        this.desc = desc;
        this.code = code;
        this.cntnsType = cntnsType;
    }

    public PostFormModel toPostFormModel() {
        return PostFormModel.builder()
                .title(this.title)
                .codeCntns(this.code)
                .desc(this.desc)
                .cntnsType(this.cntnsType)
                .cateId(161L)
                .openType(OpenType.Y)
                .build();
    }
}

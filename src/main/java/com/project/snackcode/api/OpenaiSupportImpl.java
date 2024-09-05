package com.project.snackcode.api;


import com.project.snackcode.model.openai.OpenaiResponse;
import com.project.snackcode.model.openai.OpenaiResultModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("openaiSupportImpl")
public class OpenaiSupportImpl extends OpenaiProvider{

    private String codeSeperate = "---code---";


    /**
     * support by ai
     * @param param
     * @return
     */
    @Override
    protected OpenaiResultModel executeOperation(String ...param) {
        setQuestion(
                """
                %s
                
                위 코드는  %s 언어로 작성되었습니다.
                해당 코드의 기능을 분석하여 간략한 설명, 문제점과 보완해야 할 부분을 10줄이내로 서술하고 
                '%s' 명시한 후 그 아래에 문제점들이 수정되어 완성된 코드 혹은 더 개선된 예시 코드를 제안해 주세요.
                """.formatted(param[0], param[1], codeSeperate)
        );
        OpenaiResponse response                 = sendQuestion();
        String[] contentArr                     = response.getChoices().get(0).getMessage().getContent().split(codeSeperate);
        OpenaiResultModel openaiResultModel     = OpenaiResultModel.builder().desc(contentArr[0]).code(contentArr[1]).build();
        return openaiResultModel;
    }
}

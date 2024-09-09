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
//        setQuestion(
//                """
//                %s
//
//                위 코드는  %s 언어로 작성되었습니다.
//                해당 코드의 기능을 분석하여 간략한 설명, 문제점과 보완해야 할 부분을 10줄이내로 서술하고
//                '%s' 명시한 후 그 아래에 문제점들이 수정되어 완성된 코드 혹은 더 개선된 예시 코드를 제안해 주세요.
//                """.formatted(param[0], param[1], codeSeperate)
//        );

        setQuestion(
                """
                %s
                
                위 코드는  %s 언어로 작성되었습니다.
                해당 코드를 분석하여 코드가 의도하는 기술, 기능, 문법에 대해 제일 중요한 키워드 하나만 골라서
                이 키워드에 대한 정의, 속성, 사용법등을 자세히 잘 설명하는 글을 보기좋은 형식으로 500자 이내로 작성하고, 
                '%s' 명시한 후 더 개선된 예시 코드를 출력해 주세요
                """.formatted(param[0], param[1], codeSeperate)
        );
        OpenaiResponse response                 = sendQuestion();
        String[] contentArr                     = response.getChoices().get(0).getMessage().getContent().split(codeSeperate);
        OpenaiResultModel openaiResultModel     = OpenaiResultModel.builder().desc(contentArr[0]).code(contentArr[1]).build();
        return openaiResultModel;
    }
}

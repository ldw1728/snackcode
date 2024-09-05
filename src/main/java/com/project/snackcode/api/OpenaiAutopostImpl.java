package com.project.snackcode.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.snackcode.enums.OpenaiMCategory;
import com.project.snackcode.enums.OpenaiSCategory;
import com.project.snackcode.model.openai.OpenaiResponse;
import com.project.snackcode.model.openai.OpenaiResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("openaiAutopostImpl")
public class OpenaiAutopostImpl extends OpenaiProvider{

    private String subject;
    private String category     = "[" + OpenaiSCategory.getStringValues() + "] 중 랜덤으로 하나의 단어 선택";
    private String line         = "100line under";
    private String detail       = "useful information for developer";
    private String format       = "json {title, code(write example code if exist and is meaningful), cntnsType(programmingLanguage of 'code' attribute), desc(just detail description not example code of subject in the form of look good HTML)}";
    private String lang         = "korean";
    private String sentens      = "write according to format a post for developers reflecting the above explains.";


    @Override
    protected OpenaiResultModel executeOperation(String... param) {

        OpenaiResultModel openaiResultModel = null;

        this.subject = param.length > 0 && !StringUtils.isBlank(param[0]) ? param[0] : """
                   [%s] 주제 중 하나의 주제를 골라 그 주제와 관련있는 키워드를 단어 하나만 출력.
                """.formatted(OpenaiMCategory.getStringValues());

        setQuestion(
                """              
                1. subject = %s,
                2. category = %s,
                3. line = %s,
                4. detail Requirements = %s,
                5. format = %s,
                6. language = %s
            
                %s
                
                """.formatted(
                        this.subject,
                        this.category,
                        this.line,
                        this.detail,
                        this.format,
                        this.lang,
                        this.sentens
                )
        );
        OpenaiResponse response = sendQuestion();
        String content = response.getChoices().get(0).getMessage().getContent();
        content = content.substring(content.indexOf("{"), content.lastIndexOf("}") + 1);
        log.debug(question);
        log.debug(content);
        try {
            openaiResultModel = new ObjectMapper().readValue(content, OpenaiResultModel.class);
        } catch (JsonProcessingException e) {
            //에러 시 로그출력
            log.error(e.getMessage());
        }

        return openaiResultModel;
    }
}

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

import java.util.Arrays;

@Slf4j
@Component
@Qualifier("openaiAutopostImpl")
public class OpenaiAutopostImpl extends OpenaiProvider{

    private String subject;
    private String category     = "Randomly one of [" + OpenaiSCategory.getStringValues() + "]";
    private String line         = "100line under";
    private String detail       = "useful information for developer";
    private String format       = "json (attributes = title, code - example code if exist, cntnsType - programmingLanguage of 'code' attribute, desc - description in the form of look good HTML)";
    private String lang         = "korean";
    private String sentens      = "write a post reflecting the above options.";


    @Override
    protected OpenaiResultModel executeOperation(String... param) {

        OpenaiResultModel openaiResultModel = null;

        this.subject = param.length > 0 && !StringUtils.isBlank(param[0]) ? param[0] : """
                   New keywords related to IT as one of the topics [%s]
                """.formatted(OpenaiMCategory.getStringValues());

        setQuestion(
                """
                options
                subject : %s,
                category : %s,
                line : %s,
                detail Requirements : %s,
                format : %s,
                lang : %s
                
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

package com.project.snackcode.controller;

import com.project.snackcode.model.openai.OpenaiRequest;
import com.project.snackcode.model.openai.OpenaiRequestDto;
import com.project.snackcode.model.openai.OpenaiResponse;
import com.project.snackcode.model.openai.OpenaiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class OpenaiController {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/openai/chat")
    public ResponseEntity chat(@RequestBody OpenaiRequestDto requestDto) {

        String codeSeperate = "---code---";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            return execution.execute(request, body);
        });

        String question = """
                %s
                
                위 코드는  %s 언어로 작성되었습니다.
                해당 코드의 기능을 분석하여 간략한 설명, 문제점과 보완해야 할 부분을 10줄이내로 서술하고 
                '%s' 명시한 후 그 아래에 문제점들이 수정되어 완성된 코드 혹은 더 개선된 예시 코드를 제안해 주세요.
                """.formatted(requestDto.getCode(), requestDto.getCntnsType(), codeSeperate);

        OpenaiRequest request       = OpenaiRequest.builder().model(model).message(question).build();
        OpenaiResponse response     = restTemplate.postForObject(apiUrl, request, OpenaiResponse.class);

        String[] contentArr = response.getChoices().get(0).getMessage().getContent().split(codeSeperate);

        OpenaiResponseDto responseDto = OpenaiResponseDto.builder().desc(contentArr[0]).code(contentArr[1]).cntnsType(requestDto.getCntnsType()).build();
        return ResponseEntity.ok(responseDto);
    }






}

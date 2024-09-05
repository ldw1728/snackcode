package com.project.snackcode.controller;

import com.project.snackcode.api.OpenaiProvider;
import com.project.snackcode.model.openai.OpenaiRequestDto;
import com.project.snackcode.model.openai.OpenaiResultModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenaiController {

    @Qualifier("openaiSupportImpl")
    private OpenaiProvider openaiProvider;

    /**
     * support by ai
     * @param requestDto
     * @return
     */
    @PostMapping("/openai/chat")
    public ResponseEntity chat(@RequestBody OpenaiRequestDto requestDto) {
        OpenaiResultModel resultModel = openaiProvider.getResult(requestDto.getCode(), requestDto.getCntnsType());
        resultModel.setCntnsType(requestDto.getCntnsType());
        return ResponseEntity.ok(resultModel);
    }






}

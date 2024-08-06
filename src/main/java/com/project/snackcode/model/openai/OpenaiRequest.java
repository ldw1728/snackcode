package com.project.snackcode.model.openai;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OpenaiRequest {

    private String model;
    private List<OpenaiMsg> messages;

    @Builder
    public OpenaiRequest(String model, String message) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new OpenaiMsg("user", message));
    }
}

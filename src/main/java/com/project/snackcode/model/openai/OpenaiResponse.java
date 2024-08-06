package com.project.snackcode.model.openai;

import lombok.Data;

import java.util.List;

@Data
public class OpenaiResponse {

    private List<OpenaiChoice> choices;

    @Data
    public static class OpenaiChoice {
        private int index;
        private OpenaiMsg message;
    }
}

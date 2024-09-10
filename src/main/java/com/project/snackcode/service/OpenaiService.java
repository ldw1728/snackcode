package com.project.snackcode.service;

import com.project.snackcode.api.OpenaiProvider;
import com.project.snackcode.model.openai.OpenaiResultModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenaiService {

    @Autowired
    @Qualifier("openaiAutopostImpl")
    private OpenaiProvider openaiProvider;

    private final PostService postService;

    private final CategoryService categoryService;


    /**
     * open ai 자동 포스팅 기능
     * 매 시간마다 특정 키워드에대한 내용의 글을 자동으로 포스팅한다.
     */
    //@Scheduled(cron = "0 0 * * * *")
    public void autoPosting() {

        String keyword = getKeyword();
        OpenaiResultModel openaiResultModel = openaiProvider.getResult(keyword);

        if (openaiResultModel != null) {
            postService.save(openaiResultModel.toPostFormModel());
        }

    }

    private String getKeyword() {
        return "";
    }

}

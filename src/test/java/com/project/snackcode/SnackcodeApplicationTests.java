package com.project.snackcode;

import com.project.snackcode.api.OpenaiProvider;
import com.project.snackcode.model.comment.CommentFormModel;
import com.project.snackcode.model.comment.CommentModel;
import com.project.snackcode.model.openai.OpenaiResultModel;
import com.project.snackcode.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class SnackcodeApplicationTests {


    @Autowired
    private CommentService commentService;

    @Autowired
    @Qualifier("openaiAutopostImpl")
    private OpenaiProvider openaiProvider;


    @Test
    public void 코멘트조회_테스트() {
        List<CommentModel> list = commentService.selectCommentList(85L);


        System.out.println(list);
    }

    @Test
    @Commit
    public void 코멘트저장_테스트() {

        CommentFormModel commentFormModel = new CommentFormModel();
        commentFormModel.setPrntId(null);
        commentFormModel.setPostId(2L);
        commentFormModel.setCntns("this is test comment");
        commentFormModel.setMemId(1L);

        commentService.saveComment(commentFormModel);

    }

    private CommentFormModel createCommentFormModels() {
        return null;
    }


    @Test
    public void openai_autopost() {
        OpenaiResultModel model = openaiProvider.getResult("");
        System.out.println(model);

    }
}

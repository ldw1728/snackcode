package com.project.snackcode;

import com.project.snackcode.api.OpenaiProvider;
import com.project.snackcode.model.comment.CommentFormModel;
import com.project.snackcode.model.comment.CommentModel;
import com.project.snackcode.model.openai.OpenaiResultModel;
import com.project.snackcode.service.CommentService;
import com.project.snackcode.service.OpenaiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class SnackcodeApplicationTests {


    @Autowired
    private CommentService commentService;

    @Autowired
    @Qualifier("openaiAutopostImpl")
    private OpenaiProvider openaiProvider;

    @Qualifier("openaiSupportImpl")
    @Autowired
    private OpenaiProvider openaiProvider2;

    @Autowired
    private OpenaiService openaiService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
    public void openai_support() {
        OpenaiResultModel result = openaiProvider2.getResult("""
                @Scheduled(delay="1000")
                @Scheduled(cron = "0 0/30 * * * *")     // cron 표현식
                public void autoPosting(){
                    //......
                }
                                
                                
                @EnableScheduling // 어노테이션 선언
                @SpringBootApplication
                public class ExampleApplication {
                    public static void main(String[] args){
                       \s
                    }
                }
                """, "java");

        System.out.println(result);
    }

    @Test
    //@Commit
    public void openai_autopost() {
        //openaiService.openai();
        //openaiService.autoPosting();

        System.out.println(openaiProvider2.getClass());

    }

    @Test
    public void password() {
        System.out.println(passwordEncoder.encode("1111"));
    }
}

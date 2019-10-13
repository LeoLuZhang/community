package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @Title: QuestionDTO
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/3 17:48
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}

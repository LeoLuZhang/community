package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @Title: CommentDTO
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/16 21:33
 */
@Data
public class CommentDTO {
    private Long  id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}

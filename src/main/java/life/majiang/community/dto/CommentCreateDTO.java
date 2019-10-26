package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @Title: CommentCreateDTO
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/16 21:33
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}

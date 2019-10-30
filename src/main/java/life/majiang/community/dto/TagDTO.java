package life.majiang.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Title: TagDTO
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/29 22:40
 */
@Data
public class TagDTO {
    private  String categoryName;
    private List<String> tags;
}

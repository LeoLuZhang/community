package life.majiang.community.dto;

import lombok.Data;

/**
 * @Title: GithubUser
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/9/28 8:32
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}

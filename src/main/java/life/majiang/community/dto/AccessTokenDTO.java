package life.majiang.community.dto;

import lombok.Data;

/**
 * @Title: AccesstokenDTO
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/9/27 17:07
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}

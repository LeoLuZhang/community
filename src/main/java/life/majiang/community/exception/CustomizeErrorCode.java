package life.majiang.community.exception;

/**
 * @Title: CustomizeErrorCode
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/13 17:17
 */
public enum CustomizeErrorCode  implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你找到问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004,"服务器冒烟了，要不你稍后试试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论类容不见了，要不要换个试试"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
     INVALID_INPUT(2011,"非法输入");
    ;


    //错误码
    private Integer code;
    //错误信息
    private String message;

    //构造函数初始化
    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    //重写接口的方法
    @Override
    public String getMessage() {
        return message;
    }

    //
    @Override
    public Integer getCode() {
        return null;
    }

}

package life.majiang.community.exception;

/**
 * @Title: CustomizeErrorCode
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/13 17:17
 */
public enum CustomizeErrorCode  implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找到问题不在了，要不要换个试试？"),
    ;
    private String message;

    //构造函数初始化
    CustomizeErrorCode(String message) {
        this.message = message;
    }
    //重写接口的方法
    @Override
    public String getMessage() {
        return message;
    }

}

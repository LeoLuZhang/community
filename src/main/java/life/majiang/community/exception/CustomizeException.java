package life.majiang.community.exception;

/**
 * @Title: CustomizeException
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/13 16:50
 */
public class CustomizeException extends  RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code    =  errorCode.getCode();
        this.message =  errorCode.getMessage();
    }
    @Override
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

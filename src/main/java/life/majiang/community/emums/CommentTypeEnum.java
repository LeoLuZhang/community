package life.majiang.community.emums;

/**
 * @Title: CommentTypeEnum
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/19 11:14
 */
public enum  CommentTypeEnum {
    //问题类型为1
    QUESTION(1),
    //评论类型为2
    COMMENT(2)
    ;
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {

            if(commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type = type;
    }
}

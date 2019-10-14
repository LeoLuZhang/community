package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: UserService
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/6 10:44
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        //拼接SQL
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        //若为新的user，数组users的size为0，应该插入新的user
        if(users.size()==0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
           //更新数据库里存在的user
            User dbUser = users.get(0);
            //创建updateuser，设置更新后的值.
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            //拼接更新SQL语句
            UserExample example =  new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            //更新-updateByExampleSelective，更新局部的值，可以通过是否更新前设定值决定更新哪些字段，因为如果对应的字段为空，则不更新。
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}

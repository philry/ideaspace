package dao;

import entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/29 16:48
 * Description:
 * version:1.0
 */
public interface UserMapper {

    @Select("select * from t_user where id = #{id}")
    public User selectById(Integer id);

    @Select("select * from t_user2 where user_id = #{id}")
    @Results(value={
            @Result(id=true,property = "id",column = "user_id"),
            @Result(property = "username",column = "user_username"),
            @Result(property = "password",column = "user_password"),
            @Result(property = "phone",column = "user_phone"),
            @Result(property = "address",column = "user_address")
    })
    public User selectById2(Integer id);

}

package dao;

import entity.User;
import entity.UserParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/26 11:08
 * Description:
 * version:1.0
 */
public interface UserDao {

    public void insertUser(User user);


    public User selectById(Integer id);


    public List<User> selectAll();


    public List<User> selectByUsernameAndPassword(UserParameter userParameter);
    public List<User> selectByUsernameAndPassword2(@Param("aa") String username, @Param("password") String password);

    public List<User> selectByUsernameAndPassword3(String username,String password);


    public User selectById2(Integer id);
    public User selectById3(Integer id);


    public List<User> selectByUsername(String username);
    public List<User> selectByUsername2(String username);


    public List<User> selectByParams1(User user);
    public List<User> selectByParams2(User user);
    public List<User> selectByParams3(User user);
    public List<User> selectByParams4(User user);

    public void updateUser(User user);

    public List<User> selectByIds(List<Integer> ids);


    public void insertReturnPrimaryKey(User user);



}

package dao;

import entity.User;
import entity.UserParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    public void insert(User user);

    public User selectById(Integer id);

    public List<User> selectAll();

    public List<User> selectByNameAndPassword(UserParameter userParameter);

    public List<User> selectByNameAndPassword2(@Param("a") String username, @Param("pwd") String password);

    public List<User> selectByNameAndPassword3(String username,String password);

    public User selectById2(Integer id);

    public List<User> selectByName(String name);

    public List<User> selectByName2(String name);

    public List<User> selectByParam(User user);
    public List<User> selectByParam4(User user);

    public void update(User user);

    public List<User> selectByIds(List<Integer> ids);
}

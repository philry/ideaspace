package dao;

import com.sun.org.glassfish.gmbal.ParameterNames;
import entity.User;
import entity.UserParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public void insert(User user);

    public List<User>  selectAll();

    public User  selectById(Integer id);

    public List<User> selectByNameAndPassword(UserParameter userParameter);

    public List<User> selectByNameAndPassword2(@Param("name")String username,@Param("pwd")String password);

    public List<User> selectByNameAndPassword3(String username,String password);
}

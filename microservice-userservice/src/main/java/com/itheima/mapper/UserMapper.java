package com.itheima.mapper;

import com.itheima.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	@Insert("insert int tb_user")
	void saveUser(UserModel userModel);

    @Select("select * from tb_user where username =#{username}")
    UserModel selectUser(String username);

}
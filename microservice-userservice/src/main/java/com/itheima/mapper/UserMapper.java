package com.itheima.mapper;

import com.itheima.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

	@Insert("insert int tb_user")
	void saveUser(UserModel userModel);

    @Select("select * from tb_user where username =#{username}")
    UserModel selectUser(String username);

}
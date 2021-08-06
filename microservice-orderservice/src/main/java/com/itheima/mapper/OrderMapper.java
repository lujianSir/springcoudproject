package com.itheima.mapper;

import com.itheima.model.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderMapper {

    /**
     * 通过用户ID查询订单信息
     * @param userid
     * @return
     */
    @Select("select * from tb_order where userid =#{userid}")
    List<Order> selectOrder(String userid);
}
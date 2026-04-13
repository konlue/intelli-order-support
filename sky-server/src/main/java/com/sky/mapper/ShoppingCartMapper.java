package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询指定用户的购物车数据
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 更新商品的数量
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 插入购物车数据
     */
    @Insert("insert into shopping_cart (name, image, dish_id, setmeal_id, dish_flavor, number, amount, create_time, user_id) " +
            "values (#{name}, #{image}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{createTime}, #{userId})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据用户id删除购物车数据
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long currentId);

    /**
     * 根据id删除购物车数据
     */
    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);

    /**
     * 批量插入购物车数据t
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}

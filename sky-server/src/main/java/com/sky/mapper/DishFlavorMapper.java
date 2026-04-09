package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /*
     * 批量插入菜品口味数据
     */
    void insertBatch(List<DishFlavor> flavors);

    /*
     * 根据菜品id删除单个口味
     */
    void deleteByDishId(Long id);

    /*
     * 根据菜品id批量删除口味
     */
    void deleteByDishIds(List<Long> dishIds);

    /*
     * 根据菜品id查询对应的口味数据
     */
    List<DishFlavor> getByDishId(Long id);


}

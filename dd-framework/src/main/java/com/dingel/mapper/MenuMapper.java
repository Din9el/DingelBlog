package com.dingel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-10 20:39:51
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);
}


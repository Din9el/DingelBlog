package com.dingel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-10 20:45:39
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);
}


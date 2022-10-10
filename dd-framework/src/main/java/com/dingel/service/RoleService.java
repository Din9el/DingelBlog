package com.dingel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2022-10-10 20:45:40
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}


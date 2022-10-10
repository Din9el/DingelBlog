package com.dingel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.domain.entity.Role;
import com.dingel.mapper.RoleMapper;
import com.dingel.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-10-10 20:45:40
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        return null;
    }
}


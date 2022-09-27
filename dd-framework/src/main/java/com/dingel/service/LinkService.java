package com.dingel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-09-25 19:31:17
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}


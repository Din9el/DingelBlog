package com.dingel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 19:58:14
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}


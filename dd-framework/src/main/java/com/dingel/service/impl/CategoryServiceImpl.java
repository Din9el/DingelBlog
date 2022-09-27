package com.dingel.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.constants.SystemConstants;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.Article;
import com.dingel.domain.entity.Category;
import com.dingel.domain.vo.CategoryVo;
import com.dingel.mapper.CategoryMapper;
import com.dingel.service.ArticleService;
import com.dingel.service.CategoryService;
import com.dingel.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 19:58:15
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;


    @Override
    public ResponseResult getCategoryList() {
        //1、查询文章表 状态为已发布的文章

        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList =articleService.list(articleWrapper);


        //2、获取文章的分类id 并且去重

        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());


        //3、查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories.stream().
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());


        //4、封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}


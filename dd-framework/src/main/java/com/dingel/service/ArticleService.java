package com.dingel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.Article;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList(); //获取热门文章

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);     //获取文章列表

    ResponseResult getArticleDetail(Long id);   //获取文章详情

    ResponseResult updateViewCount(Long id);    //更新文章浏览量
}

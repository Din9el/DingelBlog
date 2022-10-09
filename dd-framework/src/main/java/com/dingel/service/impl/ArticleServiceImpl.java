package com.dingel.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.constants.SystemConstants;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.Article;
import com.dingel.domain.entity.Category;
import com.dingel.domain.vo.ArticleDetailVo;
import com.dingel.domain.vo.ArticleListVo;
import com.dingel.domain.vo.HotArticleVo;
import com.dingel.domain.vo.PageVo;
import com.dingel.mapper.ArticleMapper;
import com.dingel.service.ArticleService;
import com.dingel.service.CategoryService;
import com.dingel.utils.BeanCopyUtils;
import com.dingel.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{


    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多10条
        Page<Article> page =new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

/*        List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles){
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            articleVos.add(vo);
        }*/

        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(vs);

    }






    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        //如果有categoryId 就要 查询时要和传入的系统
        lambdaQueryWrapper.eq( Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);

        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);

        //对isTop进行降序（置顶的文章要放最前面）
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);



        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        //查询categoryName
        List<Article> articles = page.getRecords();
        //articleId去查询categoryName进行设置
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
/*        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }*/



        //封装查询结果（Vo）
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);



    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);

        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_UPDATE_VIEW_KEY, id.toString());
        article.setViewCount(viewCount.longValue());

        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null){
            articleDetailVo.setCategoryName(category.getName());
        }

        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应的id浏览量
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_UPDATE_VIEW_KEY,id.toString(),1);
        return ResponseResult.okResult();
    }
}

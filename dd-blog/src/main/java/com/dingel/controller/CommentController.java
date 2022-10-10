package com.dingel.controller;


import com.dingel.annotation.SystemLog;
import com.dingel.constants.SystemConstants;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.dto.AddCommentDto;
import com.dingel.domain.entity.Comment;
import com.dingel.service.CommentService;
import com.dingel.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }


    @SystemLog(businessName="添加评论")
    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }


    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小")
    }
    )
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){

        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);

    }


}

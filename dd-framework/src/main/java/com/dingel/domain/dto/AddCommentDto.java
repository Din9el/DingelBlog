package com.dingel.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加评论dto")
public class AddCommentDto {
    @ApiModelProperty(notes = "主、键")
    private Long id;

    //评论类型（0代表文章评论，1代表友链评论）
    @ApiModelProperty(notes = "评论类型（0代表文章评论，1代表友链评论）")
    private String type;

    //文章id
    @ApiModelProperty(notes = "文章Id")
    private Long articleId;

    //根评论id
    @ApiModelProperty(notes = "根评论id")
    private Long rootId;

    //评论内容
    @ApiModelProperty(notes = "评论内容")
    private String content;

    //所回复的目标评论的userid
    @ApiModelProperty(notes = "所回复的目标评论的userid")
    private Long toCommentUserId;

    //回复目标评论id
    @ApiModelProperty(notes = "回复目标评论id")
    private Long toCommentId;


    @ApiModelProperty(notes = "文章由谁创建")
    private Long createBy;

    @ApiModelProperty(notes = "文章创建时间")
    private Date createTime;

    @ApiModelProperty(notes = "文章由谁更新")
    private Long updateBy;

    @ApiModelProperty(notes = "文章更新时间")
    private Date updateTime;


    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}

package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.domain.Comment;
import java.util.List;
/**
 * @Classname ICommentService
 * @Description 文章评论业务处理接口
 * @Date 2023-5-10
 * @Created by TanZ
 */
public interface ICommentService {
    // 获取文章下的评论
    public PageInfo<Comment> getComments(Integer aid, int page, int count);

    // 用户发表评论
    public void pushComment(Comment comment);

    //添加部分
    // 评论管理部分，抓前十条评论显示
    public  PageInfo<Comment> selectCommentWithPage(Integer page, Integer count);
    // 更新评论
    public void updateCommentWithId(Comment comment);

    // 根据主键删除评论
    public void deleteCommentWithId(int id);

    // 根据评论id查询单个评论详情
    public Comment selectCommentWithId(int id);
}


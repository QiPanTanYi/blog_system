package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CommentMapper;
import com.itheima.dao.StatisticMapper;
import com.itheima.model.domain.Comment;
import com.itheima.model.domain.Statistic;
import com.itheima.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @Classname CommentServiceImpl
 * @Description 评论功能的Service层
 * @Date 2023-5-10
 * @Created by TanZ
 */

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StatisticMapper statisticMapper;


    // 根据文章id分页查询评论
    @Override
    public PageInfo<Comment> getComments(Integer aid, int page, int count) {
        PageHelper.startPage(page,count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(aid);
        PageInfo<Comment> commentInfo = new PageInfo<>(commentList);
        return commentInfo;
    }

    // 用户发表评论
    @Override
    public void pushComment(Comment comment){
        commentMapper.pushComment(comment);
        // 更新文章评论数据量
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(comment.getArticleId());
        statistic.setCommentsNum(statistic.getCommentsNum()+1);
        statisticMapper.updateArticleCommentsWithId(statistic);
    }


    // 修改部分
    // 抓前十条评论显示在页面上
    @Override
    public PageInfo<Comment> selectCommentWithPage(Integer page, Integer count) {
        PageHelper.startPage(page, count);
        List<Comment> articleList = commentMapper.selectCommentWithPages();
        PageInfo<Comment> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }
    //更新评论
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void updateCommentWithId(Comment comment){
        commentMapper.updateCommentWithId(comment);
        redisTemplate.delete("comment_" + comment.getId());
    }

    // 出现前十条评论
    @Override
    // 根据id查询评论内容，再使用redis进行缓存
    public Comment selectCommentWithId(int id){
        Comment comment = null;
        Object o = redisTemplate.opsForValue().get("comment_"+id);
        if(o!=null){
            comment=(Comment)o;
        }else {
            comment=commentMapper.selectCommentWithId(id);
            if(comment!=null){
                redisTemplate.opsForValue().set("comment_" + id,comment);
            }
        }
        return comment;
    }


    // 删除评论
    @Override
    public void deleteCommentWithId(int id){
        // 删除评论，同时删除对应的缓存
        redisTemplate.delete("comment_" + id);
        statisticMapper.deleteStatisticWithId(id);
        commentMapper.deleteCommentWithId(id);
    }
}


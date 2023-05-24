package com.itheima.dao;

import com.itheima.model.domain.Comment;
import org.apache.ibatis.annotations.*;
import java.util.List;
/**
 * @Classname CommentMapper
 * @Description 评论的Mapper
 * @Date 2023-5-10
 * @Created by TanZ
 */

@Mapper
public interface CommentMapper {
    // 分页展示某个文章的评论
    @Select("SELECT * FROM t_comment WHERE article_id=#{aid} ORDER BY id DESC")
    public List<Comment> selectCommentWithPage(Integer aid);

    // 后台查询最新几条评论
    @Select("SELECT * FROM t_comment ORDER BY id DESC")
    public List<Comment> selectNewComment();

    // 发表评论
    @Insert("INSERT INTO t_comment (article_id,created,author,ip,content)" +
            " VALUES (#{articleId}, #{created},#{author},#{ip},#{content})")
    public void pushComment(Comment comment);

    // 站点服务统计，统计评论数量
    @Select("SELECT COUNT(1) FROM t_comment")
    public Integer countComment();

    // 根据评论id删除评论内容
    @Delete("DELETE FROM t_comment WHERE id=#{id}")
    public void deleteCommentWithId(Integer id);

    //已修改部分
    // 通过id更新文章
    public Integer updateCommentWithId(Comment comment);

    //统计出现10条评论内容
    public List<Comment> getHeatComments();

    //根据评论id查询单个文章详情
    @Select("SELECT * FROM t_comment WHERE id=#{id}")
    public Comment selectCommentWithId(int id);

    // 评论管理内容
    @Select("SELECT * FROM t_comment ORDER BY id DESC")
    public List<Comment> selectCommentWithPages();
}


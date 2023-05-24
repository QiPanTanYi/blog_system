package com.itheima.dao;

import com.itheima.model.domain.User;
import org.apache.ibatis.annotations.*;
import java.util.List;
/**
 * @Classname UserMapper
 * @Description 用户的Mapper
 * @Date 2023-5-17
 * @Created by LMF
 */
@Mapper
public interface UserMapper {
//    根据id查询用户
    @Select("SELECT * FROM t_user WHERE id=#{id}")
    public User selectUserWithId(Integer id);
//  通过id删除用户
    @Delete("DELETE FROM t_user WHERE id=#{id}")
    public void deleteUserWithId(Integer id);
// 删除用户后,同时删除对应的评论信息
//    @Delete("DELETE t_user, t_comment FROM t_user LEFT JOIN t_comment ON t_user.username = t_comment.author WHERE t_user.id = #{id}")
    @Delete("DELETE FROM t_comment WHERE author IN (SELECT username FROM t_user WHERE id = #{id})")
    public void deleteUserDataAndComments(Integer id);
    // 用户分页查询
    @Select("SELECT * FROM t_user ORDER BY id DESC")
    public List<User> selectUserWithPage();
}

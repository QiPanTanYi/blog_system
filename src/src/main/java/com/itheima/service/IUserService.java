package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.domain.User;
/**
 * @Classname IArticleService
 * @Description 文章操作接口，分页查询
 * @Date 2023-5-17
 * @Created by LMF
 */
public interface IUserService {
    //查询用户
    public User selectUserWithId(Integer id);
    //删除用户
    public void deleteUserWithId(int id);
    // 删除用户后,同时删除对应的评论信息
    public void deleteUserDataAndComments(int id);
    //分页查询用户列表
    public PageInfo<User> selectUserWithPage(Integer page,Integer count);
}

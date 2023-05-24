package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ArticleMapper;
import com.itheima.dao.CommentMapper;
import com.itheima.dao.UserMapper;
import com.itheima.model.domain.User;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * @Classname SiteServiceImpl
 * @Description 用户的Service层
 * @Date 2023-5-17
 * @Created by LMF
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    //分页查询用户列表
    @Override
    public PageInfo<User> selectUserWithPage(Integer page,Integer count){
        PageHelper.startPage(page,count);
        List<User> userList=userMapper.selectUserWithPage();
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        return pageInfo;
    }
// 通过id查询用户，并添加到缓存中
    @Override
    public User selectUserWithId(Integer id){
        User user=null;
        Object o=redisTemplate.opsForValue().get("user_"+id);
        if(o !=null){
            user=(User)o;
        }else {
         user=userMapper.selectUserWithId(id);
         if (user!=null){
             redisTemplate.opsForValue().set("user_"+id,user);
         }
        }
        return user;
    }

    //删除用户
    @Override
    public void deleteUserWithId(int id){
        redisTemplate.delete("user_"+id);
        userMapper.deleteUserWithId(id);
    }
    //删除用户之后的评论
    @Override
    public void deleteUserDataAndComments(int id){
        redisTemplate.delete("user_"+id);
        userMapper.deleteUserDataAndComments(id);
    }

}

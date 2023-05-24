package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.domain.Article;
import java.util.List;
/**
 * @Classname IArticleService
 * @Description 文章操作接口，分页查询
 * @Date 2023-5-10
 * @Created by TanZ
 */

public interface IArticleService {
    // 分页查询文章列表
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count);

    // 统计前10的热度文章信息
    public List<Article> getHeatArticles();

    // 根据文章id查询单个文章详情
    public Article selectArticleWithId(Integer id);

    // 发布文章
    public void publish(Article article);

    // 根据主键更新文章
    public void updateArticleWithId(Article article);

    // 根据主键删除文章
    public void deleteArticleWithId(int id);

    // 统计四种分类
    public Integer CountCatWithA();
    public Integer CountCatWithB();
    public Integer CountCatWithC();
    public Integer CountCatWithD();

    // 修改默认分类
    public void UpdateCatWithA(Article article);
    public void UpdateCatWithB(Article article);
    public void UpdateCatWithC(Article article);
}


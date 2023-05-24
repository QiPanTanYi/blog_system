package com.itheima.web.admin;

import com.github.pagehelper.PageInfo;
import com.itheima.model.ResponseData.ArticleResponseData;
import com.itheima.model.ResponseData.CommentResponseData;
import com.itheima.model.ResponseData.StaticticsBo;
import com.itheima.model.ResponseData.UserResponseData;
import com.itheima.model.domain.Article;
import com.itheima.model.domain.Comment;
import com.itheima.model.domain.User;
import com.itheima.service.IArticleService;
import com.itheima.service.ISiteService;
import com.itheima.service.ICommentService;
import com.itheima.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Classname AdminController
 * @Description 后台管理模块
 * @Date 2023-5-10
 * @Created by TanZ
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ISiteService siteServiceImpl;
    @Autowired
    private IArticleService articleServiceImpl;
    @Autowired
    private IUserService iUserServiceImpl;

    // 管理中心起始页
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request) {
        // 获取最新的5篇博客、评论以及统计数据
        List<Article> articles = siteServiceImpl.recentArticles(5);
        List<Comment> comments = siteServiceImpl.recentComments(5);
        StaticticsBo staticticsBo = siteServiceImpl.getStatistics();
        // 向Request域中存储数据
        request.setAttribute("comments", comments);
        request.setAttribute("articles", articles);
        request.setAttribute("statistics", staticticsBo);
        return "back/index";
    }

    // 向文章发表页面跳转
    @GetMapping(value = "/article/toEditPage")
    public String newArticle( ) {
        return "back/article_edit";
    }

    // 发表文章
    @PostMapping(value = "/article/publish")
    @ResponseBody
    public ArticleResponseData publishArticle(Article article) {
        if (StringUtils.isBlank(article.getCategories())) {
            article.setCategories("默认分类");
        }
        try {
            articleServiceImpl.publish(article);
            logger.info("文章发布成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章发布失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 跳转到后台文章列表页面
    @GetMapping(value = "/article")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "count", defaultValue = "10") int count,
                        HttpServletRequest request) {
        PageInfo<Article> pageInfo = articleServiceImpl.selectArticleWithPage(page, count);
        request.setAttribute("articles", pageInfo);
        return "back/article_list";
    }

    // 向文章修改页面跳转
    @GetMapping(value = "/article/{id}")
    public String editArticle(@PathVariable("id") String id, HttpServletRequest request) {
        Article article = articleServiceImpl.selectArticleWithId(Integer.parseInt(id));
        request.setAttribute("contents", article);
        request.setAttribute("categories", article.getCategories());
        return "back/article_edit";
    }

    // 文章修改处理
    @PostMapping(value = "/article/modify")
    @ResponseBody
    public ArticleResponseData modifyArticle(Article article) {
        try {
            articleServiceImpl.updateArticleWithId(article);
            logger.info("文章更新成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章更新失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 文章删除
    @PostMapping(value = "/article/delete")
    @ResponseBody
    public ArticleResponseData delete(@RequestParam int id) {
        try {
            articleServiceImpl.deleteArticleWithId(id);
            logger.info("文章删除成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章删除失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }
    //进入评论管理页面
    @Autowired
    private ICommentService commentServiceImpl;
    // 评论管理
    @GetMapping(value = "/comment")
    public String indexCom(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "count", defaultValue = "10") int count,
                         HttpServletRequest request) {
        PageInfo<Comment> pageInfo =
                commentServiceImpl.selectCommentWithPage(page, count);
        request.setAttribute("comments", pageInfo);
        return "back/t_comment";
    }
    // 文章删除
    @PostMapping(value = "/comment/delete")
    @ResponseBody
    public CommentResponseData delete1(@RequestParam int id) {
        try {
            commentServiceImpl.deleteCommentWithId(id);
            logger.info("评论删除成功");
            return CommentResponseData.ok();
        } catch (Exception e) {
            logger.error("评论删除失败，错误信息: "+e.getMessage());
            return CommentResponseData.fail();
        }
    }

    //分类标签
    @GetMapping(value = "/category")
    public String index3(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "count", defaultValue = "10") int count,
                         HttpServletRequest request) {
        PageInfo<Article> pageInfo = articleServiceImpl.selectArticleWithPage(page, count);
        request.setAttribute("categroy", pageInfo);
        return "back/categories_tags";
    }

    //统计分类数量
    @PostMapping(value = "/category/count1")
    @ResponseBody
    public ArticleResponseData<Integer[]> count1(){
        Integer[] num = new Integer[4];
        try {
            num[0] = articleServiceImpl.CountCatWithA();
            num[1] = articleServiceImpl.CountCatWithB();
            num[2] = articleServiceImpl.CountCatWithC();
            num[3] = articleServiceImpl.CountCatWithD();
            logger.info("分类统计信息获取成功");
            return ArticleResponseData.ok(num);
        } catch (Exception e) {
            logger.error("分类统计信息获取失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 修改分类
    @PostMapping(value = "/category/rm")
    @ResponseBody
    public ArticleResponseData Update1(Article article){
        try {
            articleServiceImpl.UpdateCatWithA(article);
            logger.info("该博客成功修改分类为：入门");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("修改分类失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    @PostMapping(value = "/category/jj")
    @ResponseBody
    public ArticleResponseData Update2(Article article){
        try {
            articleServiceImpl.UpdateCatWithB(article);
            logger.info("该博客成功修改分类为：进阶");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("修改分类失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    @PostMapping(value = "/category/gj")
    @ResponseBody
    public ArticleResponseData Update3(Article article){
        try {
            articleServiceImpl.UpdateCatWithC(article);
            logger.info("该博客成功修改分类为：高级");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("修改分类失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    //设置
    @GetMapping(value = "/setting")
    public String index4(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "count", defaultValue = "10") int count,
                         HttpServletRequest request) {
        PageInfo<User> pageInfo=iUserServiceImpl.selectUserWithPage(page,count);
        request.setAttribute("user",pageInfo);
        return "back/setting";
    }

    //删除用户
    @PostMapping(value = "/setting/delete")
    @ResponseBody
    public UserResponseData deleteuser(@RequestParam int id){
        try {
            iUserServiceImpl.deleteUserDataAndComments(id);
            iUserServiceImpl.deleteUserWithId(id);
            logger.info("用户删除成功");
            return UserResponseData.ok();
        }catch (Exception e){
            logger.error("用户删除失败，错误信息是"+e.getMessage());
            return UserResponseData.fail();
        }
    }

}


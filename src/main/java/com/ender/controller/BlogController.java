package com.ender.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ender.common.lang.Result;
import com.ender.entity.Blog;
import com.ender.service.BlogService;
import com.ender.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ender
 * @since 2021-01-24
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    /**
     * 分页查询  不传默认为1
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page =new Page(currentPage,5);
        IPage iPage = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(iPage);
    }
    /**
     * 获取详情
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客不存在");
        return Result.succ(blog);
    }
    /**
     * 编辑与添加
     */
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result list(@RequestBody Blog blog){

//        Assert.isTrue(false, "公开版不能任意编辑！");

        Blog temp = null;
        if(blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.succ(null);
    }

}

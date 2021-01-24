package com.ender.service.impl;

import com.ender.entity.Blog;
import com.ender.mapper.BlogMapper;
import com.ender.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ender
 * @since 2021-01-24
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}

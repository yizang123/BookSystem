package cn.mybook.config.MyBaitsConfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: mybook
 * @description: 分页插件配置
 * @author: wqm
 * @create: 2021-07-20 09:28
 */
@Configuration
public class MybatisPlusPage {

    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }
}
package com.neuedu.framework;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/21  10:31 21
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Configuration
public class HisWebMvcConfig implements WebMvcConfigurer {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns(
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.png", "/**/*.jpg", "/**/*.gif",
                        "/**/*.eot", "/**/*.svg", "/**/*.ttf","/**/*.woff",
                        "/**/*.swf","/**/*.txt",
                        "/login.html" ,"/login",
                        "/getValidateCode"
                        );
    }
}

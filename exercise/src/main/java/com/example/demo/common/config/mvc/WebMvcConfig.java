package com.example.demo.common.config.mvc;
import com.example.demo.common.config.mvc.interceptor.RequestTimeConsumingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 添加拦截器，排出不拦截的静态资源
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeConsumingInterceptor())
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/v2/api-docs");
    }

//    /**
//     * 添加可访问的静态资源
//     * @param registry registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

}

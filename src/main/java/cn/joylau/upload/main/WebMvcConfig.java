package cn.joylau.upload.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by JoyLau on 2017/6/6.
 * cn.joylau.upload.main
 * 2587038142@qq.com
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 静态资源文件路径配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(resourceHandlerRegistry);
    }
}

package com.clairvoyantsoft.demo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
public class MvcConfigurer implements WebMvcConfigurer {


    @Autowired
    private LoggerInterceptor loggerInterceptor;

    /**
     * Configure spring MVC view resolver
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/JSP/");
        internalResourceViewResolver.setSuffix(".jsp");
        return  internalResourceViewResolver;
    }

    /**
     * Register spring MVC message converts
     * @param converters
     */
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        mappingJackson2HttpMessageConverter.getObjectMapper().registerModule(new Hibernate5Module());
        mappingJackson2HttpMessageConverter.getObjectMapper().registerModule( new Jdk8Module());
        converters.add(mappingJackson2HttpMessageConverter);

       /* MarshallingHttpMessageConverter xmlConverter =
                new MarshallingHttpMessageConverter();

        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);

        converters.add(xmlConverter);*/
    }

    /**
     *  Configure the converts to use ISO date formats.
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loggerInterceptor);
    }

    public FilterRegistrationBean<ErrorHandleFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<ErrorHandleFilter> filterFilterRegistrationBean =
                new FilterRegistrationBean<>();
        ErrorHandleFilter errorHandleFilter = new ErrorHandleFilter();
        filterFilterRegistrationBean.setFilter(errorHandleFilter);
        filterFilterRegistrationBean.addUrlPatterns("/filter/");
        filterFilterRegistrationBean.setOrder(2);
        return filterFilterRegistrationBean;
    }

    /**
     * For enabling internal view resolver otherwise JSP will not get rendered
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

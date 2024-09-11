package br.com.escconsulting.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource(ResourceLoader resourceLoader) {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);

        try {

            Resource[] resources = resolver.getResources("classpath:messages/**/*.properties");

            Set<String> basenameSet = new HashSet<>();

            for (Resource resource : resources) {
                try {
                    String path = resource.getURI().toString();
                    int startIndex = path.indexOf("/messages");
                    if (startIndex != -1) {
                        path = path.substring(startIndex + 1);
                    }
                    path = path.replaceAll("_[a-zA-Z]{2}_[A-Z]{2}", "").replaceAll("\\.properties$", "");
                    basenameSet.add("classpath:" + path);
                } catch (IOException e) {
                    throw new RuntimeException("Error processing resource", e);
                }
            }

            String[] basenames = basenameSet.toArray(new String[0]);
            messageSource.setBasenames(basenames);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load message sources", e);
        }

        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
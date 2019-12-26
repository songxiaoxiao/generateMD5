package moxi.core.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
public class AutoWiredConfiguration {

    @Bean(name = "test")
    public ThreadPoolTaskExecutor test() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setKeepAliveSeconds(300);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10000);
        return executor;
    }



}

package biz.belcorp.spring.springboot.sqs.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "queue")
public class ConfigurationUtilImpl implements ConfigurationUtil {
    private Map<String, String> portfolioQueues = new HashMap<>();
    private Map<String, String> balanceQueues = new HashMap<>();

    public Map<String, String> getPortfolioQueues() {
        return portfolioQueues;
    }

    @Override
    public Map<String, String> getBalanceQueues() {
        return balanceQueues;
    }
}

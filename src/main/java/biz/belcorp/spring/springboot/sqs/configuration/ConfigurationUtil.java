package biz.belcorp.spring.springboot.sqs.configuration;

import java.util.Map;

public interface ConfigurationUtil {
    Map<String, String> getPortfolioQueues();

    Map<String, String> getBalanceQueues();
}

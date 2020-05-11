package biz.belcorp.spring.springboot.sqs.bean;

import belcorp.biz.ssicc.collection.commons.bean.PortfolioGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendingPortfolioRQ {
    private PortfolioGroup portfolioGroup;
    private String queueUrl;
}

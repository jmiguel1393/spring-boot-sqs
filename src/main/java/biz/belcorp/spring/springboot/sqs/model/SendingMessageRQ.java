package biz.belcorp.spring.springboot.sqs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendingMessageRQ {
    private String message;
    private String url;
}

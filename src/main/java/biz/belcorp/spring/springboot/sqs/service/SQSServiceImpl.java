package biz.belcorp.spring.springboot.sqs.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class SQSServiceImpl implements QueueService {
    @Autowired
    AmazonSQS amazonSQS;

    @Override
    public String createQueue(String name) {
        CreateQueueRequest createStandardQueueRequest = new CreateQueueRequest(name);
        return amazonSQS.createQueue(createStandardQueueRequest).getQueueUrl();
    }

    @Override
    public boolean sendMessage(String message, String url, Map<String, String> attributes) {
        boolean send = true;
        try {
            Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            attributes.forEach((k, v) -> {
                messageAttributes.put(k, new MessageAttributeValue()
                        .withStringValue(v)
                        .withDataType("String"));
            });

            SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
                    .withQueueUrl(url)
                    .withMessageBody(message)
                    .withDelaySeconds(30)
                    .withMessageAttributes(messageAttributes);

            amazonSQS.sendMessage(sendMessageStandardQueue);
        } catch (Exception e) {
            send = false;
            log.error("Error in sending message", e);
        }
        return send;
    }

    @Override
    public List<Message> getMessages(String url) {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(url)
                .withWaitTimeSeconds(10)
                .withMaxNumberOfMessages(10);
        return amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
    }
}
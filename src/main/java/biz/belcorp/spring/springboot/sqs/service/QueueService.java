package biz.belcorp.spring.springboot.sqs.service;

import com.amazonaws.services.sqs.model.Message;

import java.util.List;
import java.util.Map;

public interface QueueService {
    String createQueue(String name);

    boolean sendMessage(String message, String url, Map<String, String> attributes);

    List<Message> getMessages(String url);
}

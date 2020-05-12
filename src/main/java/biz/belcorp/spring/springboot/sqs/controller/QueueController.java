package biz.belcorp.spring.springboot.sqs.controller;

import biz.belcorp.spring.springboot.sqs.bean.*;
import biz.belcorp.spring.springboot.sqs.configuration.ConfigurationUtil;
import biz.belcorp.spring.springboot.sqs.service.QueueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/queues")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QueueController {
    private final QueueService queueService;
    private final ConfigurationUtil configurationUtil;

    @GetMapping("/portfolios")
    public ResponseEntity<?> getAllPortfolioQueues() {
        return ResponseEntity.ok(configurationUtil.getPortfolioQueues());
    }

    @GetMapping("/balances")
    public ResponseEntity<?> getAllBalanceQueues() {
        return ResponseEntity.ok(configurationUtil.getBalanceQueues());
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody QueueCreationRQ queueCreationRQ) {
        QueueCreationRS queueCreationRS = new QueueCreationRS();
        queueCreationRS.setQueueUrl(queueService.createQueue(queueCreationRQ.getName()));
        if (queueCreationRS.getQueueUrl() != null && !queueCreationRS.getQueueUrl().isEmpty())
            return ResponseEntity.ok(queueCreationRS);
        else
            return new ResponseEntity<>("Queue creation error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/messages")
    public ResponseEntity<?> create(@RequestBody SendingMessageRQ sendingMessageRQ) {
        SendingMessageRS sendingMessageRS = new SendingMessageRS();
        sendingMessageRS.setSent(queueService.sendMessage(sendingMessageRQ.getMessage(), sendingMessageRQ.getQueueUrl(), null));
        return ResponseEntity.ok(sendingMessageRS);
    }

    @PostMapping("/messages/portfolios")
    public ResponseEntity<?> create(@RequestBody SendingPortfolioRQ sendingPortfolioRQ) {
        SendingMessageRS sendingMessageRS = new SendingMessageRS();
        sendingMessageRS.setSent(queueService.sendMessage(sendingPortfolioRQ.getPortfolioGroup(), sendingPortfolioRQ.getQueueUrl()));
        return ResponseEntity.ok(sendingMessageRS);
    }
}

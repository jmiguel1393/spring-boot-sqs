package biz.belcorp.spring.springboot.sqs.controller;

import biz.belcorp.spring.springboot.sqs.model.QueueCreationRQ;
import biz.belcorp.spring.springboot.sqs.model.QueueCreationRS;
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

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody QueueCreationRQ queueCreationRQ) {
        QueueCreationRS queueCreationRS = new QueueCreationRS();
        queueCreationRS.setQueueUrl(queueService.createQueue(queueCreationRQ.getName()));
        if (queueCreationRS.getQueueUrl() != null && !queueCreationRS.getQueueUrl().isEmpty())
            return ResponseEntity.ok(queueCreationRS);
        else
            return new ResponseEntity<>("Queue creation error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

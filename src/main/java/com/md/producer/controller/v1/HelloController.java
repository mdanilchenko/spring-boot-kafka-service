package com.md.producer.controller.v1;

import com.md.producer.dto.v1.HelloDTO;
import com.md.producer.entity.Hello;
import com.md.producer.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(value = "/api/v1/hello")
@Api(value = "Hello")
public class HelloController {

    private KafkaTemplate<String, Hello> kafkaTemplate;

    private HelloService helloService;

    private static final ModelMapper mapper = new ModelMapper();

    @Autowired
    public HelloController(KafkaTemplate<String, Hello> kafkaTemplate, HelloService helloService) {
        this.kafkaTemplate = kafkaTemplate;
        this.helloService = helloService;
    }

    @ApiOperation(value = "Send Test Message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed successfully", response = HelloDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloDTO> send() {

        Hello message = new Hello(123123L,"Hello","uuidhewdiweudhwi", new Date());

        ListenableFuture<SendResult<String, Hello>> future = kafkaTemplate.send("TestTopic", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Hello>>() {

            @Override
            public void onSuccess(SendResult<String, Hello> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });

        return null;
    }

}

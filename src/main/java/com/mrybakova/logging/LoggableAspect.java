package com.mrybakova.logging;

import com.mrybakova.kafka.KafkaProducerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static com.mrybakova.OperationHelper.getUUIDAndTimeStamp;

@Aspect
@Component
public class LoggableAspect {

    public final KafkaProducerService kafkaProducerService;

    public LoggableAspect(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Pointcut("@annotation(Loggable)")
    public void loggableMethods() {
    }

    @Around("loggableMethods()")
    public Object collectLogs(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        Loggable loggable = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Loggable.class);
        if (loggable != null) {
            sendMessageToKafka(getUUIDAndTimeStamp(loggable.methodType()));
        }
        return result;
    }

    public void sendMessageToKafka(String message) {
        kafkaProducerService.sendMessage(message);
    }
}

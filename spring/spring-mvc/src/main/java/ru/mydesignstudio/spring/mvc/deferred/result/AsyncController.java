package ru.mydesignstudio.spring.mvc.deferred.result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("")
public class AsyncController {
    @GetMapping("deferred")
    public DeferredResult<String> getDeferredMessage() {
        final DeferredResult<String> result = new DeferredResult<>();
        runSeparately(() -> result.setResult("Some result"));
        return result;
    }

    @GetMapping("callable")
    public Callable<String> getCallableMessage() {
        return () -> "Another result";
    }

    @GetMapping("streaming")
    public ResponseBodyEmitter streamingMessaged() {
        final ResponseBodyEmitter bodyEmitter = new ResponseBodyEmitter();
        runSeparately(
                () -> bodyEmitter.send("First"),
                () -> bodyEmitter.send("Second"),
                () -> bodyEmitter.send("Third"),
                () -> bodyEmitter.complete()
        );
        return bodyEmitter;
    }

    private void runSeparately(RunnableWithException... runnables) {
        new Thread(() -> {
            for (RunnableWithException runnable : runnables) {
                try {
                    Thread.sleep(10);
                    runnable.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private interface RunnableWithException {
        void run() throws Exception;
    }
}

package ru.mydesignstudio.spring.aop.proxy.factory;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;

public class DebugInterceptor extends SimpleTraceInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }
}

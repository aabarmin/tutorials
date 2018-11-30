package sort.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortUtils {
    public static <T> List<T> wrapCollection(List<T> source) {
        final Object proxyInstance = Proxy.newProxyInstance(
                SortUtils.class.getClassLoader(),
                source.getClass().getInterfaces(),
                new CountingInvocationHandler(source)
        );
        return (List<T>) proxyInstance;
    }

    public static List<Comparable> toList(Comparable... args) {
        final List<Comparable> list = new ArrayList<>();
        for (Comparable arg : args) {
            list.add(arg);
        }
        return list;
    }

    public static class CountingInvocationHandler implements InvocationHandler {
        private final Map<Method, Integer> invocations = new HashMap<>();
        private final Object countable;

        public CountingInvocationHandler(Object countable) {
            this.countable = countable;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(countable, args);
            } finally {
                if (!invocations.containsKey(method)) {
                    invocations.put(method, 0);
                }
                invocations.put(method, invocations.get(method) + 1);
            }
        }
    }
}

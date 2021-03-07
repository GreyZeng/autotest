package auto.test.wordcount.executor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/4
 * @since
 */
public class ExecutorProxy implements InvocationHandler {
    private Executor executor;
    private static final Logger log = LoggerFactory.getLogger(ExecutorProxy.class);
    // 程序运行时间
    private Long runtime; 
    
    public ExecutorProxy(Executor executor) {
        this.executor = executor;
    }

    public Long getRuntime() {
        return runtime;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("开始执行耗时统计");
        long start = System.nanoTime();
        Object o = method.invoke(executor, args);
        
        runtime = System.nanoTime() - start;
        log.info("程序耗时：" + runtime);

        return o;
    }
}

package app.wordcount.executor;
 
import git.autotest.executor.Executor;
import org.junit.jupiter.api.Test;

/**
 * Created by siberia0015 on 2021/3/15.
 */
public class NodeJSExecutorTest {

    @Test
    public void exec() {
        Executor executor = new NodeJSExecutor();
        executor.exec("E:\\WordCount-Java\\PersonalProject-Java\\221801238\\src\\WordCount.js",
                "E:\\WordCountAutoTest\\WordCountAutoTest\\download\\20210314\\cases\\1.txt" +
                        " E:\\WordCount-Java\\PersonalProject-Java\\221801238\\output\\2.txt");
    }
}

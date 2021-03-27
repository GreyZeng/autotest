package git.snippets.wordcount.executor;

import auto.test.wordcount.executor.impl.JavaExecutor;
import git.snippets.executor.Executor;
import org.junit.jupiter.api.Test;

public class JavaExecutorTest {

    @Test
    public void exec() {
        Executor executor = new JavaExecutor();


        executor.compile("C:\\git\\wordcount\\src\\WordCount.java");
        executor.exec("C:\\git\\wordcount\\src\\WordCount.java", "C:\\git\\rural.txt");
    }
}
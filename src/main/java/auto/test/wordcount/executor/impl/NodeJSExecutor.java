package auto.test.wordcount.executor.impl;

import auto.test.wordcount.executor.Executor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static auto.test.wordcount.Client.NODEJS_EXE_LOCATION;
import static auto.test.wordcount.utils.CmdUtil.cmd;

/**
 * NodeJS执行程序
 *
 * @author <a href="mailto:18965375150@163.com">siberia0015</a>
 * @date 2021/3/15
 * @since
 */
public class NodeJSExecutor implements Executor {

    private static final Logger log = LoggerFactory.getLogger(NodeJSExecutor.class);

    @Test
    public void main() {

    }

    /**
     * 编译源文件
     *
     * @param mainFile Main方法的全路径
     */
    @Override
    public void compile(String mainFile) {
        // nodejs不用编译
    }

    /**
     * 获取要执行的文件名
     *
     * @return 要执行的js文件名
     */
    @Override
    public String mainFile() {
        return "WordCount.js";
    }

    /**
     * @param mainFile Main方法的全路径
     * @param input    测试用例参数 eg: input.txt output.txt
     * @return 测试用例的执行时间
     */
    @Override
    public long exec(String mainFile, String input) {
        log.info("开始执行 {} 输入参数 {}", mainFile, input);
        return nodeJS(mainFile, input);
    }

    /**
     * 执行JS程序
     * node WordCount.js input.txt output.txt
     */
    public long nodeJS(String mainFile, String input) {
        String cmd = NODEJS_EXE_LOCATION + " " + mainFile + " " + input.trim();
        log.info("begin to exec {}", cmd);
        return cmd(cmd, 120);
    }
}

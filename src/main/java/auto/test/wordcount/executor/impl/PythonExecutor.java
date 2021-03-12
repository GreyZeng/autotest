package auto.test.wordcount.executor.impl;

import auto.test.wordcount.executor.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static auto.test.wordcount.Client.PYTHON_EXE_LOCATION;
import static auto.test.wordcount.utils.CmdUtil.cmd;

/**
 * Python执行程序
 *
 * @author <a href="mailto:18965375150@163.com">siberia0015</a>
 * @date 2021/3/5
 * @since
 */
public class PythonExecutor implements Executor {
    /**
     * 编译源文件
     *
     * @param mainFile Main方法的全路径
     */
    @Override
    public void compile(String mainFile) {
        // python不用编译
    }

    /**
     * 获取要执行的文件名
     *
     * @return 要执行的python文件名
     */
    @Override
    public String mainFile() {
        return "WordCount.py";
    }

    private static final Logger log = LoggerFactory.getLogger(PythonExecutor.class);

    /**
     * @param mainFile Main方法的全路径
     * @param input    测试用例参数 eg: -n input.txt
     * @return 测试用例的执行时间
     */
    @Override
    public long exec(String mainFile, String input) {
        log.info("开始执行 {} 输入参数 {}", mainFile, input);
        return python(mainFile, input);
    }

    /*public static void main(String[] args) {
        Executor executor = new PythonExecutor();

         executor.exec("E:\\WordCountAutoTest\\test\\Main.py", "E:\\WordCountAutoTest\\test\\input.txt E:\\WordCountAutoTest\\test\\output.txt");

    }*/

    /**
     * 执行Python程序
     * python Main.py
     */
    public long python(String mainFile, String input) {
        String cmd = PYTHON_EXE_LOCATION + " " + mainFile + " " + input.trim();
        log.info("begin to exec {}", cmd);
        return cmd(cmd, 120);
    }

}


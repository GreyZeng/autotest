package app.wordcount.executor;

import git.autotest.executor.Executor;
import git.autotest.utils.CmdUtil;
import git.autotest.utils.PropertyUtil;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Python执行程序
 *
 * @author <a href="mailto:18965375150@163.com">siberia0015</a>
 * @date 2021/3/5
 * @since
 */
public class PythonExecutor implements Executor {

    private static final Logger log = getLogger(PythonExecutor.class);
 

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

    /**
     * @param mainFile Main方法的全路径
     * @param input    测试用例参数 eg: input.txt output.txt
     * @return 测试用例的执行时间
     */
    @Override
    public long exec(String mainFile, String input) {
        log.info("开始执行 {} 输入参数 {}", mainFile, input);
        return python(mainFile, input);
    }

    /**
     * 执行Python程序
     * python WordCount.py input.txt output.txt
     */
    public long python(String mainFile, String input) {
        String cmd = (String) PropertyUtil.get("PYTHON_EXE_LOCATION") + " " + mainFile + " " + input.trim();
        log.info("begin to exec {}", cmd);
        return CmdUtil.cmd(cmd, 120);
    }

}


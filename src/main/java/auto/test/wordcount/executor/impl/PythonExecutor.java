package auto.test.wordcount.executor.impl;

import auto.test.wordcount.executor.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static auto.test.wordcount.utils.CmdUtil.cmd;

/**
 * Pythonִ�г���
 *
 * @author <a href="mailto:18965375150@163.com">siberia0015</a>
 * @date 2021/3/5
 * @since
 */
public class PythonExecutor implements Executor {
    /**
     * ����Դ�ļ�
     *
     * @param mainFile Main������ȫ·��
     */
    @Override
    public void compile(String mainFile) {
        // python���ñ���
    }

    /**
     * ��ȡҪִ�е��ļ���
     *
     * @return Ҫִ�е�python�ļ���
     */
    @Override
    public String mainFile() {
        return "WordCount.py";
    }

    private static final Logger log = LoggerFactory.getLogger(JavaExecutor.class);

    /**
     * @param mainFile Main������ȫ·��
     * @param input    ������������ eg: -n input.txt
     * @return ����������ִ��ʱ��
     */
    @Override
    public long exec(String mainFile, String input) {
        log.info("��ʼִ�� {} ������� {}", mainFile, input);
        return python(mainFile, input);
    }

    /*public static void main(String[] args) {
        Executor executor = new PythonExecutor();

         executor.exec("E:\\WordCountAutoTest\\test\\Main.py", "E:\\WordCountAutoTest\\test\\input.txt E:\\WordCountAutoTest\\test\\output.txt");

    }*/
    /**
     * ִ��Python����
     * python Main.py
     */
    public long python(String mainFile, String input) {
        String cmd = "python " + mainFile + " " + input.trim();
        log.info("begin to exec {}", cmd);
        return cmd(cmd, 120);
    }

}

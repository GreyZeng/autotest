package auto.test.wordcount.executor.impl;

import auto.test.wordcount.executor.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static auto.test.wordcount.utils.CmdUtil.cmd;
import static auto.test.wordcount.utils.FileUtil.getFolder;

/**
 * Java执行程序
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/2
 * @since
 */
public class JavaExecutor implements Executor {
    /**
     * @param mainFile
     */
    @Override
    public void compile(String mainFile) {
        log.info("开始编译 {}", mainFile);
        String defaultClassPath = getFolder(mainFile);
        javac(defaultClassPath, mainFile);
    }

    /**
     * @param mainFile Main方法的全路径
     * @param input    测试用例参数 eg: -n input.txt
     * @return 测试用例的执行时间
     */
    @Override
    public long exec(String mainFile, String input) {
        log.info("开始执行 {} 输入参数 {}", mainFile, input);
        return java(getFolder(mainFile), getCompileClassName(mainFile), input);
    }

    private static final Logger log = LoggerFactory.getLogger(JavaExecutor.class);

    @Override
    public String mainFile() {
        return "WordCount.java";
    }


    /**
     * 获取mainFile编译后的名字，默认就是mainFile的文件名
     * C:\git\wordcount\src\Main.java -> Main
     *
     * @param mainFile Main文件的全路径
     * @return
     */
    private static String getCompileClassName(String mainFile) {
        return mainFile.substring(mainFile.lastIndexOf(File.separator) + 1, mainFile.lastIndexOf("."));
    }


    /**
     * 执行Java程序
     * java -classpath C:\git\wordcount\src Main
     *
     * @param classPath 指定classpath，如果不指定，则默认为Main文件所在目录
     * @param mainClass 编译后的Main文件
     * @param input     输入参数
     */
    public long java(String classPath, String mainClass, String input) {
        String cmd = "java -classpath " + classPath + " " + mainClass + " " + input.trim();
        log.info("begin to exec {}", cmd);
        return cmd(cmd, 120);
    }

    /**
     * 编译Java程序
     * javac -encoding UTF-8 -cp C:\git\wordcount\src C:\git\wordcount\src\WordCount.java
     *
     * @param classPath     指定classpath，如果不指定，则默认为Main文件所在目录 以上例子 classPath为：C:\git\wordcount\src
     * @param mainClassPath Main文件的全路径 以上例子 mainClass为：C:\git\wordcount\src\Main.java
     */
    public void javac(String classPath, String mainClassPath) {
        String cmd = "javac -encoding UTF-8 -cp " + classPath + " " + mainClassPath.replace(mainFile(), "*.java");
        log.info("begin to compile {}", cmd);
        cmd(cmd, 5);
    }


}

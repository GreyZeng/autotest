package auto.test.wordcount.executor;

import auto.test.wordcount.Result;

import java.io.File;
import java.io.IOException;

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
     * @param mainFile Main方法的全路径
     * @param input    测试用例参数 eg: -n input.txt
     * @return
     */
    @Override
    public Result exec(String mainFile, String input) {
        String defaultClassPath = getFolder(mainFile);
        String mainClass = getCompileClassName(mainFile);
        javac(defaultClassPath, mainFile);
        java(defaultClassPath, mainClass, input);
        return null;
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
    public void java(String classPath, String mainClass, String input) {
        try {
            if (null != input && !input.trim().equals("")) {
                cmd("java -classpath " + classPath + " " + mainClass);
            } else {
                cmd("java -classpath " + classPath + " " + mainClass + " " + input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 编译Java程序
     * javac -encoding UTF-8 -cp C:\git\wordcount\src C:\git\wordcount\src\Main.java
     *
     * @param classPath     指定classpath，如果不指定，则默认为Main文件所在目录 以上例子 classPath为：C:\git\wordcount\src
     * @param mainClassPath Main文件的全路径 以上例子 mainClass为：C:\git\wordcount\src\Main.java
     */
    public void javac(String classPath, String mainClassPath) {
        try {
            cmd("javac -encoding UTF-8 -cp " + classPath + " " + mainClassPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package auto.test.wordcount.executor.impl;

import static auto.test.wordcount.utils.CmdUtil.cmd;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import auto.test.wordcount.executor.Executor;

/**
 * C++执行程序
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/2
 * @since
 */
public class CppExecutor implements Executor {
    /**
     * @param mainFile Main方法的全路径
     */	
    @Override
    public void compile(String mainFile) {
    	log.info("开始编译 {} 将生成 {}", getMainFileDirectory(mainFile) + getMainFileExtension(), getMainFileDirectory(mainFile) + getExcutableFileName());
    	cmd("g++ " + getMainFileDirectory(mainFile) + getMainFileExtension() + " -o " + getMainFileDirectory(mainFile) + getExcutableFileName(), 5);
    }

    /**
     * @param mainFile Main方法的全路径
     * @param input    测试用例参数 eg: -n input.txt
     * @return 测试用例的执行时间
     */
    @Override
    public long exec(String mainFile, String input) {
    	log.info("开始执行 {} 输入参数 {}", getMainFileDirectory(mainFile) + getExcutableFileName(), input);
        return cmd(getMainFileDirectory(mainFile) + getExcutableFileName() + " " + input.trim(), 10);
    }
    
    private static final Logger log = LoggerFactory.getLogger(CppExecutor.class);

    @Override
    public String mainFile() {
        return "WordCount.cpp";
    }

    /**
     * 获取mainFile所在目录，用于编译该目录下的所有源文件，或者执行该目录下的可执行文件
     * C:\git\wordcount\src\Main.cpp -> C:\git\wordcount\src\
     *
     * @param mainFile Main文件的全路径
     * @return
     */
    private static String getMainFileDirectory(String mainFile) {
        return mainFile.substring(0, mainFile.lastIndexOf(File.separator) + 1);
    }
    
    /**
     * 获取mainFile源文件后缀，生成所有表示所有该后缀文件的字符串
     * Main.cpp -> *.cpp
     *
     * @param mainFile Main文件的全路径
     * @return
     */
    private String getMainFileExtension() {
        return "*" + mainFile().substring(mainFile().lastIndexOf("."));
    }
    
    /**
     * 获取mainFile编译后的名字，默认指定为mainFile的文件名 + .exe后缀
     * C:\git\wordcount\src\Main.cpp -> Main.exe
     *
     * @param mainFile Main文件的全路径
     * @return
     */
    private String getExcutableFileName() {
        return mainFile().substring(0, mainFile().lastIndexOf(".")) + ".exe";
    }

}

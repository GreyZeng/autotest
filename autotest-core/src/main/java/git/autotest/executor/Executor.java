package git.autotest.executor;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/2
 * @since
 */
public interface Executor {
    /**
     * 编译程序
     *
     * @param src 源码目录
     */
    void compile(String src);

    /**
     * 执行程序
     *
     * @param src  源码目录
     * @param args 命令参数
     */
    long exec(String src, String args);

    /**
     * 返回语言主执行程序
     *
     * @return 主执行程序名称
     */
    String mainFile();
}

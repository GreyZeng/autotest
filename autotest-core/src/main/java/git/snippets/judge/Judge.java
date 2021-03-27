package git.snippets.judge;

import git.snippets.Result;

/**
 * 评测
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/4
 * @since
 */
public interface Judge {
    /**
     * 评判方法
     *
     * @param result 结果文件，可以是内容，也可以是路径，看各个题目要求定
     * @param answer 答案文件
     * @return
     */
    Result judge(String result, String answer);
}

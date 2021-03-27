package git.snippets;

/**
 * TODO 这个类的设计需要再考虑，暂不抽象，目前先实现功能
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/4
 * @since
 */
public class Result {
    @Override
    public String toString() {
        return "Result{" +
                "src='" + src + '\'' +
                ", result='" + result + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                '}';
    }

    /**
     * 代码目录
     */
    private String src;
    /**
     * 输出结果
     */
    private String result;
    /**
     * 测试用例
     */
    private String answer;
    private int score;

    public String getSrc() {
        return src;
    }

    public Result src(String src) {
        this.src = src;
        return this;
    }

    public String getResult() {
        return result;
    }

    public Result result(String result) {
        this.result = result;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Result answer(String answer) {
        this.answer = answer;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Result score(int score) {
        this.score = score;
        return this;
    }
}

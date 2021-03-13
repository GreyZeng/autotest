package auto.test.wordcount.model;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */
public class JudgeItem {
    private String score;
    private String time;

    public JudgeItem() {
    }

    public JudgeItem(String score, String time) {
        this.score = score;
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

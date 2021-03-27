package git.autotest.model;

import java.util.List;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/5
 */

public class JudgeResult {
    private String studentNo;
    private List<JudgeItem> score;
    private String commitTimes;
    private String commitDetails;

    public JudgeResult(String studentNo, List<JudgeItem> score, String commitTimes, String commitDetails) {
        this.studentNo = studentNo;
        this.score = score;
        this.commitTimes = commitTimes;
        this.commitDetails = commitDetails;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public List<JudgeItem> getScore() {
        return score;
    }

    public void setScore(List<JudgeItem> score) {
        this.score = score;
    }

    public String getCommitDetails() {
        return commitDetails;
    }

    public void setCommitDetails(String commitDetails) {
        this.commitDetails = commitDetails;
    }

    public String getCommitTimes() {
        return commitTimes;
    }

    public void setCommitTimes(String commitTimes) {
        this.commitTimes = commitTimes;
    }
}

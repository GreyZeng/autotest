package git.snippets.model;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/7
 * @since
 */
public class TestCase {
    private String caseLocation;
    private String answerLocation;
    private String caseId;

    public TestCase(String caseLocation, String answerLocation, String caseId) {
        this.caseLocation = caseLocation;
        this.answerLocation = answerLocation;
        this.caseId = caseId;
    }

    public String getCaseLocation() {
        return caseLocation;
    }

    public void setCaseLocation(String caseLocation) {
        this.caseLocation = caseLocation;
    }

    public String getAnswerLocation() {
        return answerLocation;
    }

    public void setAnswerLocation(String answerLocation) {
        this.answerLocation = answerLocation;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}

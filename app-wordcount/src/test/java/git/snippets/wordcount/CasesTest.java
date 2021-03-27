package git.snippets.wordcount;

import git.snippets.model.TestCase;
import git.snippets.wordcount.generator.WordCountTestCasesGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

/**
 * @author <a href="mailto:18965375150@163.com">siberia0015</a>
 * @date 2021/3/6
 * @since
 */
public class CasesTest {
    /**
     * 测试生成随机题目和答案
     */
    @Test
    public void casesTest() {
        int num = 5;
        File tmp = new File("download");
        tmp.mkdir();

        WordCountTestCasesGenerator test = new WordCountTestCasesGenerator(num, tmp.getAbsolutePath(), 1000000, 100);
        System.out.println(test.getTestCases());
    }

    /**
     * 测试生成用例地址能否正确被获取
     */
    @Test
    public void test() {
        int num = 5;
        File tmp = new File("download");
        tmp.mkdir();
        WordCountTestCasesGenerator generator = new WordCountTestCasesGenerator(num, tmp.getAbsolutePath(), 100000, 100);
        Map<String, TestCase> testCases = generator.getTestCases();
        for (String caseId : testCases.keySet()) {
            TestCase testCase = testCases.get(caseId); //储存用例地址和答案地址
            String testCaseLocation = testCase.getCaseLocation();
            String answerLocation = testCase.getAnswerLocation();
            System.out.println(testCaseLocation);
            System.out.println(answerLocation);
        }

    }
}

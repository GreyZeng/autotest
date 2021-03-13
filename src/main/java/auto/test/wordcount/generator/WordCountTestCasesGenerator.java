package auto.test.wordcount.generator;

import auto.test.wordcount.model.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成wordcount程序的测试用例
 *
 * @author <a href="mailto:18965375150@163.com">siberia0015</a>
 * @date 2021/3/6
 * @since
 */
public class WordCountTestCasesGenerator {
    private int nums;
    private String repo;
    private Map<String, TestCase> testCases;
    private int maxLength;
    private int minLength;
    private static final Logger log = LoggerFactory.getLogger(WordCountTestCasesGenerator.class);

    public Map<String, TestCase> getTestCases() {
        return testCases;
    }

    public WordCountTestCasesGenerator(int nums, String repo, int maxLength, int minLength) {
        this.nums = nums;
        this.repo = repo;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.testCases = testCases();
    }

    // 如果用例和答案准备好了，返回准备好的用例和答案信息，不需要重新新建测试用例和答案
    private Map<String, TestCase> testCases() {
        // 生成nums数量的题目
        // config的key是题目编号，value的key是题目位置，value的value是答案的位置
        // 题目和答案位置一律放在某个写死的路径里面
        // 目前先只管生成题目，答案一律为空文件

        Map<String, TestCase> config = new HashMap<>(nums);

        // 生成目录
        File root = new File(repo);
        if (!root.exists()) {
            root.mkdir();
        }
        File caseFolder = new File(root.getAbsolutePath(), "cases");// new File("download\\" + stamp + "\\cases\\");
        if (!caseFolder.exists()) {
            caseFolder.mkdirs();
        }
        File answerFolder = new File(root.getAbsolutePath(), "answers");
        if (!answerFolder.exists()) {
            answerFolder.mkdirs();
        }

        // 生成测试用例的长度范围

        // 判断测试用例是否准备就绪
        boolean isReady = false;
        File cases[] = caseFolder.listFiles();
        File answers[] = answerFolder.listFiles();
        if (cases.length > 0 && answers.length > 0) {
            isReady = true;
            log.info("测试用例已就绪！");
            if (cases.length != nums || answers.length != nums) {
                log.error("传入参数错误！");
                return config;
            }
        }

        for (int count = 1; count <= nums; count++) {
            TestCase question = new TestCase(caseFolder + File.separator + count + ".txt", answerFolder + File.separator + count + ".txt", count + "");
            config.put(count + "", question);
            if (!isReady) {
                log.info("生成新测试用例……");
                // 写入题目文件
                int length = (int) (1 + Math.random() * (maxLength - minLength + 1)); //生成随机长度
                String caseContent = DataGenerator.generateContent(length); //生成随机字符串
                write(caseContent, caseFolder, count + ".txt");
                //写入答案文件
                String answerContent = ""; //生成的答案赋值给answerContent即可
                write(answerContent, answerFolder, count + ".txt");
            } else {
                continue;
            }
        }
        return config;
    }

    private void write(String content, File folder, String fileName) {
        BufferedWriter bw;
        try {
            File file = new File(folder, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            log.error("error to generate test cases {}", e.getMessage());
        }
    }
}

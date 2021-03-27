package git.snippets.wordcount.judge;

import git.snippets.judge.Judge;
import org.slf4j.Logger;
import git.snippets.Result;

import java.io.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * wordcount作业的Judge
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/4
 * @since
 */
public class WordCountJudge implements Judge {
    private static final Logger log = getLogger(WordCountJudge.class);

    /**
     * 评分，参考：WordCountTester#checkValid 方法
     *
     * @param resultPath 结果文件路径
     * @param answerPath 答案文件路径
     * @return
     */
    @Override
    public Result judge(String resultPath, String answerPath) {
        Result result = new Result();
        result.result(resultPath).answer(answerPath);
        try {
            int i = checkValid(answerPath, resultPath);
            result.score(i);
        } catch (IOException e) {
            log.error("fail to check :{} {}", resultPath, e.getMessage());
            result.score(-1);
        }
        return result;
    }


    //Overview: 将标程standard生成的result文件和用户生成的result文件进行对比，完全一致则
    public static int checkValid(String standardPath, String filePath) throws FileNotFoundException {
        int count = 0;
        InputStreamReader isrStandard = new InputStreamReader(new FileInputStream(standardPath));
        InputStreamReader isrTest = new InputStreamReader(new FileInputStream(filePath));
        String strStandard, strTest;
        try (BufferedReader buffStandard = new BufferedReader(isrStandard);
             BufferedReader buffTest = new BufferedReader(isrTest)) {
            strStandard = buffStandard.readLine();
            strTest = buffTest.readLine();
            if (strStandard.equals(strTest)) {
                //第一行characters: n是相同的 得1分
                count += 1;
            }
            strStandard = buffStandard.readLine();
            strTest = buffTest.readLine();
            if (strStandard.equals(strTest)) {
                //第二行words: m是相同的 得2分
                count += 2;
            }
            strStandard = buffStandard.readLine();
            strTest = buffTest.readLine();
            if (strStandard.equals(strTest)) {
                //第三行lines: m是相同的  得2分
                count += 2;
            }
            //检查核心部分是否正确(求词频、求词组、求特定出现量的单词)
            boolean checkMain = true;
            while ((strStandard = buffStandard.readLine()) != null) {
                strTest = buffTest.readLine();
                if (!strStandard.equals(strTest)) {
                    checkMain = false;
                    break;
                }
            }
            //核心部分占5分 完全正确才能得分，否则0分
            if (checkMain) {
                count += 5;
            }
            return count;
        } catch (Exception e) {
            log.error("execute error found {} {}", e.getMessage(), standardPath);
            //其它错误
            return -1;
        }
    }
}


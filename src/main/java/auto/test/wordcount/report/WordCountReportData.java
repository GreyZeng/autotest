package auto.test.wordcount.report;

import auto.test.wordcount.model.JudgeItem;
import auto.test.wordcount.model.JudgeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/5
 * @since
 */

public class WordCountReportData implements ReportData {
    private final List<JudgeResult> results;

    private static final Logger log = LoggerFactory.getLogger(WordCountReportData.class);

    public WordCountReportData(List<JudgeResult> results) {
        this.results = results;
    }

    @Override
    public String[] headers() {
        if (results.isEmpty()) {
            throw new RuntimeException("export data is empty");
        }
        final long count = results.get(0).getScore().size();
        final String[] headers = new String[(int) (count * 2 + 8)];
        headers[0] = "StudentNo";
        headers[1] = "test_Score";
        int index = 2;
        for (int i = 1; i <= count; index++) {
            if (index % 2 == 0) {
                headers[index] = "Score" + i;
            } else {
                headers[index] = "Time" + i;
                i++;
            }
        }
        headers[index++] = "correct_score";
        headers[index++] = "performance_score";
        headers[index++] = "rule_score";
        headers[index++] = "final_score";
        // 学生的提交次数
        headers[index++] = "commit_times";
        // 提交详情
        headers[index] = "commit_details";
        return headers;
    }


    @Override
    public List<List<String>> records() {
        List<List<String>> records = new ArrayList<>();

        for (JudgeResult result : results) {
            List<String> record = new ArrayList<>();
            record.add(result.getStudentNo());
            final Double scores = result.getScore().stream().mapToDouble(x -> Double.parseDouble(x.getScore())).sum();
            record.add(String.valueOf(scores));

            BigDecimal sumTime = new BigDecimal(0d);
            int times = 0;
            for (JudgeItem judgeItem : result.getScore()) {
                record.add(judgeItem.getScore());
                record.add(judgeItem.getTime());

                // 计算汇总的时间
                sumTime = sumTime.add(new BigDecimal(Double.parseDouble(judgeItem.getTime())));
                times++;
            }

            // 计算平均时间
            Double avgTime = sumTime.divide(new BigDecimal(Double.parseDouble(String.valueOf(times)))).doubleValue();


            // 正确性得分
            Double correctScore = calCorrectScore(scores);
            record.add(String.valueOf(correctScore));

            // FIXME 性能得分
            Double performanceScore = correctScore >= 30d ? 10d : 0d;

            record.add(String.valueOf(performanceScore));

            // commit次数规则符合得分
            Double ruleScore = calRuleScore(result.getCommitTimes());
            record.add(String.valueOf(ruleScore));

            // 总得分
            Double finalScore = calFinalScore(correctScore, performanceScore, ruleScore);
            record.add(String.valueOf(finalScore));
            record.add(result.getCommitTimes());
            record.add(result.getCommitDetails());
            records.add(record);
        }
        return records;
    }

    private Double calFinalScore(Double correctScore, Double performanceScore, Double ruleScore) {
        BigDecimal cS = new BigDecimal(correctScore);
        BigDecimal pS = new BigDecimal(performanceScore);
        BigDecimal rS = new BigDecimal(ruleScore);
        BigDecimal add = cS.add(pS);
        BigDecimal add1 = add.add(rS);
        return add1.doubleValue();
    }


    // TODO 这里写死了提交次数必须大于等于十次才能得到要求分3分
    private Double calRuleScore(String commitTimes) {
        try {
            int i = Integer.parseInt(commitTimes);
            if (i >= 10) {
                return 3d;
            }
            return 0d;
        } catch (Exception e) {
            log.error("parse commit times error {}", commitTimes);
            return 0d;
        }


    }

    //TODO 这里写死了总分，应该根据不同题目的总分来确定这个值
    private Double calCorrectScore(Double scores) {
        BigDecimal decimal = new BigDecimal(scores);
        BigDecimal sum = new BigDecimal(100d);
        BigDecimal base = new BigDecimal(30d);
        BigDecimal multiply = base.multiply(decimal);
        BigDecimal divide = multiply.divide(sum);
        return divide.doubleValue();
    }


}

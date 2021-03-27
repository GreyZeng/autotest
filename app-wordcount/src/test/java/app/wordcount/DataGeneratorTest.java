package app.wordcount;

import git.autotest.generator.DataGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/t
 * @since
 */
public class DataGeneratorTest {

    /**
     * 测试生成随机指定长度字符串
     */
    @Test
    public void testGenerateContent(){
        int len = 1000;
        final String content = DataGenerator.generateContent(len);
        System.out.println(content);
        assert len == content.length();
    }
}

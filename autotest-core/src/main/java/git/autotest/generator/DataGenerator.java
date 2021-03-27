package git.autotest.generator;

import java.util.Random;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/3
 * @since
 */
public class DataGenerator {
    /**
     * 生成指定长度的随机字符串
     *
     * @param len 指定长度
     * @return 指定长度随机字符串
     */
    public static String generateContent(int len) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        while (count <= len){
            if(len - count < 5){
                builder.append(generateWord(len - count));
                break;
            }
            int wordCount;
            do{
                wordCount = new Random().nextInt(10);
            }while(wordCount >= (len - count - 1));

            builder.append(generateWord(wordCount));
            builder.append(generateSpace());

            count += wordCount + 1;
        }
        return builder.toString();
    }

    /**
     * 生成指定长度随机单词
     * @param len 指定长度
     * @return 指定长度随机单词
     */
    private static char[] generateWord(int len){
        char[] word = new char[len];
        for (int i = 0; i < len; i++) {
            word[i] = generateASCII();
        }
        return word;
    }

    /**
     * 生成随机ASCII码
     * @return  char 随机ASCII码
     */
    private static char generateASCII() {
        if (Math.random() > 0.5) {
            // 生成大写字母
            // A-Z: 65 ~ 90
            return (char) (new Random().nextInt(26) + 65);
        } else {
            // 生成小写字母
            // a-z: 97 ~ 122
            return (char) (new Random().nextInt(26) + 97);
        }
    }

    /**
     * 生成随机间隔字符
     * @return char 间隔字符
     */
    private static char generateSpace() {
        int noneAlpha = (int) (Math.random() * 127);
        double ctrlChance = (double) noneAlpha / (double) 31;
        if (ctrlChance < 0.2) {
            noneAlpha = '\r';
        } else if (ctrlChance < 0.4) {
            noneAlpha = '\n';
        } else if (ctrlChance < 1) {
            noneAlpha = '\t';
        }
        return (char) (noneAlpha);
    }

}

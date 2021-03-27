package git.snippets.wordcount.judge;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class WordCountJudgeTest {

    @Test
    public void checkValid() throws FileNotFoundException {

        String standardPath = "D:\\git\\WordCountAutoTest\\download\\1615249322321\\answers\\3.txt";
        String filePath = "D:\\git\\WordCountAutoTest\\download\\1615249322321\\PersonalProject-Java\\221801432\\output\\3.txt";
        int score = WordCountJudge.checkValid(standardPath, filePath);
        System.out.println(score);
    }
}
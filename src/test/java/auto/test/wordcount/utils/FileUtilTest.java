package auto.test.wordcount.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static auto.test.wordcount.generator.DataGenerator.generateContent;
import static auto.test.wordcount.utils.FileUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUtilTest {

    @Test
    public void testSameContent() throws IOException {
        // 长度有限制
        int len = 1808033;
        String encoding = "UTF-8";
        String[] fileAPath = generateEmptyFile();
        String[] fileBPath = generateEmptyFile();
        String fileContent = generateContent(len);
        contentToFile(fileContent, fileAPath[1]);
        contentToFile(fileContent, fileBPath[1]);
        assertEquals(content(fileAPath[1], encoding), fileContent);
        assertEquals(content(fileBPath[1], encoding), fileContent);
        assertTrue(isSameContent(fileAPath[1], fileBPath[1], encoding));
        deleteFile(new File(fileAPath[0]));
        deleteFile(new File(fileBPath[0]));
    }

    @Test
    public void testDeleteFile() {

    }
}
package git.snippets.utils;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static git.snippets.utils.FileUtil.generateEmptyFile;

public class GitUtilTest {

    @Test
    public void cloneRepo() throws GitAPIException, IOException, InterruptedException {
        String url = "https://gitee.com/GreyZeng/clonable";
        String[] fileA = generateEmptyFile();
        String[] fileB = generateEmptyFile();
        GitUtil.cloneRepo(url, fileA[0], true);
        GitUtil.cloneRepo(url, fileB[0], false);
        System.out.println("完成下载，请手动查看Git仓库是否正常下载下来，如果正常下载下来了，请删除下载的文件，不要提交到Git上");
        /*TimeUnit.SECONDS.sleep(60);
        FileUtil.deleteFile(new File(fileA[0]));
        FileUtil.deleteFile(new File(fileB[0]));*/
    }

    @Test
    public void testHistory() {
        List<String> history = GitUtil.history("C:\\git\\algorithm", "docs");

    }
}
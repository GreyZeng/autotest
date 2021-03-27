package git.snippets.utils;

import git.snippets.report.ReportData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 生成CSV
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/2
 * @since
 */
public class CSVUtil {
    /**
     * 将reportData写入csv中
     * csvLocation是csv的路径，如果不存在，需要新建
     *
     * @param reportData  需要导出的csv数据的表头和内容
     * @param csvLocation csv的路径
     * @return 如果成功则返回true，不成功则返回false
     */
    public static boolean exportToCSV(ReportData reportData, String csvLocation) {
        final File csvFile = new File(csvLocation);

        if (!csvFile.getParentFile().exists()) {
            if (!csvFile.getParentFile().mkdirs()) {
                return false;
            }
        }
        if (!csvFile.exists()) {
            try {
                if (!csvFile.createNewFile()) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        final String[] headers = reportData.headers();
        final List<List<String>> records = reportData.records();

        try (FileWriter out = new FileWriter(csvFile);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for (List<String> record : records) {
                printer.printRecord(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

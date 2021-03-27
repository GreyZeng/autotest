package git.autotest.report;

import java.util.List;

/**
 * @author <a href="mailto:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/4
 * @since
 */
public interface ReportData {
    /**
     * 用来返回csv数据的表头
     * @return 表头列表
     */
    String[] headers();

    /**
     * 返回csv具体数据
     * @return csv具体数据
     */
    List<List<String>> records();
}

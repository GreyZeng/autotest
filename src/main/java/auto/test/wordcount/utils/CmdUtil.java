package auto.test.wordcount.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/3/3
 * @since
 */
public class CmdUtil {
    private static final Logger log = LoggerFactory.getLogger(CmdUtil.class);
    private static final long TIMEOUT_FLAG = Integer.MAX_VALUE;
    private static final long EXECUTE_ERROR_FLAG = -3;

    /**
     * 执行CMD命令
     *
     * @param cmd
     * @throws IOException
     */
    public static long cmd(String cmd, int timeout) {
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            long start = System.currentTimeMillis();
            process = runtime.exec(cmd);
            if (!process.waitFor(timeout, TimeUnit.SECONDS)) {
                process.destroy();
                log.warn("time limited {}, over {}", cmd, timeout);
                return TIMEOUT_FLAG;
            }
            return System.currentTimeMillis() - start;
        } catch (InterruptedException | IOException e) {
            log.error("execute cmd error {}  {} ", cmd, e.getMessage());
            return EXECUTE_ERROR_FLAG;
        }
    }
}

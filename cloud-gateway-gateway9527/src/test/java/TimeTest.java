import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @Author cx
 * @Date 2020/11/4 13:02
 * @Version 1.0
 */

@Slf4j
public class TimeTest {

    @Test
    public void timeNow(){

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        log.info("***********"+zonedDateTime);
    }
}

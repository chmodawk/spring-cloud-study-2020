import java.time.ZonedDateTime;

public class T2 {

    public static void main(String[] args) {
        ZonedDateTime abj = ZonedDateTime.now(); // 默认时区
        System.out.println(abj);
        // 2021-06-28T19:44:44.695+08:00[Asia/Shanghai]
    }
}

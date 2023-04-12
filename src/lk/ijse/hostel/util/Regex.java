package lk.ijse.hostel.util;

import java.util.regex.Pattern;

public class Regex {
    public static Pattern name = Pattern.compile("^([A-Za-z\\s\\.]){3,25}$");
    public static Pattern address = Pattern.compile("^([0-9A-Za-z\\s\\.\\:\\/\\,]){3,50}$");
    public static Pattern contact = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");

}

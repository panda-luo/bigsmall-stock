package club.panda1024.stock.util;

import lombok.extern.slf4j.Slf4j;


/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Slf4j
public class ValidHelper {

    private ValidHelper() {}


    public static boolean validStockCode(String s) {
        return s.matches("^\\d{6}$");
    }

}

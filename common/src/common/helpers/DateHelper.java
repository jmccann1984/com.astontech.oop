package common.helpers;

/**
 * Created by Joshua.McCann on 6/29/2017.
 */
public class DateHelper extends CommonHelper {

    public static java.sql.Date utilDateToSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date date){
        return new java.sql.Date(date.getTime());
    }
}

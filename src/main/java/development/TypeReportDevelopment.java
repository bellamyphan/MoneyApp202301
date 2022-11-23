package development;

import objects.type.TypeReportHandler;
import tools.DateHandler;

import java.util.Date;

public class TypeReportDevelopment {
    public static void main(String[] args) {
        Date startDate = new DateHandler("2018-10-01").getDate();
        Date endDate = new DateHandler("2018-10-31").getDate();
        System.out.println(new TypeReportHandler().getTypeReportFilterByTime(startDate, endDate));
    }
}

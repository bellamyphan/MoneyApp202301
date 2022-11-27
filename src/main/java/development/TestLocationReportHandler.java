package development;

import objects.location.LocationReportHandler;
import objects.type.TypeReportHandler;

public class TestLocationReportHandler {
    public static void main(String[] args) {
        System.out.println(new LocationReportHandler().getLocationReportFilterUntilToday());
        System.out.println();

        System.out.println(new TypeReportHandler().getTypeReportFilterUntilToday());
        System.out.println();

        System.out.println(new LocationReportHandler().getLocationPerNameReportFilterUntilToday());
    }
}

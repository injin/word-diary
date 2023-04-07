package project.diary;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

public class DateTest {

    @Test
    public void dateTest() {

        int searchYear = 2023;
        int searchMonth = 4;
        YearMonth yearMonth = YearMonth.of(2023, 04);
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        System.out.println(start);
        System.out.println(end);

    }

}

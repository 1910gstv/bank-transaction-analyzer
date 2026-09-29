package test.java;

import main.java.BankStatementCSVParser;
import main.java.domain.BankStatementParser;
import main.java.domain.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.List;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "30-01-2017,-50,Tesco";
        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldFailOnInvalidDateFormat(){
        final String line = "2017-01-31,-100,Tesco";
        statementParser.parseFrom(line);
    }


    @Test
    public void shouldParseCorrectLines() throws Exception{
        final List<String> lines = List.of(
            "30-01-2017,-100,Tedesco",
            "30-01-2018,310,Salario",
            "26-02-2017,-70,Pagamento",
            "18-02-2017,200,Copinho"
        );

        final List<BankTransaction> result = statementParser.parseLinesFrom(lines);

        Assert.assertEquals(4, result.size());
    }
}

package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ExpenseListTest {

    public static final String LIST_OF_EXPENSES = "Here is the list of your expenses:";
    public static final String LIST_OF_EXPENSES2 = "    Category    |    Date    |   Name   |  Amount";


    @Test
    public void deleteExpense_existingItem_success() throws MintException {
        ExpenseList expenseList = new ExpenseList();
        String name = "Cheese burger";
        String date = "2021-12-23";
        String amount = "15.5";
        String catNum = "0";

        expenseList.expenseList.add(new Expense(name, date, amount, catNum));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        expenseList.deleteExpense(name, date, amount, catNum);
        String expectedOutput  = "I have deleted: Food | 2021-12-23 | Cheese burger | $15.50"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void deleteExpense_nonExistingItem_printErrorMessage() throws MintException {
        ExpenseList expenseList = new ExpenseList();
        Parser parser = new Parser();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        parser.executeCommand("delete n/Cheese d/2021-12-23 a/18 c/1", expenseList);
        String ExpectedOutput  = "Hmm.. That item is not in the list." + Ui.LINE_SEPARATOR;
        assertEquals(ExpectedOutput, outContent.toString());
    }

    @Test
    public void deleteExpense_wrongAmountFormat_printErrorMessage() throws MintException {
        ExpenseList expenseList = new ExpenseList();
        Parser parser = new Parser();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        parser.executeCommand("delete n/Cheese d/2021-12-23 a/15.5abc c/1", expenseList);
        String expectedOutput  = Parser.STRING_INCLUDE + "1. " + Parser.STRING_AMOUNT + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void deleteExpense_wrongDateFormat_printErrorMessage() throws MintException {
        ExpenseList expenseList = new ExpenseList();
        Parser parser = new Parser();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        parser.executeCommand("delete n/Cheese d/2021-12 a/15.5 c/1", expenseList);
        String expectedOutput  = Parser.STRING_INCLUDE + "1. " + Parser.STRING_DATE + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void viewExpense_twoExpenses_success() throws MintException {
        CategoryList.initialiseCategories();
        ExpenseList expenseList = new ExpenseList();
        expenseList.expenseList.add(new Expense("Cheese burger", "2021-12-23", "15.5"));
        expenseList.expenseList.add(new Expense("book", "2022-12-23", "9"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] emptyArray = {"view"};
        expenseList.viewExpense(emptyArray);
        String expectedOutput  = LIST_OF_EXPENSES + System.lineSeparator()
                + LIST_OF_EXPENSES2 + System.lineSeparator()
                + "     Others     | 2021-12-23 | Cheese burger | $15.50" + System.lineSeparator()
                + "     Others     | 2022-12-23 | book | $9.00" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void viewExpense_zeroExpenses_success() throws MintException {
        ExpenseList expenseList = new ExpenseList();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] emptyArray = {"view"};
        expenseList.viewExpense(emptyArray);
        String expectedOutput  = LIST_OF_EXPENSES + System.lineSeparator()
                + LIST_OF_EXPENSES2 + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
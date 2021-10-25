package seedu.duke.commands;

import seedu.duke.entries.Entry;
import seedu.duke.exception.MintException;
import seedu.duke.finances.NormalFinanceManager;
import seedu.duke.finances.RecurringFinanceManager;
import seedu.duke.utility.Ui;

import java.util.ArrayList;

public class EditRecurringCommand extends Command {
    private Entry query;
    ArrayList<String> tags;


    public EditRecurringCommand(ArrayList<String> tags, Entry query) {
        this.query = query;
        this.tags = tags;
    }

    @Override
    public void execute(NormalFinanceManager normalFinanceManager,
                        RecurringFinanceManager recurringFinanceManager, Ui ui) {
        try {
            recurringFinanceManager.editEntryByKeywords(tags, query);
        } catch (MintException e) {
            ui.printError(e);
        }
    }

}

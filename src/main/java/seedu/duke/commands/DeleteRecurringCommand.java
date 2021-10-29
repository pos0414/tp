package seedu.duke.commands;

import seedu.duke.budget.BudgetManager;
import seedu.duke.entries.Entry;
import seedu.duke.entries.RecurringEntry;
import seedu.duke.exception.MintException;
import seedu.duke.finances.NormalFinanceManager;
import seedu.duke.finances.RecurringFinanceManager;
import seedu.duke.storage.BudgetDataManager;
import seedu.duke.storage.DataManagerActions;
import seedu.duke.storage.NormalListDataManager;
import seedu.duke.storage.RecurringListDataManager;
import seedu.duke.utility.Ui;

import java.util.ArrayList;

public class DeleteRecurringCommand extends Command {
    private final Entry query;
    private final ArrayList<String> tags;
    private final boolean isDeleteAll;

    public DeleteRecurringCommand(ArrayList<String> tags, Entry query, boolean isDeleteAll) {
        this.query = query;
        this.tags = tags;
        this.isDeleteAll = isDeleteAll;
    }

    @Override
    public void execute(NormalFinanceManager normalFinanceManager,
                        RecurringFinanceManager recurringFinanceManager, BudgetManager budgetManager,
                        NormalListDataManager normalListDataManager, DataManagerActions dataManagerActions,
                        RecurringListDataManager recurringListDataManager, BudgetDataManager budgetDataManager, Ui ui) {
        try {
            if (isDeleteAll) {
                deleteAll(recurringFinanceManager, recurringListDataManager);
                return;
            }
            Entry deletedEntry = recurringFinanceManager.deleteEntryByKeywords(tags, query);
            String stringToDelete = RecurringFinanceManager.overWriteString((RecurringEntry) deletedEntry);
            recurringListDataManager.deleteLineInTextFile(stringToDelete);
            ui.printEntryDeleted(deletedEntry);
        } catch (MintException e) {
            ui.printError(e);
        }
    }

    public void deleteAll(RecurringFinanceManager recurringFinanceManager,
                          RecurringListDataManager recurringListDataManager) {
        if (Ui.isConfirmDeleteAll()) {
            recurringFinanceManager.deleteAll();
            recurringListDataManager.deleteAll();
            Ui.deleteAllConfirmation();
        } else {
            Ui.deleteAborted();
        }
    }
}

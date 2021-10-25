package seedu.duke.commands;

import seedu.duke.finances.NormalFinanceManager;
import seedu.duke.finances.RecurringFinanceManager;
import seedu.duke.utility.Ui;

public abstract class Command {
    public abstract void execute(NormalFinanceManager normalFinanceManager,
                                 RecurringFinanceManager recurringFinanceManager, Ui ui);

    public boolean isExit() {
        return false;
    }
}


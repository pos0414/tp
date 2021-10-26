package seedu.duke.entries;

import seedu.duke.utility.Ui;

import java.time.LocalDate; // import the LocalDate class

public class Entry {
    protected String name;
    protected LocalDate date;
    protected double amount;
    protected Type type;

    public Entry(Entry entry) {
        this.name = entry.getName();
        this.date = entry.getDate();
        this.amount = entry.getAmount();
        this.type = null;
    }

    public Entry(String name, LocalDate date, double amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getNameIndented() {
        double length = name.length();
        int leftIndent = (int) Math.floor((16 - length) / 2);
        int rightIndent = (int) Math.ceil((16 - length) / 2);
        if (leftIndent < 0) {
            leftIndent = 0;
        }
        if (rightIndent < 0) {
            rightIndent = 0;
        }
        return Ui.getIndent(leftIndent, rightIndent, name).toString();
    }
  
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getAmountIndented() {
        double length = Double.toString(getAmount()).length();
        int rightIndent = (int)(8 - length);
        if (rightIndent < 0) {
            rightIndent = 0;
        }
        return Ui.getIndent(0, rightIndent, String.format("%,.2f", getAmount())).toString();
    }

    public Type getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Enum getCategory() {
        return null;
    }

    public String getCategoryIndented() {
        double length = getCategory().toString().length();
        int leftIndent = (int) Math.floor((16 - length) / 2);
        int rightIndent = (int) Math.ceil((16 - length) / 2);
        if (leftIndent < 0) {
            leftIndent = 0;
        }
        if (rightIndent < 0) {
            rightIndent = 0;
        }
        return Ui.getIndent(leftIndent, rightIndent, getCategory().toString()).toString();
    }

    public String toStringIndented() {
        return null;
    }
}

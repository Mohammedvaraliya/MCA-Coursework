class InvalidDayException extends Exception {
    public InvalidDayException(String message) {
        super(message);
    }
}

class InvalidMonthException extends Exception {
    public InvalidMonthException(String message) {
        super(message);
    }
}

class DateHandling {
    private int day;
    private int month;
    private int year;

    public DateHandling() {
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }

    public DateHandling(int day, int month, int year) throws InvalidDayException, InvalidMonthException {

        if (month < 1 || month > 12) {
            throw new InvalidMonthException("Month is not valid. It must be between 1 and 12.");
        }

        if (day < 1 || day > getMaxDaysInMonth(month, year)) {
            throw new InvalidDayException("Day is not valid for the given month and year.");
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    private int getMaxDaysInMonth(int month, int year) {
        // Check for leap year for February
        if (month == 2) {
            return (isLeapYear(year)) ? 29 : 28;
        }

        // April, June, September, and November have 30 days
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        // All other months have 31 days
        return 31;
    }

    // Method to check if a year is a leap year
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void displayDate() {
        System.out.println("Date: " + day + "/" + month + "/" + year);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.out.println("Please provide day, month, and year as command-line arguments.");
                return;
            }

            int day = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            int year = Integer.parseInt(args[2]);

            DateHandling date = new DateHandling(day, month, year);
            date.displayDate();
            System.out.println("Valid date.");

        } catch (InvalidDayException | InvalidMonthException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter integers for day, month, and year.");
        }
    }
}
import java.util.*;
class BankAccount {
    String accountNumber;
    String username;
    double balance;

    BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return username + " – Balance: " + (long) balance;
    }

    String fullDetails() {
        return accountNumber + " | " + username + " | Balance: " + balance;
    }
}

public class Banking {
    static final Scanner SC = new Scanner(System.in);

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> transactionHistory = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();
    static int nextRequestAccNumber = 200;

    public static void main(String[] args) {
        task6PhysicalArray();

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1 – Enter Bank\n2 – Enter ATM\n3 – Admin Area\n4 – Exit");
            int choice = readInt("Choice: ", 1, 4);
            switch (choice) {
                case 1 -> bankMenu();
                case 2 -> atmMenu();
                case 3 -> adminMenu();
                case 4 -> {
                    System.out.println("Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    /** Task 6: physical array of 3 accounts, print, then load into logical LinkedList */
    static void task6PhysicalArray() {
        BankAccount[] bankArray = new BankAccount[3];
        bankArray[0] = new BankAccount("101", "Ali", 150000);
        bankArray[1] = new BankAccount("102", "Sara", 220000);
        bankArray[2] = new BankAccount("103", "Ivan", 50000);

        System.out.println("--- Task 6: Physical array BankAccount[3] ---");
        for (int i = 0; i < bankArray.length; i++) {
            System.out.println((i + 1) + ". " + bankArray[i].toString());
        }
        Collections.addAll(accounts, bankArray);
        System.out.println("(Loaded into LinkedList for banking operations.)\n");
    }

    static void bankMenu() {
        while (true) {
            System.out.println("\n[BANK MENU]");
            System.out.println("1. Request new account (queue)");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Add bill payment request (queue)");
            System.out.println("5. Undo last transaction (stack pop)");
            System.out.println("6. Display last transaction (stack peek)");
            System.out.println("7. Display all accounts");
            System.out.println("8. Search account by username");
            System.out.println("0. Back to main menu");
            int c = readInt("Choice: ", 0, 8);
            if (c == 0) return;
            switch (c) {
                case 1 -> submitAccountRequest();
                case 2 -> depositOrWithdraw(true);
                case 3 -> depositOrWithdraw(false);
                case 4 -> addBillRequest();
                case 5 -> undoLastTransaction();
                case 6 -> peekLastTransaction();
                case 7 -> displayAllAccounts();
                case 8 -> searchByUsername();
                default -> { /* unreachable due to readInt */ }
            }
        }
    }

    static void submitAccountRequest() {
        System.out.print("Enter name for new account: ");
        String name = SC.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        String num = String.valueOf(nextRequestAccNumber++);
        accountRequests.add(new BankAccount(num, name, 0));
        System.out.println("Account opening request submitted (pending admin).");
    }

    static void depositOrWithdraw(boolean deposit) {
        System.out.print("Enter username: ");
        String name = SC.nextLine().trim();
        BankAccount acc = findAccountByUsername(name);
        if (acc == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print(deposit ? "Deposit amount: " : "Withdraw amount: ");
        double amt = readDoublePositive();
        if (!deposit && acc.balance < amt) {
            System.out.println("Insufficient funds.");
            return;
        }
        if (deposit) {
            acc.balance += amt;
            transactionHistory.push("Deposit " + (long) amt + " to " + acc.username);
            System.out.println("New balance: " + (long) acc.balance);
        } else {
            acc.balance -= amt;
            transactionHistory.push("Withdraw " + (long) amt + " from " + acc.username);
            System.out.println("New balance: " + (long) acc.balance);
        }
    }

    static void addBillRequest() {
        System.out.print("Bill name (e.g. Electricity Bill): ");
        String bill = SC.nextLine().trim();
        if (bill.isEmpty()) {
            System.out.println("Bill name cannot be empty.");
            return;
        }
        billQueue.add(bill);
        System.out.println("Added: " + bill);
    }

    static void undoLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to undo.");
            return;
        }
        String removed = transactionHistory.pop();
        System.out.println("Undo → " + removed + " removed");
    }

    static void peekLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts.");
            return;
        }
        System.out.println("Accounts List:");
        int i = 1;
        for (BankAccount a : accounts) {
            System.out.println(i++ + ". " + a.toString());
        }
    }

    static void searchByUsername() {
        System.out.print("Username to search: ");
        String q = SC.nextLine().trim();
        BankAccount acc = findAccountByUsername(q);
        if (acc == null) {
            System.out.println("No account found for: " + q);
        } else {
            System.out.println("Found: " + acc.fullDetails());
        }
    }

    static void atmMenu() {
        System.out.print("Enter username: ");
        String name = SC.nextLine().trim();
        BankAccount acc = findAccountByUsername(name);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("1. Balance enquiry\n2. Withdraw");
        int choice = readInt("Choice: ", 1, 2);
        if (choice == 1) {
            System.out.println("Balance: " + (long) acc.balance);
        } else {
            System.out.print("Amount: ");
            double amt = readDoublePositive();
            if (acc.balance < amt) {
                System.out.println("Insufficient funds.");
                return;
            }
            acc.balance -= amt;
            transactionHistory.push("Withdraw " + (long) amt + " from " + acc.username);
            System.out.println("New balance: " + (long) acc.balance);
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n[ADMIN AREA]");
            System.out.println("1. Process next account opening request");
            System.out.println("2. Process next bill payment");
            System.out.println("3. View pending account requests");
            System.out.println("4. View bill payment queue");
            System.out.println("5. View all accounts");
            System.out.println("0. Back to main menu");
            int c = readInt("Choice: ", 0, 5);
            if (c == 0) return;
            switch (c) {
                case 1 -> processNextAccountRequest();
                case 2 -> processNextBill();
                case 3 -> viewPendingAccountRequests();
                case 4 -> viewBillQueue();
                case 5 -> displayAllAccounts();
                default -> { }
            }
        }
    }

    static void processNextAccountRequest() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending account requests.");
            return;
        }
        BankAccount newAcc = accountRequests.poll();
        accounts.add(newAcc);
        System.out.println("Processed: account opened for " + newAcc.username + " (" + newAcc.accountNumber + ")");
    }

    static void processNextBill() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        String bill = billQueue.poll();
        transactionHistory.push("Bill payment processed: " + bill);
        System.out.println("Processing: " + bill);
        if (!billQueue.isEmpty()) {
            System.out.println("Remaining: " + billQueue.peek());
        }
    }

    static void viewPendingAccountRequests() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        System.out.println("Pending account requests:");
        int i = 1;
        for (BankAccount r : accountRequests) {
            System.out.println(i++ + ". " + r.username + " (acc# " + r.accountNumber + ")");
        }
    }

    static void viewBillQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        System.out.println("Bill payment queue (FIFO order):");
        int i = 1;
        for (String b : billQueue) {
            System.out.println(i++ + ". " + b);
        }
    }

    static BankAccount findAccountByUsername(String name) {
        for (BankAccount a : accounts) {
            if (a.username.equalsIgnoreCase(name)) return a;
        }
        return null;
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (SC.hasNextInt()) {
                int v = SC.nextInt();
                SC.nextLine();
                if (v >= min && v <= max) return v;
            } else {
                SC.nextLine();
            }
            System.out.println("Enter a number between " + min + " and " + max + ".");
        }
    }

    static double readDoublePositive() {
        while (true) {
            if (SC.hasNextDouble()) {
                double v = SC.nextDouble();
                SC.nextLine();
                if (v > 0) return v;
            } else {
                SC.nextLine();
            }
            System.out.println("Enter a positive number.");
        }
    }
}

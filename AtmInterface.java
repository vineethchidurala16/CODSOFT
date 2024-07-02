import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }
	

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            if (amount <= 0) {
                System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
            return false;
        }
    }

    public double checkBalance() {
        return this.balance;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void runATM() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayOptions();
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount;
                    try {
                        withdrawalAmount = Double.parseDouble(scanner.nextLine());
                        if (userAccount.withdraw(withdrawalAmount)) {
                            System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.checkBalance());
                        } else {
                            System.out.println("Withdrawal failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "2":
                    System.out.print("Enter deposit amount: ");
                    double depositAmount;
                    try {
                        depositAmount = Double.parseDouble(scanner.nextLine());
                        if (userAccount.deposit(depositAmount)) {
                            System.out.println("Deposit successful. Updated balance: $" + userAccount.checkBalance());
                        } else {
                            System.out.println("Deposit failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "3":
                    System.out.println("Current balance: $" + userAccount.checkBalance());
                    break;

                case "4":
                    System.out.println("Exiting ATM. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000);  // Initial balance $1000
        ATM atmMachine = new ATM(userAccount);
        atmMachine.runATM();
    }
}
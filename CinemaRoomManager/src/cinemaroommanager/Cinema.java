package cinemaroommanager;

import java.util.Scanner;


public class Cinema {


    Scanner scanner = new Scanner(System.in);

    static int rows = 0;
    static int seats = 0;
    char[][] array;
    boolean open = false;
    int purchasedTickets = 0;
    int totalSeats = 0;
    int currentIncome = 0;
    int totalIncome = 0;
    double purchasedPercentage = 0.0;

    public void createRoom() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        totalSeats = rows * seats;

        int frontRows = (rows / 2) * seats;
        int backRows = totalSeats - frontRows;
        totalIncome = frontRows * 10 + backRows * 8;

        array = new char[rows + 1][seats + 1];
        array[0][0] = ' ';

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < seats + 1; j++) {
                array[i][j] = 'S';
            }
        }

        char count1 = '1';
        for (int i = 1; i < rows + 1; i++) {
            array[i][0] = count1;
            count1++;
        }

        char count2 = '1';
        for (int j = 1; j < seats + 1; j++) {
            array[0][j] = count2;
            count2++;
        }
    }

    public boolean choose() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice = scanner.nextInt();
        System.out.println();

        switch (choice) {
            case 1:
                showTheSeats();
                choose();
                break;
            case 2:
                buyATicket();
                choose();
                break;
            case 3:
                statistics();
                choose();
            case 0:
                open = false;
                break;
            default:
        }
        return open;
    }

    public void showTheSeats() {
        System.out.println("Cinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void buyATicket() {

        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        if (row > rows || seat > seats || row < 1 || seat < 1) {
            System.out.println("Wrong Input!");
            System.out.println();
            buyATicket();
        } else if (array[row][seat] == 'B') {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            buyATicket();
        } else {
            array[row][seat] = 'B';
            purchasedTickets++;

            if (totalSeats > 60) {
                if (row > rows / 2) {
                    System.out.println("Ticket price: $8");
                    currentIncome += 8;
                } else {
                    System.out.println("Ticket price: $10");
                    currentIncome += 10;
                }
            } else {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            }
        }
        System.out.println();
    }

    public void statistics() {
        if (purchasedTickets != 0) {
            double total = totalSeats;
            double purchased = purchasedTickets;
            purchasedPercentage = (purchased / total) * 100;
        }

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f", purchasedPercentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {

        Cinema cinema = new Cinema();
        cinema.createRoom();
        System.out.println();

        while (cinema.choose()) {
            cinema.choose();
        }

    }
}

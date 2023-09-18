import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Theatre {
    public static boolean Loop1 = true;     //creating this boolean public, so I can create my own method
    public static boolean Loop2 = true;
    public static int row_num = 0;
    public static int cancel_row_num = 0;
    public static int ticketPrice = 0;
    public static int totalPrice = 0;
    public static ArrayList<Ticket> ticketsArray = new ArrayList<>();    //creating objects array
    public static String[] row1 = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};     //creating 3 arrays to store seat status
    public static String[] row2 = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static String[] row3 = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static void main(String[] args) {

        System.out.println("Welcome to the New Theatre");
        while (true) {
            try{
                Scanner input = new Scanner(System.in);     //printing main menu
                System.out.print("""
                        -------------------------------------------------
                        Please select an option:
                        1) Buy a ticket
                        2) Print seating area
                        3) Cancel ticket
                        4) List available seats
                        5) Save to file
                        6) Load from file
                        7) Print ticket information and total price
                        8) Sort tickets by price
                         0) Quit
                        -------------------------------------------------
                        Enter option:\s""");
                int option = input.nextInt();       //asks for an option
                switch (option) {
                    case 1 -> buy_ticket();     //calling methods according to the asked option
                    case 2 -> print_seating_area();
                    case 3 -> cancel_ticket();
                    case 4 -> show_available();
                    case 5 -> save();
                    case 6 -> load();
                    case 7 -> show_tickets_info();
                    case 8 -> sorting_tickets();
                    case 0 -> {                         //terminates the program
                        System.out.println("Thank you for using our application. Have a nice day!");
                        return;
                    }
                    default -> System.out.println("Option not valid.");
                }
            }
            catch (Exception e) {
                System.out.println("Please enter a number! \n");                    //if user enters a string
            }
        }
    }
    public static void get_seat_info(String[] array1, int ticket_price) {      //creating a method to ask for the seat to book after knowing the particular row

        while (true) {
            try {
                System.out.print("Enter the seat number(1-"+array1.length+"): ");
                Scanner input = new Scanner(System.in);
                int seat_num = input.nextInt();
                if ((1 <= seat_num) && (seat_num <= array1.length)) {
                    if (array1[seat_num - 1].equals("1")) {       //checking whether the particular seat is already booked
                        System.out.println("Seat already booked. Please try a different seat \n");
                        break;
                    } else {
                        array1[seat_num - 1] = "1";                 //updating the array
                        System.out.println(Arrays.toString(array1));
                        ticketPrice = ticket_price;
                        totalPrice = totalPrice + ticket_price;
                        personDetails(row_num, seat_num);       //getting input to the object person
                    }
                    Loop1 = false;
                    break;
                }
                else {
                    System.out.println("Please enter a number between 1-"+array1.length+" \n");
                }
            }
            catch (Exception e) {
                System.out.println("Please enter a number!\n");
            }
        }
    }
    public static void buy_ticket() {

        while (true) {
            try {
                Scanner input1 = new Scanner(System.in);
                System.out.print("How many seats are you planning to book: ");
                int bookings = input1.nextInt();
                for (int x = 0; x < bookings; x++) {
                    Loop1 = true;
                    while (Loop1) {
                        try {
                            Scanner input2 = new Scanner(System.in);
                            System.out.print("Enter the row number: ");
                            row_num = input2.nextInt();
                            switch (row_num) {
                                case 1:
                                    get_seat_info(row1, 20);
                                    break;
                                case 2:
                                    get_seat_info(row2, 10);
                                    break;
                                case 3:
                                    get_seat_info(row3, 5);
                                    break;
                                default:
                                    System.out.println("Please enter a number between 1-3 \n");
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Please enter a number!\n");
                        }
                    }
                }
                return;
            }
            catch (Exception e) {
                System.out.println("Please enter a number!\n");
            }
        }
    }
    public static void print_row(String[] array, int space_num) {       //creating new method to print each row under option 2

        for (int x = 0; x < array.length; x++) {
            if (array[x].equals("0")) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if(x == space_num){
                System.out.print(" ");
            }
        }
    }
    public static void print_seating_area() {

        System.out.print("\n     ***********\n     *  STAGE  *\n     ***********\n");       //printing the seating plan
        System.out.println();
        System.out.print("    ");
        print_row(row1, 5);
        System.out.println();
        System.out.print("  ");
        print_row(row2, 7);
        System.out.println();
        print_row(row3, 9);
        System.out.println();
    }
    public static void cancel_seat_info(String[] array2) {      //creating a method to ask for the seat to cancel after knowing the particular row

        while (true) {
            try {
                System.out.print("Enter the seat number(1-"+array2.length+"): ");
                Scanner input4 = new Scanner(System.in);
                int cancel_seat_num = input4.nextInt();
                if ((1 <= cancel_seat_num) && (cancel_seat_num <= array2.length)) {
                    if (array2[cancel_seat_num - 1].equals("0")) {      //checking whether the particular seat is already booked
                        System.out.println("Seat is not booked yet. Please check again");
                        System.out.println("(Enter 0 if need to go to the MAIN MENU)\n");
                        break;
                    } else {
                        System.out.println("Ticket cancelled successfully");
                        array2[cancel_seat_num - 1] = "0";      //updating the array
                        System.out.println(Arrays.toString(array2));
                        System.out.println(cancel_row_num);
                        removeTicket(cancel_row_num, cancel_seat_num);      //removing ticket info from the tickets array
                    }
                    Loop2 = false;
                    break;
                }
                else {
                    System.out.println("Please enter a number between 1-"+array2.length+"\n");
                }
            }
            catch (Exception e) {
                System.out.println("Please enter a number!\n");
            }

        }
    }
    public static void cancel_ticket() {
        Loop2 = true;
        while (Loop2) {
            try {
                Scanner input3 = new Scanner(System.in);
                System.out.print("Enter the row number: ");
                cancel_row_num = input3.nextInt();
                switch (cancel_row_num) {
                    case 1:
                        cancel_seat_info(row1);
                        totalPrice = totalPrice - 20;
                        break;
                    case 2:
                        cancel_seat_info(row2);
                        totalPrice = totalPrice - 10;
                        break;
                    case 3:
                        cancel_seat_info(row3);
                        totalPrice = totalPrice - 5;
                        break;
                    case 0:     //returns to main menu in case there's no seats to cancel
                        return;
                    default:
                        System.out.println("Please enter a number between 1-3 \n");
                }
            }
            catch (Exception e) {
                System.out.println("Please enter a number!\n");
            }
        }
    }
    public static void seats_available(int row_n, String[] array3) {        //creating a method to option 4
        System.out.print("Seats available in row "+row_n+": ");
        for (int num = 0; num < array3.length; num++) {
            if (array3[num].equals("0")) {
                System.out.print(num + 1 + ", ");
            }
        }
        System.out.println();
    }
    public static void show_available() {       //showing available seats

        seats_available(1, row1);
        seats_available(2, row2);
        seats_available(3, row3);
    }
    public static void save () {        //saves seating status to a text file

        try {
            FileWriter fileWrite = new FileWriter("Save.txt");
            for (String s : row1) fileWrite.write(s + " ");
            fileWrite.write("\n");
            for (String s : row2) fileWrite.write(s + " ");
            fileWrite.write("\n");
            for (String s : row3) fileWrite.write(s + " ");
            fileWrite.close();
        }
        catch (IOException ex ) {
            System.out.println("error");
        }
        System.out.println("Saving file...........\nFile successfully saved\n");
    }
    public static void load () {        //loads previously saved data from the text file

        try {
            System.out.println("loading file........");
            File file = new File("Save.txt");
            Scanner file_reader = new Scanner(file);
            String info;
            int lineCount = 1;
            while (file_reader.hasNextLine()) {
                info = file_reader.nextLine();
                String[] info_without_space = info.split(" ");
                if (lineCount == 1) {
                    for (int i = 0; i < row1.length; i++) {
                        row1[i] = info_without_space[i];
                    }
                } else if (lineCount == 2) {
                    for (int i = 0; i < row2.length; i++) {
                        row2[i] = info_without_space[i];
                    }
                } else if (lineCount == 3) {
                    for (int i = 0; i < row3.length; i++) {
                        row3[i] = info_without_space[i];
                    }
                }
                lineCount++;
            }
            file_reader.close();
            System.out.println("\nFile loaded successfully.");
        }
        catch (IOException ex) {
            System.out.println("Error");
        }
    }
    public static void personDetails(int row, int seat) {       //creating a method to get person's information

        String name;
        while (true) {
            Scanner input1 = new Scanner(System.in);
            System.out.print("\nEnter your name: ");
            name = input1.next();
            boolean result1 = name.matches("[a-zA-Z]+");
            if (result1) {
                break;
            } else {
                System.out.println("Enter only letters, try again!");
            }
        }

        String surname;
        while (true) {
            Scanner input2 = new Scanner(System.in);
            System.out.print("Enter your surname: ");
            surname = input2.next();
            boolean result2 = surname.matches("[a-zA-Z]+");
            if (result2) {
                break;
            } else {
                System.out.println("Enter only letters, try again!\n");
            }
        }
        String email;
        while (true) {
            Scanner input3 = new Scanner(System.in);
            System.out.print("Enter your email: ");
            email = input3.next();
            boolean result3 = email.endsWith("@gmail.com");
            if (result3) {
                break;
            } else {
                System.out.println("Please enter a valid Gmail address. (ex:- someone@gmail.com)\n");        //checking email
            }
        }


        System.out.println("\nPrice: " + ticketPrice + "$");
        System.out.println("Ticket booked successfully.\n");
        Person personInfo = new Person(name, surname, email);
        Ticket ticketInfo = new Ticket(row, seat, ticketPrice, personInfo);
        ticketsArray.add(ticketInfo);
    }
    public static void  removeTicket(int row, int seat) {       //removes the ticket from the array list when cancelling a ticket

        int ticketNum = 1;
        for (int i = 0; i < ticketsArray.size(); i++) {
            if (ticketsArray.get(i).getRow() == row && ticketsArray.get(i).getSeat() == (seat)) {
                ticketNum = i;
            }
        }
        ticketsArray.remove(ticketNum);
    }
    public static void show_tickets_info() {        //shows ticket info

        if (ticketsArray.size() == 0) {
            System.out.println("No seats have been booked yet!\n");
        }
        for (Ticket ticket: ticketsArray) {
            ticket.print();
        }
        System.out.println("Total price: "+totalPrice+"$");
    }
    public static void sorting_tickets() {      //sorting tickets using selection sort method

        int i = ticketsArray.size();
        Ticket temp_larger_price;
        for (int n = 0; n < i; n++) {
            for (int j = 1; j < (i); j++) {
                if (ticketsArray.get(j-1).getPrice() > ticketsArray.get(j).getPrice()) {
                    temp_larger_price = ticketsArray.get(j-1);
                    ticketsArray.set(j-1, ticketsArray.get(j));
                    ticketsArray.set(j, temp_larger_price);
                }
            }
        }
        System.out.println("Tickets sorting complete.\n");
        show_tickets_info();
    }
}
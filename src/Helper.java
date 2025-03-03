import model.HourlySalaryEmployee;
import model.SalariedEmployee;
import model.StaffMember;

import model.Volunteer;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Helper {
    static Scanner scanner = new Scanner(System.in);
    private static int status = 0;
    private static int choice;

    static Table table;
    static CellStyle cellStyle;
    
    public static void printSystemInfo(){
        cellStyle = new CellStyle(CellStyle.HorizontalAlign.LEFT);
        table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.SURROUND_HEADER_AND_COLUMNS);

        // Table column's width
        table.setColumnWidth(0, 50, 80);

        // Table header
        table.addCell("Staff management system".toUpperCase(), new CellStyle(CellStyle.HorizontalAlign.CENTER));

        // Table body
        table.addCell(" ".repeat(6) + "1. Insert Employee", cellStyle);
        table.addCell(" ".repeat(6) + "2. Update Employee", cellStyle);
        table.addCell(" ".repeat(6) + "3. Display Employee", cellStyle);
        table.addCell(" ".repeat(6) + "4. Remove Employee", cellStyle);
        table.addCell(" ".repeat(6) + "5. Exit", cellStyle);

        System.out.println(table.render());
        System.out.println("-".repeat(52));
    }

    public static void printEmployeeType(){
        table = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        table.addCell(" ".repeat(3) + "1. Volunteer" + " ".repeat(3), cellStyle);
        table.addCell(" ".repeat(3) + "2. Salaried Employee" + " ".repeat(3), cellStyle);
        table.addCell(" ".repeat(3) + "3. Hourly Employee" + " ".repeat(3), cellStyle);
        table.addCell(" ".repeat(3) + "4. Back" + " ".repeat(3), cellStyle);

        System.out.println(table.render());
    }

    public static <T extends StaffMember> void printEmployeeTypeInfo(T member){
       cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
       if(member instanceof Volunteer){
           table = new Table(6,BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

           // Table header
           table.addCell("Type", cellStyle);
           table.addCell("ID", cellStyle);
           table.addCell("Name", cellStyle);
           table.addCell("Address", cellStyle);
           table.addCell("Salary", cellStyle);
           table.addCell("Pay", cellStyle);

           // Table body
           table.addCell(" ".repeat(3) + "Volunteer" + " ".repeat(3));
           table.addCell(" ".repeat(3) + member.getId() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.getName() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.getAddress() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + ((Volunteer) member).getSalary() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.pay() + " ".repeat(3), cellStyle);
       } else if (member instanceof SalariedEmployee){
           table = new Table(7,BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

           // Table header
           table.addCell("Type", cellStyle);
           table.addCell("ID", cellStyle);
           table.addCell("Name", cellStyle);
           table.addCell("Address", cellStyle);
           table.addCell("Salary", cellStyle);
           table.addCell("Bonus", cellStyle);
           table.addCell("Pay", cellStyle);

           // Table body
           table.addCell(" ".repeat(3) + "Salaried Employee" + " ".repeat(3));
           table.addCell(" ".repeat(3) + member.getId() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.getName() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.getAddress() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + ((SalariedEmployee) member).getSalary() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + ((SalariedEmployee) member).getBonus() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.pay() + " ".repeat(3), cellStyle);
       } else if (member instanceof HourlySalaryEmployee){
           table = new Table(7,BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

           // Table header
           table.addCell("Type", cellStyle);
           table.addCell("ID", cellStyle);
           table.addCell("Name", cellStyle);
           table.addCell("Address", cellStyle);
           table.addCell("Hour", cellStyle);
           table.addCell("Rate", cellStyle);
           table.addCell("Pay", cellStyle);

           // Table body
           table.addCell(" ".repeat(3) + "Hourly Salary Employee" + " ".repeat(3));
           table.addCell(" ".repeat(3) + member.getId() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.getName() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.getAddress() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + ((HourlySalaryEmployee) member).getHourWorked() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + ((HourlySalaryEmployee) member).getRate() + " ".repeat(3), cellStyle);
           table.addCell(" ".repeat(3) + member.pay() + " ".repeat(3), cellStyle);
       }
        System.out.println(table.render());
    }

    public static void printMessage(String message, int flag){
        if (flag == 1)
            System.out.println( "✅ " + Color.GREEN.getCode() + message + Color.RESET.getCode());
        else if (flag == 0)
            System.out.println( "❌ " + Color.RED.getCode() + message + Color.RESET.getCode());
        else if (flag == 2)
            System.out.println( "⚠️ " + Color.YELLOW.getCode() + message + Color.RESET.getCode());
        else if (flag == 99)
            System.out.println("🥰 " + Color.PURPLE.getCode() + message + Color.RESET.getCode() + " 🚀");
    }

    public static int validateChoice(int choice, String label, boolean zero){
        boolean validatedChoice;
        int checkedChoice = 0;
        do {
            System.out.print(label);
            String input = scanner.nextLine();
            String regex = "^[0-9]+$";
            validatedChoice = Pattern.matches(regex, input);
            if(validatedChoice) {
                checkedChoice = Integer.parseInt(input);
                if(zero){
                    if(checkedChoice > choice) {
                        printMessage("Please input choice that starts from 0 to " + choice + "!", 0);
                        validatedChoice = false;
                    }
                }else{
                    if(checkedChoice > choice || checkedChoice == 0) {
                        printMessage("Please input choice that starts from 1 to " + choice + "!", 0);
                        validatedChoice = false;
                    }
                }
            }
        }while(!validatedChoice);
        return checkedChoice;
    }

    public static <T extends Number> T validateNumberInput(String label, Class<T> type, String keyword){
        String input;
        String regex = "^\\d+(\\.\\d+)?$";

        do {
            System.out.print(label);
            input = scanner.nextLine();

            if (input.matches(regex)){
                try {
                    if (type == Integer.class){
                        int value = Integer.parseInt(input);
                        if (value > 0)
                            return type.cast(value);
                        else
                            printMessage(keyword + " must be greater than 0. Please try again.", 0);
                    } else if (type == Double.class) {
                        double value = Double.parseDouble(input);
                        if (value > 0)
                            return type.cast(value);
                        else
                            printMessage(keyword + " must be greater than 0. Please try again.", 0);
                    }
                } catch (NumberFormatException e){
                    printMessage("Invalid number format. Please try again.", 0);
                }
            } else
                printMessage("Invalid input. Enter a valid number.", 0);
        } while(true);
    }

    public static String validateString(String label, String type){
        String input;
        String regex = "";
        String message = "Please enter a valid " + type + "!";
        boolean validatedInput;
        do {
            System.out.print(label);
            input = scanner.nextLine();
            if(type.equalsIgnoreCase("address")){
                regex = "^[0-9a-zA-Z\\s,#.-]{2,100}$";
            } else if (type.equalsIgnoreCase("name")){
                regex = "^[a-zA-Z ]+$";
            }
            validatedInput = Pattern.matches(regex, input);
            if(!validatedInput)
                printMessage(message, 0);
        } while(!validatedInput);

        return input;
    }

    public static void displayEmployeeInfo(List<StaffMember> staffMemberList){
        int pageSize = 3;  // Number of employees to display per page
        int totalPages = (int) Math.ceil((double) staffMemberList.size() / pageSize);
        int currentPage = 1;  // Start at page 1

        do {
            System.out.println("\n" + "=".repeat(10) + "* Display Employee *" + "=".repeat(10));
            cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
            table = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

            // Table columns' width
            // Table header
            table.addCell("Type", cellStyle);
            table.addCell("ID", cellStyle);
            table.addCell("Name", cellStyle);
            table.addCell("Address", cellStyle);
            table.addCell("Salary", cellStyle);
            table.addCell("Bonus", cellStyle);
            table.addCell("Hour", cellStyle);
            table.addCell("Rate", cellStyle);
            table.addCell("Pay", cellStyle);

            // Calculate the starting index and ending index for the current page
            int startIdx = (currentPage - 1) * pageSize;
            int endIdx = Math.min(startIdx + pageSize, staffMemberList.size());

            // Table body: Add the rows for the current page
            for (int i = startIdx; i < endIdx; i++) {
                StaffMember staffMember = staffMemberList.get(i);
                if (staffMember instanceof Volunteer) {
                    table.addCell(" ".repeat(3) + "Volunteer" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getId() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getName() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getAddress() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", ((Volunteer) staffMember).getSalary()) + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", staffMember.pay()) + " ".repeat(3), cellStyle);
                } else if (staffMember instanceof SalariedEmployee) {
                    table.addCell(" ".repeat(3) + "Salaried Employee" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getId() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getName() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getAddress() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", ((SalariedEmployee) staffMember).getSalary()) + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", ((SalariedEmployee) staffMember).getBonus()) + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", staffMember.pay()) + " ".repeat(3), cellStyle);
                } else if (staffMember instanceof HourlySalaryEmployee) {
                    table.addCell(" ".repeat(3) + "Hourly Employee" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getId() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getName() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + staffMember.getAddress() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "---" + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + ((HourlySalaryEmployee) staffMember).getHourWorked() + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", ((HourlySalaryEmployee) staffMember).getRate()) + " ".repeat(3), cellStyle);
                    table.addCell(" ".repeat(3) + "$" + String.format("%.2f", staffMember.pay()) + " ".repeat(3), cellStyle);
                }
            }

            // Render table
            System.out.println(table.render());

            // Display pagination options
            System.out.println("Page: " + currentPage + " / " + totalPages);
            System.out.print("1. First Page" + " ".repeat(3));
            System.out.print("2. Next Page" + " ".repeat(3));
            System.out.print("3. Previous Page" + " ".repeat(3));
            System.out.print("4. Last Page" + " ".repeat(3));
            System.out.print("5. Exit" + "\n");

            // Get user choice for pagination
            status = validateChoice(5, "=> Choose : ", false);

            // Handle pagination logic
            switch (status) {
                case 1:  // First Page
                    currentPage = 1;
                    break;
                case 2:  // Next Page
                    if (currentPage < totalPages) currentPage++;
                    break;
                case 3:  // Previous Page
                    if (currentPage > 1) currentPage--;
                    break;
                case 4:  // Last Page
                    currentPage = totalPages;
                    break;
                case 5:  // Exit
                    break;
            }
        } while (status != 5);
    }

    public static int insertEmployeeOption(List<StaffMember> staffMemberList){
        System.out.println("\n" + "=".repeat(10) + "* Insert Employee *" + "=".repeat(10));
        System.out.println("Choose Type: ");
        printEmployeeType();
        int type;
        do {
            type = validateChoice(4, "-> Enter Type Number: ", false);
            switch (type){
                case 1 -> {
                    StaffMember volunteer = insertEmployeeType(Volunteer.class);
                    staffMemberList.add(volunteer);
                    return 1;
                }
                case 2 -> {
                    StaffMember salariedEmployee = insertEmployeeType(SalariedEmployee.class);
                    staffMemberList.add(salariedEmployee);
                    return 1;
                }
                case 3 -> {
                    StaffMember hourlyEmployee = insertEmployeeType(HourlySalaryEmployee.class);
                    staffMemberList.add(hourlyEmployee);
                    return 1;
                }
                case 4 -> {
                    return - 1;
                }
            }
        } while(type != 4);
        return status;
    }

    public static <T extends StaffMember> StaffMember insertEmployeeType(Class<T> member){
        System.out.println("=> ID : " + (StaffMember.getInstanceCount() + 1));
        String name = validateString("=> Enter Name : ", "name");
        String address = validateString("=> Enter Address : ", "address");

        if (member == Volunteer.class) {
            double salary = validateNumberInput("=> Enter Salary : ", Double.class, "Salary");
            printMessage("You have added " + name + " of type Volunteer successfully", 1);
            return new Volunteer(StaffMember.getInstanceCount() + 1, name, address, salary);
        } else if (member == HourlySalaryEmployee.class) {
            int hoursWorked = validateNumberInput("=> Enter Worked Hour : ", Integer.class, "Worked hour");
            double rate = validateNumberInput("=> Enter Rate : ", Double.class, "Rate");
            printMessage("You have added " + name + " of type Hourly Salary Employee successfully", 1);
            return new HourlySalaryEmployee(StaffMember.getInstanceCount() + 1, name, address, hoursWorked, rate);
        } else if (member == SalariedEmployee.class) {
            double salary = validateNumberInput("=> Enter Salary : ", Double.class, "Salary");
            double bonus = validateNumberInput("=> Enter Bonus : ", Double.class, "Bonus");
            printMessage("You have added " + name + " of type Salaried Employee successfully", 1);
            return new SalariedEmployee(StaffMember.getInstanceCount() + 1, name, address, salary, bonus);
        }

        return null;
    }

    public static int updateEmployee(List<StaffMember> staffMemberList){
        System.out.println("\n" + "=".repeat(10) + "* Update Employee *" + "=".repeat(10));
        int id = validateNumberInput("=> Enter or Search ID to update : ", Integer.class, "ID");
        Optional<StaffMember> searchMember = searchEmployeeById(id, staffMemberList);
        if (searchMember.isEmpty()){
            printMessage("Employee with ID : " + id + " is not found!", 0);
        } else {
            do {
                searchMember.ifPresent(member -> {
                    // Display employee details
                    printEmployeeTypeInfo(member);
                    System.out.println();
                    System.out.println("Choose one column to update : ");
                    System.out.print("1. Name" + " ".repeat(3));
                    System.out.print("2. Address" + " ".repeat(3));
                    String className = String.valueOf(member.getClass());
                    if (className.contains("Volunteer")) {
                        System.out.print("3. Salary" + " ".repeat(3));
                        System.out.print("0. Cancel");
                        System.out.println();
                        choice = validateChoice(3, "=> Select Column Number: ", true);
                    } else if (className.contains("SalariedEmployee")) {
                        System.out.print("3. Salary" + " ".repeat(3));
                        System.out.print("4. Bonus" + " ".repeat(3));
                        System.out.print("0. Cancel");
                        System.out.println();
                        choice = validateChoice(4, "=> Select Column Number: ", true);
                    } else if (className.contains("HourlySalaryEmployee")) {
                        System.out.print("3. Hour" + " ".repeat(3));
                        System.out.print("4. Rate" + " ".repeat(3));
                        System.out.print("0. Cancel");
                        System.out.println();
                        choice = validateChoice(4, "=> Select Column Number: ", true);
                    }
                    switch (choice) {
                        case 1 -> {
                            String name = validateString("=> Change Name To : ", "name");
                            member.setName(name);
                            printMessage("* Name has updated successfully!", 1);
                        }
                        case 2 -> {
                            String address = validateString("=> Change Address To : ", "address");
                            member.setAddress(address);
                            printMessage("* Address has updated successfully!", 1);
                        }
                        case 3 -> {
                            if (className.contains("Volunteer")) {
                                double salary = validateNumberInput("=> Change Salary To : ", Double.class, "Salary");
                                ((Volunteer) member).setSalary(salary);
                                printMessage("* Salary has updated successfully!", 1);
                            } else if (className.contains("SalariedEmployee")) {
                                double salary = validateNumberInput("=> Change Salary To : ", Double.class, "Salary");
                                ((SalariedEmployee) member).setSalary(salary);
                                printMessage("* Salary has updated successfully!", 1);
                            } else if (className.contains("HourlySalaryEmployee")) {
                                int hour = validateNumberInput("=> Change Hour To : ", Integer.class, "Hour");
                                ((HourlySalaryEmployee) member).setHourWorked(hour);
                                printMessage("* Hour has updated successfully!", 1);
                            }
                        }
                        case 4 -> {
                            if (className.contains("SalariedEmployee")) {
                                double bonus = validateNumberInput("=> Change Bonus To : ", Double.class, "Bonus");
                                ((SalariedEmployee) member).setBonus(bonus);
                                printMessage("* Bonus has updated successfully!", 1);
                            } else if (className.contains("HourlySalaryEmployee")) {
                                double rate = validateNumberInput("=> Change Rate To : ", Double.class, "Rate");
                                ((HourlySalaryEmployee) member).setRate(rate);
                                printMessage("* Rate has updated successfully!", 1);
                            } else {
                                printMessage("Invalid Input!", 0);
                            }
                        }
                    }
                });
            } while (choice != 0);
        }
        return status;
    }

    public static Optional searchEmployeeById(int id, List<StaffMember> staffMemberList){
        Optional<StaffMember> employeeOpt = staffMemberList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();
        return employeeOpt;
    }

    public static int removeEmployee(List<StaffMember> staffMemberList){
        System.out.println("\n" + "=".repeat(10) + "* Remove Employee *" + "=".repeat(10));
        int id = validateNumberInput("=> Enter ID to remove : ", Integer.class, "ID");
        Optional<StaffMember> searchMember = searchEmployeeById(id, staffMemberList);
        if (searchMember.isEmpty()){
            printMessage("Fail to Remove because Employee with ID : " + id + " is not found!", 0);
        } else {
            do {
                searchMember.ifPresent(member -> {
                    // Display employee details
                    printEmployeeTypeInfo(member);
                    System.out.println("\nAre you sure you want to remove this employee?");
                    System.out.println("1. Yes, remove");
                    System.out.println("0. Cancel");

                    // Get the user's choice
                    int choice = validateChoice(1, "=> Select option: ", true);

                    switch (choice) {
                        case 1 -> {
                            // Remove the employee from the list
                            staffMemberList.remove(member);
                            printMessage("Employee with ID " + id + " has been successfully removed.", 1);
                        }
                    }
                });
            } while (choice != 0);
        }
        return status;
    }
}
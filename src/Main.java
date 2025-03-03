import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<StaffMember> staffMemberList = new ArrayList<>(Arrays.asList(
                new Volunteer(1, "Tina", "PP", 0.0),
                new SalariedEmployee(2, "Dana", "KPS", 300.0, 10),
                new HourlySalaryEmployee(3, "Sokha", "BTB", 60, 10.0),
                new Volunteer(4, "Lee", "SR", 0.0),
                new SalariedEmployee(5, "Fy", "KT", 300.0, 20.0),
                new HourlySalaryEmployee(6, "Ka", "PV", 50, 10.0)
        ));
        int option;
        do {
            System.out.println();
            Helper.printSystemInfo();
            option = Helper.validateChoice(5, "-> Choose an option() : ", false);
            switch (option){
                case 1 -> {
                    Helper.insertEmployeeOption(staffMemberList);
                }
                case 2 -> {
                    Helper.updateEmployee(staffMemberList);
                }
                case 3 -> {
                    Helper.displayEmployeeInfo(staffMemberList);
                }
                case 4 -> {
                    Helper.removeEmployee(staffMemberList);
                }
                case 5-> Helper.printMessage("I wish you all the best!", 99);
            }
        }while(option != 5);
        Helper.scanner.close();
    }
}
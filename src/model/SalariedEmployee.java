package model;

public class SalariedEmployee extends StaffMember {
    private double salary;
    private double bonus;
    public SalariedEmployee(int id, String name, String address, double salary, double bonus){
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "SalariedEmployee{" +
                "salary=" + salary +
                ", bonus=" + bonus +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public double pay(){
        return salary + bonus;
    }
}

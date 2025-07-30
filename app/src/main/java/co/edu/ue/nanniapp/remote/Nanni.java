package co.edu.ue.nanniapp.remote;

public class Nanni {
    private String name;
    private String location;
    private int salary;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String name) {
        this.location = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int name) {
        this.salary = name;
    }
    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", url='" + location + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }

}


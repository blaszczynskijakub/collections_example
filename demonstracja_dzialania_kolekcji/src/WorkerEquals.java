import java.util.Objects;

public class WorkerEquals implements Comparable<WorkerEquals> {
    public String surname;
    public String age;

    public WorkerEquals(String surname, String age) {
        this.surname = surname;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerEquals that = (WorkerEquals) o;
        return Objects.equals(surname, that.surname) && Objects.equals(age, that.age);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "WorkerEquals{" +
                "surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                '}';
    }



    @Override
    public int compareTo(WorkerEquals o) {
        return Integer.parseInt(this.age)-Integer.parseInt(o.age);
    }
}

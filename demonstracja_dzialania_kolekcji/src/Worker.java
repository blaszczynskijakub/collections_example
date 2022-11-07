import java.util.Comparator;

public class Worker {
    public String surname;
    public String age;

    public Worker( String surname, String age) {
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
class NameCompare implements Comparator<Worker>
{

        public int compare(Worker m1, Worker m2)
        {
            return m1.surname.compareTo(m2.surname);
        }

}


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;


/*
 * Klasa PersonException jest klasą wyjątków służącą do zgłaszania błędów
 * występujących przy operacjach na obiektach klasy RentalCompany.
 */
class RentalCompanyException extends Exception {

    private static final long serialVersionUID = 1L;

    public RentalCompanyException(String message) {
        super(message);
    }

}

public class RentalCompany {

    public HashSet<String> cars = new HashSet<>();
    public TreeSet<String> trucks = new TreeSet<>();
    public ArrayList<String> clients = new ArrayList<>();
    public LinkedList<String> salaries = new LinkedList<>();

    public ArrayList<WorkerEquals> workerEquals = new ArrayList<>();
    public ArrayList<Worker> workers = new ArrayList<>();

    public static RentalCompany rentalCompanyAdding() throws RentalCompanyException {
        RentalCompany rentalCompany = new RentalCompany();


        return rentalCompany;
    }


    public String getCars() {
        return cars.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String getTrucks() {
        return trucks.stream().map(Object::toString).collect(Collectors.joining(", "));
    }


    public String getClients() {
        return clients.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String getSalaries() {
        return salaries.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String getWorkers() {
        return workers.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String getWorkersEquals() {
        return workerEquals.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

}  // koniec klasy RentalCompany

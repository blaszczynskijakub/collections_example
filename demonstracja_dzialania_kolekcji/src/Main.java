import java.util.Collections;

public class Main {

    private static final String GREETING_MESSAGE =
            "Program kolekcje - wersja konsolowa\n" +
                    "Inspirowany repo kolekcje dr inż. Paweł Rogaliński\n";
    private static final String NEGATIVE_RESULT_MESSAGE =
            "Obiekty w kolekcji nie są sobie równe\n";

    private static final String POSITIVE_RESULT_MESSAGE =
            "Obiekty w kolekcji są sobie równe\n";
    private static final String MENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Usuń wybrane dane z kolekcji    \n" +
                    "2 - Usuń dane                       \n" +
                    "3 - Dodaj dane do kolekcji          \n" +
                    "4 - Stan wypożyczalni               \n" +
                    "5 - Dodawanie elementów tych samych, a takich samych               \n" +
                    "6 - Wpływ equals na kolekcję               \n" +
                    "7 - Wpływ Comparable i Comparator na kolekcję               \n" +
                    "0 - Zakończ program                 \n";
    private static final String DelateElementCollectionMENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Utwórz wypożyczalnie samochodów \n" +
                    "2 - Usuń wszystkie dane             \n" +
                    "3 - Modyfikuj wypożyczalnie         \n" +
                    "4 - Stan wypożyczalni               \n" +
                    "0 - Zakończ program                 \n";


    private static final String CHANGE_MENU =
            "   Co zmienić?     \n" +
                    "1 - HashSet samochody        \n" +
                    "2 - TreeSet ciężarówki       \n" +
                    "3 - ArrayLista klienci       \n" +
                    "4 - LinkedLista płace        \n" +
                    "0 - Powrót do menu głównego  \n";

    private static final String AddDifElementMenu =
            "   Co zmienić?     \n" +
                    "1 - Dodaj 2 elementy takie same do HashSet auta     \n" +
                    "2 - Dodaj 2 elementy te same do HashSet auta       \n"
                    +
                    "3 - Dodaj 2 elementy takie same do ArrayList klienci     \n" +
                    "4 - Dodaj 2 elementy te same do do ArrayList klienci      \n"
                    +
                    "0 - Powrót do menu głównego  \n";

    private static final String EqualsInfluenceMenu =
            "   Co zmienić?     \n" +
                    "1 - Dodaj 2 obiekty do ArrayList z nadpisanym equals     \n" +
                    "2 - Dodaj 2 obiekty do ArrayList bez nadpisanego equals   \n"
                    +
                    "3 - Sprawdź równość obiektów ArrayLista z equals    \n"
                    +
                    "4 - Sprawdź równość obiektów ArrayLista bez equals   \n"
                    +
                    "0 - Powrót do menu głównego  \n";

    private static final String CompareableInfluenceMenu =
            "   Co zmienić?     \n" +
                    "1 - Dodaj 2 obiekty do ArrayList z nadpisanym Comparable   \n" +
                    "2 - Dodaj 2 obiekty do ArrayList z nadpisanym Comparator   \n"
                    +
                    "3 - Posortuj obiekty w ArrayLista z Comparable po wieku   \n"
                    +
                    "4 - Posortuj obiekty w ArrayLista z Comparator po nazwie\n"
                    +
                    "0 - Powrót do menu głównego  \n";


    /**
     * ConsoleUserDialog - pomocnicza klasa zawierająca zestaw
     * prostych metod do realizacji dialogu z użytkownikiem
     * w oknie konsoli tekstowej.
     */
    private static final ConsoleUserDialog UI = new JOptionUserDialog();


    public static void main(String[] args) throws RentalCompanyException {
        //uruchomienie aplikacji
        Main application = new Main();
        application.runMainLoop();
    }


    public RentalCompany currentCompany = null;


    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() throws RentalCompanyException {
        UI.printMessage(GREETING_MESSAGE);
        currentCompany = RentalCompany.rentalCompanyAdding();
        while (true) {
            UI.clearConsole();
            showRentalCompany(currentCompany);
            try {
                switch (UI.enterInt(MENU + "==>> ")) {
                    case 1:
                        if (currentCompany == null)
                            throw new RentalCompanyException("Żadny element nie został usunięty.");
                        delateRentalCompanyData(currentCompany);
                        break;
                    case 2:
                        currentCompany = null;
                        currentCompany = RentalCompany.rentalCompanyAdding();
                        UI.printInfoMessage("Dane aktualnej firmy zostały usunięte");
                        break;
                    case 3:
                        if (currentCompany == null)
                            throw new RentalCompanyException("Żadna zmiana nie zaszła");
                        changeRentalCompanyData(currentCompany);
                        break;
                    case 4:
                        break;
                    case 5:
                        if (currentCompany == null)
                            throw new RentalCompanyException("Żadna zmiana nie zaszła");
                        addDiffElement(currentCompany);
                        break;
                    case 6:
                        if (currentCompany == null)
                            throw new RentalCompanyException("Żadna zmiana nie zaszła");
                        equalsInfluence(currentCompany);
                        break;
                    case 7:
                        if (currentCompany == null)
                            throw new RentalCompanyException("Żadna zmiana nie zaszła");
                        compareInfluence(currentCompany);
                        break;


                    case 0:
                        UI.printInfoMessage("\nProgram zakończył działanie!");
                        System.exit(0);
                } // koniec instrukcji switch
            } catch (RentalCompanyException e) {

                UI.printErrorMessage(e.getMessage());
            }
        } // koniec pętli while
    }


    /*
     * Metoda wyświetla stan kolekcji
     */
    static void showRentalCompany(RentalCompany rentalCompany) {
        StringBuilder sb = new StringBuilder();

        if (rentalCompany != null) {
            sb.append("Aktualna firma: \n")
                    .append("  HashSet marki samochdów: ").append(rentalCompany.getCars()).append("\n")
                    .append("  TreeSet marki ciężarówek: ").append(rentalCompany.getTrucks()).append("\n")
                    .append("  ArrayList nazwiska klientów: ").append(rentalCompany.getClients()).append("\n")
                    .append("  LinkedList wartości pensji: ").append(rentalCompany.getSalaries()).append("\n")
                    .append("  ArrayList pracowników z equals: ").append(rentalCompany.getWorkersEquals()).append("\n")
                    .append("  ArrayList pracowników bez equals: ").append(rentalCompany.getWorkers()).append("\n");
        } else
            sb.append("Brak danych firmy\n");
        UI.printMessage(sb.toString());
    }


    /*
     * Metoda pozwala dodawać elementry do kolekcji
     */
    static void changeRentalCompanyData(RentalCompany rentalCompany) {
        while (true) {
            UI.clearConsole();
            showRentalCompany(rentalCompany);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1:
                        rentalCompany.cars.add(UI.enterString("Podaj nazwę auta: "));
                        break;
                    case 2:
                        rentalCompany.trucks.add(UI.enterString("Podaj nazwę ciężarówki: "));
                        break;
                    case 3:
                        rentalCompany.clients.add(UI.enterString("Podaj nazwisko klienta: "));
                        break;
                    case 4:
                        rentalCompany.salaries.add(UI.enterString("Podaj wartość pensji"));
                        break;
                    case 0:
                        return;
                }  // koniec instrukcji switch
            } catch (Exception e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
    }

    //Metoda usuwa wybrany element kolekcji wykrzoystujac miedzy innymi iteracje
    static void delateRentalCompanyData(RentalCompany rentalCompany) {
        while (true) {
            UI.clearConsole();
            showRentalCompany(rentalCompany);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1:
                        String input = UI.enterString("Podaj auto do usunięcia: ");
                        //iteracja po elementach
                        for (String car : rentalCompany.cars) {
                            if (car.equals(input)) {
                                rentalCompany.cars.remove(car);
                            }

                        }
                        break;
                    case 2:
                        input = UI.enterString("Podaj ciężarówkę do usunięcia: ");
                        //iteracja po elementach
                        for (String truck : rentalCompany.trucks) {
                            if (truck.equals(input)) {
                                rentalCompany.trucks.remove(truck);
                            }

                        }
                        break;
                    case 3:
                        input = UI.enterString("Podaj klienta do usunięcia: ");
                        //iteracja po elementach
                        for (String client : rentalCompany.clients) {
                            if (client.equals(input)) {
                                rentalCompany.clients.remove(client);
                            }

                        }
                        break;
                    case 4:
                        input = UI.enterString("Podaj pensję do usunięcia: ");
                        //iteracja po elementach
                        for (String salary : rentalCompany.salaries) {
                            if (salary.equals(input)) {
                                rentalCompany.salaries.remove(salary);
                            }

                        }
                        break;

                    case 5:


                        break;

                    case 0:
                        return;
                }  // koniec instrukcji switch
            } catch (Exception e) {


            }
        }
    }


    //Metoda do dodawania tych samych i takich samych elementów do kolekcji
    static void addDiffElement(RentalCompany rentalCompany) {
        while (true) {
            UI.clearConsole();
            showRentalCompany(rentalCompany);

            try {
                switch (UI.enterInt(AddDifElementMenu + "==>> ")) {
                    case 1:
                        String car = UI.enterString("Podaj nazwę aut: ");
                        String car2 = car;
                        rentalCompany.cars.add(car);
                        rentalCompany.cars.add(car2);
                        break;
                    case 2:

                        car = UI.enterString("Podaj nazwę aut: ");
                        rentalCompany.cars.add(car);
                        rentalCompany.cars.add(car);
                        break;
                    case 3:
                        String surname = UI.enterString("Podaj nazwisko klientów: ");
                        String surname2 = surname;
                        rentalCompany.clients.add(surname);
                        rentalCompany.clients.add(surname2);
                        break;
                    case 4:

                        surname = UI.enterString("Podaj nazwisko klientów: ");
                        rentalCompany.clients.add(surname);
                        rentalCompany.clients.add(surname);
                        break;

                    case 0:
                        return;
                }  // koniec instrukcji switch
            } catch (Exception e) {

                UI.printErrorMessage(e.getMessage());
            }
        }
    }

    //metoda do porówania działania equals
    static void equalsInfluence(RentalCompany rentalCompany) {
        while (true) {
            UI.clearConsole();
            showRentalCompany(rentalCompany);

            try {
                switch (UI.enterInt(EqualsInfluenceMenu + "==>> ")) {
                    case 1:
                        WorkerEquals workerEquals = new WorkerEquals((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        WorkerEquals workerEquals2 = new WorkerEquals((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        rentalCompany.workerEquals.add(workerEquals);
                        rentalCompany.workerEquals.add(workerEquals2);
                        break;
                    case 2:
                        Worker worker = new Worker((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        Worker worker2 = new Worker((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        rentalCompany.workers.add(worker);
                        rentalCompany.workers.add(worker2);
                        break;
                    case 3:
                        int loopResult = 0;
                        for (WorkerEquals element : rentalCompany.workerEquals) {
                            if (!element.equals(rentalCompany.workerEquals.get(0))) {
                                UI.printMessage(NEGATIVE_RESULT_MESSAGE);
                                loopResult = 1;
                                break;
                            }
                        }
                        if (loopResult == 0) {
                            UI.printMessage(POSITIVE_RESULT_MESSAGE);

                        }

                        break;

                    case 4:
                        loopResult = 0;
                        for (Worker element : rentalCompany.workers) {
                            if (!element.equals(rentalCompany.workers.get(0))) {
                                UI.printMessage(NEGATIVE_RESULT_MESSAGE);
                                loopResult = 1;
                                break;
                            }
                        }
                        if (loopResult == 0) {
                            UI.printMessage(POSITIVE_RESULT_MESSAGE);

                        }
                        break;

                    case 0:
                        return;
                }  // koniec instrukcji switch
            } catch (Exception e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
    }


    //metoda do pokanaie compare i comparable
    static void compareInfluence(RentalCompany rentalCompany) {
        while (true) {
            UI.clearConsole();
            showRentalCompany(rentalCompany);

            try {
                switch (UI.enterInt(CompareableInfluenceMenu + "==>> ")) {
                    case 1:
                        WorkerEquals workerEquals = new WorkerEquals((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        WorkerEquals workerEquals2 = new WorkerEquals((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        rentalCompany.workerEquals.add(workerEquals);
                        rentalCompany.workerEquals.add(workerEquals2);
                        break;
                    case 2:
                        Worker worker = new Worker((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        Worker worker2 = new Worker((UI.enterString("Podaj nazwisko pracownika: ")), UI.enterString("Podaj wiek pracownika: "));
                        rentalCompany.workers.add(worker);
                        rentalCompany.workers.add(worker2);
                        break;
                    case 3:
                        Collections.sort(rentalCompany.workerEquals);

                        break;

                    case 4:
                        NameCompare nameCompare = new NameCompare();
                        Collections.sort(rentalCompany.workers, nameCompare);
                        break;

                    case 0:
                        return;
                }  // koniec instrukcji switch
            } catch (Exception e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
    }
}  // koniec klasy PersonConsoleApp

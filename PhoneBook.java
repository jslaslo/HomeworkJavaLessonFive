import javax.swing.*;
import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        Map<String, List<String>> phoneBook = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        boolean isWork = true;
        System.out.println("Добро пожаловать в программу телефонный стправочник!");
        while (isWork) {
            System.out.println();
            System.out.printf("Список команд:%n" +
                    "1) ADD <NAME> <PHONE NUMBER> - добавить в справочник новый контакт;%n" +
                    "2) GET <NAME> - получить список всех номеров по фамилии контакта;%n" +
                    "3) REMOVE <NAME> - удалить все номера по фамилии;%n" +
                    "4) LIST - посмотреть все записи;%n" +
                    "5) EXIT - завершить программу.%n");
            System.out.println();
            System.out.print("Введите команду: ");
            String input = scanner.nextLine();
            String[] data = input.split(" ", 3);
            if (data[0].equals("ADD")) {
                if (data.length > 2) {
                    addedNumber(data[1], data[2], phoneBook);
                    System.out.println("Контакт успешно добавлен...");
                } else {
                    System.out.printf("Вы не ввели имя контакта или его номер телефона." +
                            "%nПовторите попытку...%n");
                }
            } else if (data[0].equals("GET")) {
                if (data.length > 1) {
                    getByName(data[1], phoneBook);
                } else {
                    System.out.printf("Вы не ввели имя контакта, который хотели бы найти." +
                            "%nПовторите попытку...%n");
                }
            } else if (data[0].equals("REMOVE")) {
                if (data.length > 1) {
                    removeContact(data[1], phoneBook);
                } else {
                    System.out.printf("Вы не ввели имя контакта, который хотели бы удалить." +
                            "%nПовторите попытку...%n");
                }
            } else if (data[0].equals("LIST")) {
                System.out.println(getAll(phoneBook));
            } else if (data[0].equals("EXIT")) {
                System.out.printf("До свидания!%n");
                isWork = false;
            } else {
                System.out.printf("Некорректный ввод команды, ознакомьтесь с командами." +
                        "%nПовторите попытку...%n");
            }
        }
    }

    static void sorted(Map<String, List<String>> phoneBook) {
        Map<String, List<String>> contacts = phoneBook;
        System.out.println(contacts);
    }

    static void addedNumber(String name, String number, Map<String, List<String>> phoneBook) {
        if (phoneBook.containsKey(name)) {
            List<String> numbersPerson = phoneBook.get(name);
            numbersPerson.add(number);
            phoneBook.put(name, numbersPerson);
        } else {

            List<String> numbersPerson = new ArrayList<>();
            numbersPerson.add(number);
            phoneBook.put(name, numbersPerson);
        }
    }

    static void getByName(String name, Map<String, List<String>> phoneBook) {
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            if (entry.getKey().equals(name)) {
                System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue().toString());
                return;
            }
        }
        System.out.printf("Не найдена запись с фамилией \"%s\"%n", name);
    }

    static void removeContact(String name, Map<String, List<String>> phoneBook) {
        if (phoneBook.containsKey(name)) {
            phoneBook.remove(name);
            System.out.println("Контакт успешно удален...");
        } else {
            System.out.printf("Не найдена запись с фамилией \"%s\"%n", name);
        }
    }

    static String getAll(Map<String, List<String>> phoneBook) {
        if (phoneBook.isEmpty()) {
            return "Телефонный справочник пока пуст%n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phoneBook.entrySet());
            entries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
            for (Map.Entry<String, List<String>> entry : entries) {
                List<String> phoneNumbers = entry.getValue();
                stringBuilder.append(entry.getKey());
                stringBuilder.append(" -> ");
                stringBuilder.append(phoneNumbers);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
}



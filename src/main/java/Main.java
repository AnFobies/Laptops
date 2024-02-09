//Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
//        Создать множество ноутбуков.
//        Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации
//        и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
//        Например:
//        “Введите цифру, соответствующую необходимому критерию:
//        1 - ОЗУ
//        2 - Объем ЖД
//        3 - Операционная система
//        4 - Цвет …
//        5 - Поиск
//        Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации
//        можно также в Map.
//        Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
//        Работу сдать как обычно ссылкой на гит репозиторий.
//        Частые ошибки:
//        1. Заставляете пользователя вводить все существующие критерии фильтрации
//        2. Невозможно использовать более одного критерия фильтрации одновременно
//        3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру,
//        а не всем введенным пользователем
//        4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков
//        или добавить еще ноутбук, то программа начинает работать некорректно

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Set<Laptops> laptops = new HashSet<>();
        laptops.add(new Laptops(8, 512, "Windows", "Gray"));
        laptops.add(new Laptops(2, 128, "Linux", "Gray"));
        laptops.add(new Laptops(4, 256, "Windows", "Red"));
        laptops.add(new Laptops(32, 2048, "Windows", "Black"));
        laptops.add(new Laptops(8, 512, "Mac", "White"));
        laptops.add(new Laptops(8, 1024, "Mac", "Gray"));
        laptops.add(new Laptops(16, 1024, "Linux", "Red"));
        laptops.add(new Laptops(6, 256, "Windows", "White"));
        laptops.add(new Laptops(16, 512, "Linux", "Black"));

        filter(laptops);

    }

    public static void filter(Set<Laptops> laptops) {
        HashMap<String, Object> filters = new HashMap<>();

        int userRequest = 0;

        System.out.println("""
                Подскажите, по каким критериям вы хотите отфильтровать данные?
                1) Оперативная память\s
                2) Объем жесткого диска\s
                3) Операционная система\s
                4) Цвет ноутбука\s
                5) Закончить с фильтрацией""");

        Scanner scanner = new Scanner(System.in);

        while (userRequest != 5) {
            userRequest = scanner.nextInt();
            if (userRequest < 1 || userRequest > 5) {
                System.out.println("Введите цифру от 1 до 5");
            }
            switch (userRequest) {
                case (1):
                    System.out.println("Минимальный объем ОЗУ?");
                    filters.put("ram", scanner.nextInt());
                    break;
                case (2):
                    System.out.println("Минимальный объем ЖД?");
                    filters.put("hd", scanner.nextInt());
                    break;
                case (3):
                    System.out.println("Операционная система?");
                    filters.put("os", scanner.next());
                    break;
                case (4):
                    System.out.println("Цвет?");
                    filters.put("color", scanner.next());
                    break;
            }
        }


        Set<Laptops> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hd", 0) instanceof Integer && laptop.hd >= (int) filters.getOrDefault("hd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptops laptop : filteredLaptops) {
            System.out.println(laptop);

        }
    }
}
package ua.vfrundin.start;

import ua.vfrundin.models.*;

/**
 * Class StartUI - класс является точкой входа в программу, он обеспечивает полноценную работу трекера,
 * используя пользовательский ввод и методы работы с хранилищем заявок.
 *
 * @author vfrundin
 * @version 1.0
 * @since 25.02.2018
 */
public class StartUI {

    /**
     * Константа для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа для вывода в консоль всех заявок.
     */
    private static final String SHOWALL = "1";
    /**
     * Константа для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа для поиска заявки по ID.
     */
    private static final String FINDBYID = "4";
    /**
     * Константа для поиска заявок по имени.
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля объекта класса.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод реализует основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select: ");
            if (ADD.equals(answer)) {
                // Добавление заявки через отдельный метод.
                this.createItem();
            } else if (SHOWALL.equals(answer)) {
                // Вывод на экран всех заявок.
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                // Редактирование заявки.
                this.editItem();
            } else if (DELETE.equals(answer)) {
                // Удаление заявки.
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                // Поиск заявки по Id.
                this.findItemById();
            } else if (FINDBYNAME.equals(answer)) {
                // Поиск заявки по Id.
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                // Выход из цикла.
                String byBy = String.format("------------------------------------------------------------------- "
                                + "%n%s %n%s", "                          ВСЕГО ДОБРОГО!",
                        "-------------------------------------------------------------------");
                System.out.println(byBy);
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("--------------------- Добавление новой заявки ---------------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите текст заявки: ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("----------------- Новая заявка с Id: " + item.getId() + "-----------------");
    }

    /**
     * Метод выводит на экран все заявки находящиеся в хранилище.
     */
    private void showAllItems() {
        System.out.println("------------------ Перечень всех заявок в ситеме ------------------");
        Item[] orders = this.tracker.getAll();
        int ordersCount = orders.length;
        for (int index = 0; index < ordersCount; index++) {
            String ordersElement = String.format("%s%s %n%s%s %n%s%s %n", "Id заявки: ", orders[index].getId(),
                    "Имя заявки: ", orders[index].getName(), "Текст заявки: ", orders[index].getDescription());
            System.out.println(ordersElement);
        }
        System.out.println("----------------- Количество заявок в хранилище: " + ordersCount + " ----------------");
    }

    /**
     * Метод реализует изменение заявки находящейся в хранилище.
     */
    private void editItem() {
        System.out.println("----------------------- Корректировка заявки ----------------------");
        String ordersId = this.input.ask("Укажите Id заявки: ");
        Item item = this.tracker.findById(ordersId);
        if (item != null) {
            String question = String.format("Старое имя заявки: %s %n%s", item.getName(), "Введите новое имя заявки: ");
            String name = this.input.ask(question);
            question = String.format("Старый текст заявки: %s %n%s", item.getDescription(),
                    "Введите новый текст заявки: ");
            String desc = this.input.ask(question);
            Item newItem = new Item(name, desc);
            newItem.setId(ordersId);
            this.tracker.update(newItem);
            System.out.println("-------------- Заявка с Id " + ordersId + " обновлена! ---------------");
        } else {
            System.out.println("-------------- Заявка с Id " + ordersId + " не найдена! --------------");
        }
    }

    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private void deleteItem() {
        System.out.println("------------------------- Удаление заявки -------------------------");
        String ordersId = this.input.ask("Укажите Id заявки: ");
        Item item = this.tracker.findById(ordersId);
        if (item != null) {
            this.tracker.delete(item);
            System.out.println("---------------- Заявка с Id " + ordersId + " удалена! ---------------");
        } else {
            System.out.println("-------------- Заявка с Id " + ordersId + " не найдена! --------------");
        }
    }

    /**
     * Метод реализует поиск заявки по Id.
     */
    private void findItemById() {
        System.out.println("----------------------- Поиск заявки по Id ------------------------");
        String ordersId = this.input.ask("Укажите Id заявки: ");
        Item item = this.tracker.findById(ordersId);
        if (item != null) {
            String searchResult = String.format("Результат поиска: %nId заявки: %s%nИмя заявки: %s%n"
                    + "Текст заявки: %s%n", item.getId(), item.getName(), item.getDescription());
            System.out.println(searchResult);
            System.out.println("-------------------------------------------------------------------");
        } else {
            System.out.println("-------------- Заявка с Id " + ordersId + " не найдена! --------------");
        }
    }

    /**
     * Метод реализует поиск по имени заявки.
     */
    private void findItemsByName() {
        System.out.println("---------------------- Поиск заявок по имени ----------------------");
        String ordersName = this.input.ask("Укажите имя заявки: ");
        Item[] searchResults = this.tracker.findByName(ordersName);
        int ordersCount = searchResults.length;
        for (int index = 0; index < ordersCount; index++) {
            String ordersElement = String.format("%s%s %n%s%s %n%s%s %n", "Id заявки: ", searchResults[index].getId(),
                    "Имя заявки: ", searchResults[index].getName(), "Текст заявки: ",
                    searchResults[index].getDescription());
            System.out.println(ordersElement);
        }
        System.out.println("------------------------ Найдено заявок: " + ordersCount + " ------------------------");
    }

    private void showMenu() {
        String mainMenu = String.format("%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s", "Main menu:",
                "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                "4. Find item by Id", "5. Find items by name", "6. Exit Program");
        System.out.println(mainMenu);
    }

    /**
     * Запуск программы
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

}


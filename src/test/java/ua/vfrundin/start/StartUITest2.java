package ua.vfrundin.start;

import org.junit.Test;
import ua.vfrundin.models.Item;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author vfrundin
 * @version $Id$
 * @since 0.1
 */
public class StartUITest2 {

    // Создаем Tracker
    Tracker tracker = new Tracker();
    // Поле содержит ссылку на стандартный вывод в консоль.
    private final PrintStream stdout = System.out;
    // Создаем буфер для хранения вывода.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("Execute before.");
        // Заменяем стандартный вывод на вывод в память для тестирования.
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        // Возвращаем обратно стандартный вывод в консоль.
        System.setOut(this.stdout);
        System.out.println("Execute after.");
    }

    @Test
    public void whenTrackerHas1ItemThenItShowItemWithSameName() {
        // Напрямую добавляем новую заявку.
        Item item = tracker.add(new Item("test name", "desc"));
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"1", "6"});
        // создаём StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что вывод в консоль соответствует нашим ожиданиям.
        assertThat(new String(out.toByteArray()),
                is(String.format("%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n%s%n%s%s %n%s%s %n%s%s %n%n%s%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n%s %n%s %n%s%n",
                        "Main menu:", "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                        "4. Find item by Id", "5. Find items by name", "6. Exit Program",
                        "------------------ Перечень всех заявок в ситеме ------------------",
                        "Id заявки: ", tracker.getAll()[0].getId(), "Имя заявки: ", tracker.getAll()[0].getName(),
                        "Текст заявки: ", tracker.getAll()[0].getDescription(),
                        "----------------- Количество заявок в хранилище: 1 ----------------",
                        "Main menu:", "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                        "4. Find item by Id", "5. Find items by name", "6. Exit Program",
                        "-------------------------------------------------------------------",
                        "                          ВСЕГО ДОБРОГО!",
                        "-------------------------------------------------------------------")));
    }

    @Test
    public void whenUserAdd4ItemsAndThenSearchItem3ByIdThenTrackerFindItemWithNameItem3() {
        // Напрямую добавляем новые заявки.
        Item item1 = tracker.add(new Item("test name 1", "desc 1"));
        Item item2 = tracker.add(new Item("test name 2", "desc 2"));
        Item item3 = tracker.add(new Item("test name 3", "desc 3"));
        Item item4 = tracker.add(new Item("test name 4", "desc 4"));
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"4", item3.getId(), "6"});
        // создаём StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что вывод в консоль соответствует нашим ожиданиям.
        assertThat(new String(out.toByteArray()),
                is(String.format("%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n%s%n%s%n%s%s%n%s%s%n%s%s%n%n%s%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n%s %n%s %n%s%n",
                        "Main menu:", "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                        "4. Find item by Id", "5. Find items by name", "6. Exit Program",
                        "----------------------- Поиск заявки по Id ------------------------",
                        "Результат поиска: ", "Id заявки: ", tracker.getAll()[2].getId(),
                        "Имя заявки: ", tracker.getAll()[2].getName(), "Текст заявки: ",
                        tracker.getAll()[2].getDescription(),
                        "-------------------------------------------------------------------",
                        "Main menu:", "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                        "4. Find item by Id", "5. Find items by name", "6. Exit Program",
                        "-------------------------------------------------------------------",
                        "                          ВСЕГО ДОБРОГО!",
                        "-------------------------------------------------------------------")));
    }

    @Test
    public void whenUserAdd4ItemsAndThenSearchItem2ByNameThenTrackerFindItemWithNameItem2() {
        // Напрямую добавляем новые заявки.
        Item item1 = tracker.add(new Item("test name 1", "desc 1"));
        Item item2 = tracker.add(new Item("test name 2", "desc 2"));
        Item item3 = tracker.add(new Item("test name 3", "desc 3"));
        Item item4 = tracker.add(new Item("test name 4", "desc 4"));
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"5", item2.getName(), "6"});
        // создаём StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что вывод в консоль соответствует нашим ожиданиям.
        assertThat(new String(out.toByteArray()),
                is(String.format("%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n%s%n%s%s %n%s%s %n%s%s %n%n%s%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n%s %n%s %n%s%n",
                        "Main menu:", "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                        "4. Find item by Id", "5. Find items by name", "6. Exit Program",
                        "---------------------- Поиск заявок по имени ----------------------",
                        "Id заявки: ", tracker.getAll()[1].getId(), "Имя заявки: ", tracker.getAll()[1].getName(),
                        "Текст заявки: ", tracker.getAll()[1].getDescription(),
                        "------------------------ Найдено заявок: 1 ------------------------",
                        "Main menu:", "0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                        "4. Find item by Id", "5. Find items by name", "6. Exit Program",
                        "-------------------------------------------------------------------",
                        "                          ВСЕГО ДОБРОГО!",
                        "-------------------------------------------------------------------")));
    }

}
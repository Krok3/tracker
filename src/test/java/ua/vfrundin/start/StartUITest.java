package ua.vfrundin.start;

import org.junit.Test;
import ua.vfrundin.models.Item;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class StartUITest {

    Tracker tracker = new Tracker(); // Создаем Tracker

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenTrackerHas1ItemThenItShowItemWithSameName() {
        // Напрямую добавляем новую заявку.
        Item item = tracker.add(new Item("test name", "desc"));
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"1", "6"});
        // создаём StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateTrackerHasUpdatedValue() {
        // Напрямую добавляем новую заявку.
        Item item = tracker.add(new Item());
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // Создаем StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenUserAdd2ItemsAndThenDelItem1ThenTrackerHasItemWithNameItem2() {
        // Напрямую добавляем новые заявки.
        Item item1 = tracker.add(new Item("test name 1", "desc 1"));
        Item item2 = tracker.add(new Item("test name 2", "desc 2"));
        // Создаем StubInput с данными имитирующими ввод пользователя.
        Input input = new StubInput(new String[]{"3", item1.getId(), "6"});
        // создаём StartUI и вызываем метод init().
        new StartUI(input, tracker).init();
        // Проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.getAll()[0].getName(), is("test name 2"));
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
        // Проверяем, что в tracker найдена нужная заявка.
        assertThat(tracker.findById(item3.getId()).getName(), is("test name 3"));
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
        // Проверяем, что в tracker найдена нужная заявка.
        assertThat(tracker.findByName(item2.getName())[0].getName(), is("test name 2"));
    }

}
package ua.vfrundin.start;

import org.junit.Test;
import ua.vfrundin.models.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    Tracker tracker = new Tracker();
    Item item1 = new Item("test1", "testDescription", 123L);
    Item item2 = new Item("test2", "testDescription2", 1234L);
    Item item3 = new Item("test3", "testDescription3", 12345L);
    Item item4 = new Item("test2", "testDescription4", 123456L);
    Item item5 = new Item("test5", "testDescription5", 1234567L);

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        tracker.add(item1);
        assertThat(tracker.getAll()[0], is(item1));
    }

    @Test
    public void whenAddSomeItemsToArrayThenGetArrayOfSameItems() {
        // Добавляем заявки в трекер.
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        Item[] expected = {item1, item2, item3, item4, item5};
        // Проверяем, что метод вернул все заявки.
        assertThat(tracker.getAll(), is(expected));
    }

    @Test
    public void whenUpdateNameThenReturnNewName() {
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(item1);
        // Проставляем старый id из previous, который был сгенерирован выше.
        item2.setId(item1.getId());
        // Обновляем заявку в трекере.
        tracker.update(item2);
        // Проверяем, что заявка с таким id имеет новое имя test2.
        assertThat(tracker.findById(item1.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteOneItemThenArrayOfItemsBecomesShorter() {
        // Добавляем заявки в трекер.
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        // Удаляем заявку из трекера.
        tracker.delete(item4);
        Item[] expected = {item1, item2, item3, item5};
        // Проверяем, что заявка удалена корректно.
        assertThat(tracker.getAll(), is(expected));
    }

    @Test
    public void whenSearchingItemByIdThenFindCorrectItem() {
        // Добавляем заявки в трекер.
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        // Получаем id одной из заявок.
        Item[] testArray = tracker.getAll();
        String wantedId = testArray[2].getId();
        Item expected = item3;
        // Проверяем, что метод нашел нужную заявку.
        assertThat(tracker.findById(wantedId), is(expected));
    }

    @Test
    public void whenSearchingItemByNameThenFindCorrectItem() {
        // Добавляем заявки в трекер.
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        // Получаем имя одной из заявок.
        String wantedName = item2.getName();
        Item[] expected = {item2, item4};
        // Проверяем, что метод нашел нужные заявки.
        assertThat(tracker.findByName(wantedName), is(expected));
    }

}
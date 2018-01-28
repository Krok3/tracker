package ua.vfrundin.start;

import ua.vfrundin.models.*;

import java.util.*;

/**
 * Class Tracker - класс реализует хранилище заявок и позволяет выполнять основные операции с ними, такие как:
 * добавление заявок, редактирование заявок, удаление заявок, получение списка всех заявок, получение списка по имени,
 * получение заявки по id.
 *
 * @author vfrundin
 * @version 1.0
 * @since 27.01.2018
 */
public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод реализующий добавление заявки в хранилище.
     *
     * @param item новая заявка.
     * @return item добавленная заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует id для новых заявок
     *
     * @return String.valueOf(System.currentTimeMillis() + RN.nextInt()) - сгенерированное id новой заявки.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод обновляет (заменяет) заявку в массиве заявок.
     *
     * @param item измененная заявка.
     */
    public void update(Item item) {
        String wantedId = item.getId();
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(wantedId)) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Метод удаляет заявку из массива заявок.
     *
     * @param item заявка для удаления.
     */
    public void delete(Item item) {
        String wantedId = item.getId();
        int wantedIndex = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(wantedId)) {
                wantedIndex = index;
                break;
            }
        }
        if (wantedIndex + 1 < items.length) {
            System.arraycopy(items, wantedIndex + 1, items, wantedIndex, position - wantedIndex);
        } else {
            items[wantedIndex] = null;
        }
        this.position--;
    }

    /**
     * Метод ищет заявку в массиве заявок по id.
     *
     * @param id уникальный идентификатор искомой заявки.
     * @return result результат поиска (найденнаяlse заявка или null).
     */
    protected Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод ищет все заявки с совпадающим именем в массиве заявок.
     *
     * @param key искомое имя заявки.
     * @return Arrays.copyOf(outArray, outIndex) - массив с найденными заявками (или пустой массив).
     */
    protected Item[] findByName(String key) {
        Item[] outArray = new Item[items.length];
        int outIndex = 0;

        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                outArray[outIndex] = item;
                outIndex++;
            }
        }
        return Arrays.copyOf(outArray, outIndex);
    }

    /**
     * Метод возвращает все заявки внесенные в массив заявок.
     *
     * @return result - массив всех заведенных заявок.
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

}

package ua.vfrundin.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author vfrundin
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        // Получаем ссылку на стандартный вывод в консоль.
        PrintStream stdout = System.out;
        // Создаем буфер для хранения вывода.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Заменяем стандартный вывод на вывод в память для тестирования.
        System.setOut(new PrintStream(out));
        // Выполняем действия пишущие в консоль.
        new Paint(new Square()).draw();
        // Проверяем результат вычисления.
        assertThat(new String(out.toByteArray()), is(String.format("%s%n%s%n%s%n%s%n", "****", "*  *", "*  *", "****")));
        // Возвращаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        // Получаем ссылку на стандартный вывод в консоль.
        PrintStream stdout = System.out;
        // Создаем буфер для хранения вывода.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Заменяем стандартный вывод на вывод в память для тестирования.
        System.setOut(new PrintStream(out));
        // Выполняем действия пишущие в консоль.
        new Paint(new Triangle()).draw();
        // Проверяем результат вычисления.
        assertThat(new String(out.toByteArray()), is(String.format("%s%n%s%n%s%n%s%n", "   *", "  * *", " *   *", "*******")));
        // Возвращаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }
}
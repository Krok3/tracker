package ua.vfrundin.pseudo;

import org.junit.Before;
import org.junit.After;
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
    public void whenDrawSquare() {
        // Выполняем действия пишущие в консоль.
        new Paint(new Square()).draw();
        // Проверяем результат вычисления.
        assertThat(new String(out.toByteArray()), is(String.format("%s%n%s%n%s%n%s%n", "****", "*  *", "*  *", "****")));
    }

    @Test
    public void whenDrawTriangle() {
        // Выполняем действия пишущие в консоль.
        new Paint(new Triangle()).draw();
        // Проверяем результат вычисления.
        assertThat(new String(out.toByteArray()), is(String.format("%s%n%s%n%s%n%s%n", "   *", "  * *", " *   *", "*******")));
    }
}
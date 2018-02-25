package ua.vfrundin.start;

import java.util.Scanner;

/**
 * Class ConsoleInput - класс реализует интерфейс Input, обеспечивает пользовательский ввод данных через консоль.
 *
 * @author vfrundin
 * @version 1.0
 * @since 25.02.2018
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод опрашивает пользователя через консоль.
     *
     * @param question - строка, текст запроса данных от пользователя.
     * @return scanner.nextLine() - возвращает строку введенную пользователем.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

}

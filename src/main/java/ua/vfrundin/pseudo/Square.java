package ua.vfrundin.pseudo;

/**
 * Class Square - класс реализует интерфейс Shape, обеспечивает прорисовку квадрата в псевдографике.
 *
 * @author vfrundin
 * @version $Id$
 * @since 0.1
 */
public class Square implements Shape {
    /**
     * Метод формирует строку в форме квадрата.
     * @return pic.toString() - возвращает строку в форме квадрата.
     */
    @Override
    public String draw() {
        String line = System.getProperty("line.separator");
        StringBuilder pic = new StringBuilder();
        pic.append("****");
        pic.append(line);
        pic.append("*  *");
        pic.append(line);
        pic.append("*  *");
        pic.append(line);
        pic.append("****");
        pic.append(line);
        return pic.toString();
    }
}

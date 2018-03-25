package ua.vfrundin.pseudo;

/**
 * Class Paint - класс предназначен для прорисовки геометрических фигур любой фоормы. Иллюстрирует использование
 * pattern Strategy, позволяет динамически менять форму объекта класса Paint.
 *
 * @author vfrundin
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /* Конкретное значение поля shape определяет форму предмета. */
    private Shape shape;

    /**
     * Конструтор инициализирующий поля объекта класса.
     *
     * @param shape форма геометрической фигуры.
     */
    public Paint(Shape shape) {
        this.shape = shape;
    }

    /**
     * Метод-сеттер, позволяет менять форму объекта.
     *
     * @param shape форма геометрической фигуры.
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Метод выводит в консоль геометрическую фигуру выбранной формы.
     */
    public void draw() {
        System.out.print(shape.draw());
    }
}

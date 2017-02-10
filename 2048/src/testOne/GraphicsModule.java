package testOne;

public class GraphicsModule {
	/**
	 * Определяет действия, которые должен производить графический модуль игры.
	 *
	 * @author DoKel
	 * @version 1.0
	 */
	public interface GraphicsModule1 {

	    /**
	     * Отрисовывает переданное игровое поле
	     *
	     * @param field Игровое поле, которое необходимо отрисовать
	     */
	    void draw(Gamezona field);

	    /**
	     * @return Возвращает true, если в окне нажат "крестик"
	     */
	    boolean isCloseRequested();

	    /**
	     * Заключительные действия, на случай, если модулю нужно подчистить за собой.
	     */
	    void destroy();
	}
}

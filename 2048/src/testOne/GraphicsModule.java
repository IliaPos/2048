package testOne;

public class GraphicsModule {
	/**
	 * ���������� ��������, ������� ������ ����������� ����������� ������ ����.
	 *
	 * @author DoKel
	 * @version 1.0
	 */
	public interface GraphicsModule1 {

	    /**
	     * ������������ ���������� ������� ����
	     *
	     * @param field ������� ����, ������� ���������� ����������
	     */
	    void draw(Gamezona field);

	    /**
	     * @return ���������� true, ���� � ���� ����� "�������"
	     */
	    boolean isCloseRequested();

	    /**
	     * �������������� ��������, �� ������, ���� ������ ����� ���������� �� �����.
	     */
	    void destroy();
	}
}

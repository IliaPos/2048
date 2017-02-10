package testOne;

import java.util.Random;

import javax.swing.JOptionPane;

public class Logic {
	private Gamezona gamezona;
	boolean endGame = false;

	// Создание массива интов (в нём происходит логика игры)
	private int[][] theField = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];
	private int[][] oldField = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];
	

	public Logic(Gamezona gamezona) {
		super();
		this.gamezona = gamezona;

	}

	// Метод очищающий поле (заполняет его нулями).
	private void clearField() {
		for (int i = 0; i < Constants.COUNT_CELLS_X; i++) {
			for (int s = 0; s < Constants.COUNT_CELLS_Y; s++) {
				theField[i][s] = 0;
			}
		}
	}

	// Метод создаёт в начале игры 2 рандомные плитки.
	public void createInitialCells() {
		clearField();
		for (int i = 0; i < Constants.COUNT_INITITAL_CELLS; i++) {
			generateNewCell();
		}
		gamezona.refresh(theField);

	}

	// Метод создаёт рандомную плитку.
	private void generateNewCell() {

		int randomX, randomY, randomN, n;
		randomN = new Random().nextInt(100);
		if (randomN > 15) {
			n = Constants.INITIAL_CELL_STATE;
		} else {
			n = Constants.LUCKY_INITIAL_CELL_STATE;
		}
		do {
			randomX = new Random().nextInt(Constants.COUNT_CELLS_X);
			randomY = new Random().nextInt(Constants.COUNT_CELLS_Y);

		} while (theField[randomX][randomY] != 0);
		theField[randomX][randomY] = n;

	}

	int[] oldRow = new int[Constants.COUNT_CELLS_Y];

	// логика сдвигов плиток.
	public void shift(Direction sdvig) {
		boolean rif = false;
		int[][] tempField = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];
		arrayCopy(theField, tempField);

		switch (sdvig) {
		case RIGHT:
			for (int i = 3; i >= 0; i--) {
				int j = 3, k = 3;
				while (j >= 0) {
					if (theField[i][j] == 0) {
						j--;
					} else {
						if (j != k) {
							if (theField[i][k] == 0) {
								theField[i][k] = theField[i][j];
								rif = true;
								theField[i][j] = 0;
							} else {
								if (theField[i][j] == theField[i][k]) {
									theField[i][k] = theField[i][k] * 2;
									rif = true;
									theField[i][j] = 0;
									k--;
								} else {
									k--;
									if (theField[i][k] == 0) {
										theField[i][k] = theField[i][j];
										rif = true;
										theField[i][j] = 0;
									}
								}
							}

						}
						j--;

					}
				}

			} // for
			break;
		case LEFT:
			for (int i = 3; i >= 0; i--) {
				int j = 0, k = 0;
				while (j <= 3) {
					if (theField[i][j] == 0) {
						j++;
					} else {
						if (j != k) {
							if (theField[i][k] == 0) {
								theField[i][k] = theField[i][j];
								rif = true;
								theField[i][j] = 0;
							} else {
								if (theField[i][j] == theField[i][k]) {
									theField[i][k] = theField[i][k] * 2;
									rif = true;
									theField[i][j] = 0;
									k++;
								} else {
									k++;
									if (theField[i][k] == 0) {
										theField[i][k] = theField[i][j];
										rif = true;
										theField[i][j] = 0;
									}
								}
							}

						}
						j++;

					}
				}

			} // for
			break;
		case DOWN:
			for (int i = 3; i >= 0; i--) {
				int j = 3, k = 3;
				while (j >= 0) {
					if (theField[j][i] == 0) {
						j--;
					} else {
						if (j != k) {
							if (theField[k][i] == 0) {
								theField[k][i] = theField[j][i];
								rif = true;
								theField[j][i] = 0;
							} else {
								if (theField[j][i] == theField[k][i]) {
									theField[k][i] = theField[k][i] * 2;
									rif = true;
									theField[j][i] = 0;
									k--;
								} else {
									k--;
									if (theField[k][i] == 0) {
										theField[k][i] = theField[j][i];
										rif = true;
										theField[j][i] = 0;
									}
								}
							}

						}
						j--;

					}
				}

			} // for
			break;
		case UP:
			for (int i = 3; i >= 0; i--) {
				int j = 0, k = 0;
				while (j <= 3) {
					if (theField[j][i] == 0) {
						j++;
					} else {
						if (j != k) {
							if (theField[k][i] == 0) {
								theField[k][i] = theField[j][i];
								rif = true;
								theField[j][i] = 0;
							} else {
								if (theField[j][i] == theField[k][i]) {
									theField[k][i] = theField[k][i] * 2;
									rif = true;
									theField[j][i] = 0;
									k++;
								} else {
									k++;
									if (theField[k][i] == 0) {
										theField[k][i] = theField[j][i];
										rif = true;
										theField[j][i] = 0;
									}
								}
							}

						}
						j++;

					}
				}

			} // for
			break;

		}
		if (rif) {
			arrayCopy(tempField,oldField );
			generateNewCell();
			
		}

		gamezona.refresh(theField);
	}

	// private Direction shiftProv(Direction sdvigProv) {
	int automatic = 0;

	public void createCellTest() {
		clearField();
		theField[0][0] = 4;
		theField[0][1] = 4;
		// theField[0][2] = 2;
		// theField[0][3] = 2;
		theField[1][1] = 2;
		theField[1][3] = 2;
		theField[2][0] = 4;
		theField[2][1] = 4;
		theField[2][2] = 4;
		theField[2][3] = 8;
		theField[3][0] = 2;
		theField[3][1] = 2;
		theField[3][2] = 8;
		theField[3][3] = 16;

		gamezona.refresh(theField);
	}

	private void arrayCopy(int[][] source, int[][] destination) {
		for (int i = 0; i < 4; i++) {
			for (int s = 0; s < 4; s++) {
				destination[i][s] = source[i][s];

			}
		}
	}

	public void reBack() {
		arrayCopy(oldField, theField);
		gamezona.refresh(theField);

	}
}

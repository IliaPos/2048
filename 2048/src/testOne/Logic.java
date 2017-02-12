package testOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Logic {

	private Gamezona gamezona;
	private boolean won = false;
	private boolean oldWon = false;
	public int score = 0, oldscore = 0, scoreTop = 0, oldScoreTop = 0;
	
	String path = System.getProperty("user.dir");
	// Создание массива интов (в нём происходит логика игры)
	private int[][] theField = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];
	// Создания массива интов для reset
	private int[][] oldField = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];

	public Logic(Gamezona gamezona) {
		super();
		this.gamezona = gamezona;
		this.scoreTop = readerScoreTop();

	}

	// Метод очищающий поле (заполняет его нулями).
	private void clearField() {
		for (int i = 0; i < Constants.COUNT_CELLS_X; i++) {
			for (int s = 0; s < Constants.COUNT_CELLS_Y; s++) {
				theField[i][s] = 0;

			}
		}
		score = 0;
	}

	// Метод создаёт в начале игры 2 рандомные плитки.
	public void createInitialCells() {
		clearField();
		for (int i = 0; i < Constants.COUNT_INITITAL_CELLS; i++) {
			generateNewCell();
		}
		gamezona.refresh(theField, score, scoreTop);
		this.won = false;

	}

	public void writerScoreTop() {
		try (FileWriter writer = new FileWriter(path + "/records.dat", false)) {
			// запись всей строки
			String text = Integer.toString(scoreTop);
			writer.write(text);
			// // запись по символам
			// writer.append('\n');
			// writer.append('E');
			//
			writer.flush();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public Integer readerScoreTop() {
		File file = new File(path + "/records.dat");
		BufferedReader br = null;

		try {
			if (file.exists() && !file.isDirectory()) {
				InputStream in = new FileInputStream(file);
				br = new BufferedReader(new InputStreamReader(in));
				List<String> lines = new ArrayList<String>();
				String line = null;
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
				if (!lines.isEmpty()) {
					return Integer.parseInt(lines.get(0));
				}

			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

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

		int tempscore = score;
		int tempscoreTop = scoreTop;
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
									score = score + theField[i][k];
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
									score = score + theField[i][k];
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
									score = score + theField[k][i];
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
									score = score + theField[k][i];
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
			arrayCopy(tempField, oldField);
			generateNewCell();
			oldscore = tempscore;
			oldScoreTop = tempscoreTop;
			if (score > scoreTop) {
				scoreTop = score;
				writerScoreTop();
			}
			gamezona.refresh(theField, score, scoreTop);

			if (proverkaEnd()) {

				JOptionPane.showMessageDialog(null, "your score = " + " " + score, "Game over!!" + " ",
						JOptionPane.INFORMATION_MESSAGE);

			}
			oldWon = won;
			if (!won && proverkaWin()) {
				JOptionPane.showMessageDialog(null, "Click \"OK\" to continue ", "You won!!" + " ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		

	}

	private boolean proverkaEnd() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (theField[i][j] == 0) {
					return false;
				}
				if (j < 3) {
					if (theField[i][j] == theField[i][j + 1]) {
						return false;
					}
					if (theField[j][i] == theField[j + 1][i]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean proverkaWin(){
		
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ( theField[i][j] == 2048){
					this.won = true;
					return true;
					
				}
				if (theField[j][i] == 2048){
					this.won = true;
					return true;
				}
			}
		
		
	}
		
		return false;
		
	}
		
		
//	}

	private void arrayCopy(int[][] source, int[][] destination) {
		for (int i = 0; i < 4; i++) {
			for (int s = 0; s < 4; s++) {
				destination[i][s] = source[i][s];

			}
		}
	}

	public void reBack() {
		arrayCopy(oldField, theField);
		score = oldscore;
		scoreTop = oldScoreTop;
		won = oldWon;
		gamezona.refresh(theField, score, scoreTop);
		

	}

	public void resetScoreTop() {
		scoreTop = 0;
		writerScoreTop();
		gamezona.refresh(theField, score, scoreTop);

	}

}

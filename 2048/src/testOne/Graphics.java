package testOne;

import java.util.HashMap;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Graphics  {
	enum Graphic {
		CELL2("2"), CELL4("4"), CELL8("8"), CELL16("16"), CELL32("32"), CELL64("64"),
		CELL128("128"), CELL256("256"), CELL512("512"), CELL1024("1024"), CELL2048("2048"), EMPTY("empty");
	

		private Texture texture;

		Graphic(String texturename) {
			try {
				this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + texturename + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		

		public Texture getTexture() {
			return this.texture;
		}
	}
	
	
	
	
	
	public static HashMap<Integer, Graphic> spriteByNumber;
	
	Graphics() {
        spriteByNumber = new HashMap<>();
		spriteByNumber.put(2, Graphic.CELL2);
		spriteByNumber.put(4, Graphic.CELL4);
		spriteByNumber.put(8, Graphic.CELL8);
		spriteByNumber.put(16, Graphic.CELL16);
		spriteByNumber.put(32, Graphic.CELL32);
		spriteByNumber.put(64, Graphic.CELL64);
		spriteByNumber.put(128, Graphic.CELL128);
		spriteByNumber.put(256, Graphic.CELL256);
		spriteByNumber.put(512, Graphic.CELL512);
		spriteByNumber.put(1024, Graphic.CELL1024);
		spriteByNumber.put(2048, Graphic.CELL2048);
	}
	public Graphic getSpriteByNumber(int n) {
		if(spriteByNumber.containsKey(n)) {
			return spriteByNumber.get(n);
		}

		return Graphic.EMPTY;
	}
	
}

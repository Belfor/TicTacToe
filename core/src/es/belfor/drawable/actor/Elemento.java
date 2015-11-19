package es.belfor.drawable.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Elemento extends Actor {
	public static final int CIRCLE = 0;
	public static final int X = 1;

	private Texture texture;
	private ShapeRenderer s;
	int elemento;

	public Elemento(float x, float y, float ancho, float alto, int elemento) {
		super();
		this.setBounds(x, y, ancho, alto);
		s = new ShapeRenderer();
		this.elemento = elemento;
	}

	private void drawCircle(Batch batch, float parentAlpha) {
		batch.end();
		s.begin(ShapeType.Filled);
		s.setColor(0.60f, 0.25f, 0.25f, 1);
		if (getWidth() < getHeight())
			s.circle(getX() + getWidth() / 2, getY() + getHeight() / 2,
					getWidth() / 2);
		else
			s.circle(getX() + getWidth() / 2, getY() + getHeight() / 2,
					(getHeight() - 1) / 2);
		s.end();

		batch.begin();
	}
	
	private void drawX(Batch batch, float parentAlpha) {
		batch.end();
			s.begin(ShapeType.Filled);
			s.setColor(Color.CYAN);
				s.rectLine(getX(), getY(), getX() + getWidth() - 1, getY()+ getHeight() - 1, 3);
				s.rectLine(getX(), getY()+ getHeight() - 1 ,getX() + getWidth() -1 , getY(), 3);
			s.end();
		batch.begin();
	}


	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		switch(elemento){
			case CIRCLE:drawCircle(batch,parentAlpha);
						break;
			case X: 	drawX(batch,parentAlpha);
						break;

		}

	}

	@Override
	public void act(float delta) {

	}

}
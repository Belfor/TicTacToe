package es.belfor.drawable;



import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;


public class Cuadrante extends Group{

	int n,m;
	float ancho, alto;
	ShapeRenderer bg;
	ShapeRenderer lines;
	

	public Cuadrante(){
		
	}
	public Cuadrante(int n, int m, float ancho, float alto){
		bg = new ShapeRenderer();
		lines = new ShapeRenderer();
	
		this.n = n;
		this.m = m;
		this.ancho = ancho;
		this.alto = alto;
	}
		
	public boolean fueraDeRangoX(float x){
		if (getIndexX(x) < n) return false;
		return true;
	}
	
	public boolean fueraDeRangoY(float y){
		if (getIndexY(y) < m) return false;
		return true;
	}
	
	public void drawBg(){
		bg.begin(ShapeType.Filled);
		bg.setColor(Color.TEAL);
		bg.rect(150, Gdx.graphics.getHeight() - alto * m , ancho * n,  alto * m); //Fondo de color Teal

		bg.end();
		lines.begin(ShapeType.Filled);
		
		lines.setColor(Color.WHITE);
		for (int i = 0; i < n; i++)
			lines.rect(150 + (ancho * i), Gdx.graphics.getHeight() - alto * m, 1, alto*m ); //Lineas blancas
		for (int i = 0; i < m; i++)
			lines.rect(150,   Gdx.graphics.getHeight() - (alto * i) ,  ancho * n, 1 ); //Lineas blancas
		lines.end();
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		drawBg();
		batch.begin();
	}
	

	@Override
	public void act(float delta) {
			
			
	}	
	
	public int getIndexX(float x){
		return (int) ((x-150)/ancho);
	}
	
	public int getIndexY(float y){
		return (int) (((y - Gdx.graphics.getHeight())/(-alto)));
	}

	
		
}
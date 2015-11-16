package es.belfor.drawable;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
		this.ancho = ancho/n;
		this.alto = alto/m;
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
		bg.rect(this.getX(), Gdx.graphics.getHeight() - alto * m , ancho * n,  alto * m); //Fondo de color Teal

		bg.end();
		lines.begin(ShapeType.Filled);
		
		lines.setColor(Color.WHITE);
		for (int i = 0; i < n; i++)
			lines.rect(this.getX() + (ancho * i), Gdx.graphics.getHeight() - alto * m, 1, alto*m ); //Lineas blancas horizontales
		for (int i = 0; i < m; i++)
			lines.rect(this.getX(),   Gdx.graphics.getHeight() - (alto * i) ,  ancho * n, 1 ); //Lineas blancas verticales
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
		return (int) ((x)/ancho);
	}
	
	public int getIndexY(float y){
		return (int) (((y - Gdx.graphics.getHeight())/(-alto)));
	}

	
		
}
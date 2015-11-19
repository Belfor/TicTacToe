package es.belfor.drawable;
/* Autor: Cristobal Daniel Aleman de Leon Nickname: Belfor
 * 
 * Clase Cuadrante
 * Esta clase se ocupa de dibujar en pantalla el cuadrante del tres en raya tiene los metodos necesarios para dibujar X e Y en su 
 * corresponiente casilla
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;

import es.belfor.drawable.actor.Elemento;


public class Cuadrante extends Group{

	int n,m;
	float ancho, alto;
	ShapeRenderer bg;
	ShapeRenderer lines;
	Group elements;
	

	public Cuadrante(){
		
	}
	/**
	 * Constructor de la clase, n numero de casillas horizontales, m numero de casillas verticales
	 * @param n
	 * @param m
	 * @param ancho
	 * @param alto
	 */
	public Cuadrante(int n, int m, float ancho, float alto){
		bg = new ShapeRenderer();
		lines = new ShapeRenderer();
		elements = new Group();
	
		this.n = n;
		this.m = m;
		this.ancho = ancho/n;
		this.alto = alto/m;
	}
	/**
	 * Nos devuelve true si la posicion a la que intentas acceder esta fuera del rango de las x
	 * @param x
	 * @return
	 */
	public boolean fueraDeRangoX(float x){
		if (getIndexX(x) < n) return false;
		return true;
	}
	 /**
	  * Nos devuelve true si la posicion a la que intentas acceder esta fuera del rango de las y
	  * @param y
	  * @return
	  */
	public boolean fueraDeRangoY(float y){
		if (getIndexY(y) < m) return false;
		return true;
	}
	/**
	 * Dibuja el el cuadrante
	 */
	public void drawBg(Batch batch, float parentAlpha){
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
	/**
	 * Metodo de dibujado
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawBg(batch,parentAlpha);
	
		elements.draw(batch, parentAlpha);
	}
	

	@Override
	public void act(float delta) {
			
			
	}	
	
	/**
	 * Devuelve el inidice X en funcion a la posicion Y
	 * @param x
	 * @return
	 */
	public int getIndexX(float x){
		return (int) ((x - getX())/ancho);
	}
	/**
	 *Devuelve el indice Y en funcion a la posicion Y
	 * @param y
	 * @return
	 */
	public int getIndexY(float y){
		return (int) (((y - Gdx.graphics.getHeight())/(-alto)));
	}
	
	public void drawElemento(float x, float y, int elemento){
		int ix = getIndexX(x);
		int iy = getIndexY(y);
		System.out.print(ix + " " + iy);
		elements.addActor(new Elemento((ix * ancho) + getX(),Gdx.graphics.getHeight() -alto * (iy + 1), ancho, alto, elemento));
		
	}

	
		
}
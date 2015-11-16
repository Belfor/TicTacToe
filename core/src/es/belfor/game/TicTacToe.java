package es.belfor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import es.belfor.drawable.Cuadrante;

public class TicTacToe extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Stage stage;
	Cuadrante cuadrante;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		stage = new Stage();
		cuadrante = new Cuadrante(3,3,Gdx.graphics.getWidth() - 150,Gdx.graphics.getHeight());
		cuadrante.setX(150);
		stage.addActor(cuadrante);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}

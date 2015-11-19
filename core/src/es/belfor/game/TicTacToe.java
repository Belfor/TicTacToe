package es.belfor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import es.belfor.drawable.Cuadrante;
import es.belfor.drawable.actor.Elemento;

public class TicTacToe extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Stage stage;
	Cuadrante cuadrante;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		stage = new Stage();
		cuadrante = new Cuadrante(3, 3, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		
		stage.addActor(cuadrante);
		
		Gdx.input.setInputProcessor(stage);

		// Click del raton
		stage.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// if (dialog.getStage() == null)
				System.out.println(x + " " + y);
				if (cuadrante != null) {
					if (!cuadrante.fueraDeRangoX(x) && !cuadrante.fueraDeRangoY(y)) {
						System.out.println(x + " " + y);
						cuadrante.drawElemento(x,y,Elemento.CIRCLE);
						
					}
				}
				return true;

				// }

			}
		});

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}

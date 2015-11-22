package es.belfor.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import es.belfor.IA.MiniMax;
import es.belfor.drawable.Cuadrante;
import es.belfor.drawable.actor.Elemento;

public class TicTacToe extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Stage stage;
	Cuadrante cuadrante;
	MiniMax minimax;

	boolean turno_jugador = false;
	ArrayList <Integer> tablero;

	@Override
	public void create() {
		tablero = new ArrayList <Integer>();
		minimax = new MiniMax();
		
		for (int i = 0; i < 9; i++) tablero.add(0);
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
					if (!cuadrante.fueraDeRangoX(x)
							&& !cuadrante.fueraDeRangoY(y)) {
						if(tablero.get(cuadrante.getIndexX(x) + cuadrante.getIndexY(y) * 3) == 0 && turno_jugador){
							System.out.println(x + " " + y);
							cuadrante.drawElemento(x, y, Elemento.CIRCLE);
							System.out.println("Suma " + (cuadrante.getIndexX(x) + cuadrante.getIndexY(y)*3));
							tablero.set(cuadrante.getIndexX(x) + cuadrante.getIndexY(y) * 3,1);
							turno_jugador = false;
						}
					}
				}
				return true;

				// }

			}
		});

	}


	public void drawMovimiento() {
		
		tablero = minimax.mueve(tablero);
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cuadrante.drawElemento(j, i, tablero.get(cnt));
				cnt++;
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!turno_jugador) {
			drawMovimiento();
			turno_jugador = true;
		}
		stage.draw();
	}
}

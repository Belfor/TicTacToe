package es.belfor.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import es.belfor.IA.MiniMax;
import es.belfor.drawable.Cuadrante;
import es.belfor.drawable.actor.Elemento;

public class TicTacToe extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage;
	Cuadrante cuadrante;
	MiniMax minimax;

	boolean turno_jugador = false;
	ArrayList <Integer> tablero;
	
	Dialog dialog;
	Skin skin;
	
	int turno;

	@Override
	public void create() {
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"),new TextureAtlas("skin/uiskin.atlas"));
		dialog = new Dialog("EL JUEGO HA ACABADO", skin);
		dialog.button("Aceptar",true);
		
		tablero = new ArrayList <Integer>();
		minimax = new MiniMax();
		
		for (int i = 0; i < 9; i++) tablero.add(0);
		batch = new SpriteBatch();
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
				if (dialog.getStage() == null)
					if (cuadrante != null) {
						if (!cuadrante.fueraDeRangoX(x)
								&& !cuadrante.fueraDeRangoY(y)) {
							if(tablero.get(cuadrante.getIndexX(x) + cuadrante.getIndexY(y) * 3) == 0 && turno_jugador){
								cuadrante.drawElemento(x, y, Elemento.CIRCLE);
								tablero.set(cuadrante.getIndexX(x) + cuadrante.getIndexY(y) * 3,1);
								turno_jugador = false;
								turno++;
							}
						}
					}
					return true;
	
				}
		});
		
		dialog.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
					resetGame();
					dialog.remove();
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
	
	public void resetGame(){
		for (int i = 0; i < tablero.size(); i++)
			tablero.set(i, 0);
		cuadrante.reset();
		minimax = new MiniMax();
		turno = 0;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (minimax.esVictoria(tablero) || turno == 9){
			dialog.show(stage);
		}else
			if (!turno_jugador) {
				turno++;
				drawMovimiento();
				turno_jugador = true;
			}
	
		stage.draw();
	}
}

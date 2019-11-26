package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MyBox2D extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;

	Texture img2;
	Sprite sprite2;

	World world;
	Body body;
	Body body2;

	final float PIXELS_TO_METERS = 100f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		float screenHeight = Gdx.graphics.getHeight();
		float screenWidth = Gdx.graphics.getWidth();

		// Creating the dynamic logo
		img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(screenWidth / 2 - sprite.getWidth() / 2, screenHeight);

		//Create a physics world, with simulation. The vector passed in is gravity
		world = new World(new Vector2(0, -1.0f), true);

		//Now create a body definition. This defines the physics objects type and position in the simulation
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		//We are going to use 1 to 1 dimensions. Meaning 1 in physics engine is 1 pixel
		//Set our body to the same position as our sprite
		bodyDef.position.set((sprite.getX() + sprite.getWidth()/2)  / PIXELS_TO_METERS,
				             (sprite.getY() + sprite.getHeight()/2) / PIXELS_TO_METERS);

		//Create a body in the world using our definition
		body = world.createBody(bodyDef);

		//Now define the dimensions of the physics shape
		PolygonShape shape = new PolygonShape();

		// We are a box, so this makes sense, no?
		// Basically set the physics polygon to a box with the same dimensions as our sprite
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS,
				       sprite.getHeight()/ 2 / PIXELS_TO_METERS);

		// FixtureDef is a confusing expression for physical properties
		// Basically this is where you, in addition to defining the shape of the body
		// you also define it's properties like density, restitution and others we will see shortly
		// If you are wondering, density and area are used to calculate over all mass
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		Fixture fixture = body.createFixture(fixtureDef);

		shape.dispose();

		// Creating the static couch
		img2 = new Texture("Couch.png");
		sprite2 = new Sprite(img2);
		sprite2.setPosition(0,0);
		float couchScaleY = 0.2f * screenHeight/sprite2.getHeight();
		float finalWidth = couchScaleY * sprite2.getWidth();
		if(finalWidth < screenWidth)
			finalWidth = screenWidth;
		sprite2.setSize(finalWidth, couchScaleY*sprite2.getHeight());

		// Create a static body for our couch
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyDef.BodyType.StaticBody;
		bodyDef2.position.set( (sprite2.getX() + sprite2.getWidth()/2)  / PIXELS_TO_METERS,
				               (sprite2.getY() + sprite2.getHeight()/2) / PIXELS_TO_METERS);
		// Create the couch in the physics world
		body2 = world.createBody(bodyDef2);

		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(sprite2.getWidth()/ 2 / PIXELS_TO_METERS,
				        sprite2.getHeight() / 2 / PIXELS_TO_METERS);

		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = shape2;
		fixtureDef2.density = 1f;
		Fixture fixture2 = body2.createFixture(fixtureDef2);

		shape2.dispose();

	}

	@Override
	public void render () {

		//Update the world
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth()/2 ,
				           (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight()/2 );
		sprite2.setPosition((body2.getPosition().x * PIXELS_TO_METERS) - sprite2.getWidth()/2 ,
				            (body2.getPosition().y * PIXELS_TO_METERS) - sprite2.getHeight()/2 );
		// created by the dynamic body

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		sprite.draw(batch);
		//batch.draw(sprite, sprite.getX(), sprite.getY());
		sprite2.draw(batch);
		//batch.draw(sprite2, sprite2.getX(), sprite2.getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

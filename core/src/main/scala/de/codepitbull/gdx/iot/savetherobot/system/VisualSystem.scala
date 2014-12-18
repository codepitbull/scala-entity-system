package de.codepitbull.gdx.iot.savetherobot.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import de.codepitbull.gdx.iot.savetherobot.component.{DeathCounterFamily, VisualFamily, Visual}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System
import com.badlogic.gdx.graphics.g2d.BitmapFont

class VisualSystem extends System {

  var batch: SpriteBatch = null
  var font: BitmapFont = null

  override def init(): Unit = {
    batch = new SpriteBatch()
    font = new BitmapFont();
    font.setColor(Color.RED);
  }

  override def execute(worldTimeDelta: Float): Unit = {
    Gdx.gl.glClearColor(1, 1, 1, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    VisualFamily.members().foreach(_.visual().draw(batch))
    DeathCounterFamily.members().foreach(death => font.draw(batch, death.text, death.positionAndRotation.x, death.positionAndRotation.y))
    batch.end()
  }

  override def destroy(): Unit = {
    batch.dispose()
  }
}

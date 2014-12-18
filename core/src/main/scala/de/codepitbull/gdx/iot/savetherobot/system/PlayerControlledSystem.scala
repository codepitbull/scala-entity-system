package de.codepitbull.gdx.iot.savetherobot.system

import java.lang.Math._

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import de.codepitbull.gdx.iot.savetherobot.component.{PlayerControlledFamily, PositionAndRotation}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System

class PlayerControlledSystem extends System {
  
  val degreesPerSecond = 240
  val distPerSecond = 300

  override def init(): Unit = {}

  override def execute(worldTimeDelta: Float): Unit = {
    PlayerControlledFamily.members().foreach( player => {
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        val rotation = (player.positionAndRotation.rotation - worldTimeDelta * degreesPerSecond) % 360
        if (rotation < 0)
          player.positionAndRotation.rotation = 360 + rotation
        else
          player.positionAndRotation.rotation = rotation
      }
      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
        player.positionAndRotation.rotation = ((player.positionAndRotation.rotation + worldTimeDelta * degreesPerSecond) % 360)
      }
      if (Gdx.input.isKeyPressed(Keys.UP)) {
        val delta = worldTimeDelta * distPerSecond
        val rotation = toRadians(360 - player.positionAndRotation.rotation)
        val x = sin(rotation) * delta
        val y = cos(rotation) * delta
        player.positionAndRotation.translate(x.toFloat, y.toFloat)
      }
      if (Gdx.input.isKeyPressed(Keys.DOWN)) {
        val delta = worldTimeDelta * -distPerSecond
        val rotation = toRadians(360 - player.positionAndRotation.rotation)
        val x = sin(rotation) * delta
        val y = cos(rotation) * delta
        player.positionAndRotation.translate(x.toFloat, y.toFloat)
      }
    }
    )

  }

  override def destroy(): Unit = {}
}

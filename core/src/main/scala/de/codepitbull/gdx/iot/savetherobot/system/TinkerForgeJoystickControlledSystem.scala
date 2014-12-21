package de.codepitbull.gdx.iot.savetherobot.system

import java.lang.Math._

import com.tinkerforge.{BrickletJoystick, IPConnection}
import de.codepitbull.gdx.iot.savetherobot.component.{PlayerControlled, PlayerControlledFamily}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System

class TinkerForgeJoystickControlledSystem(val joy1:BrickletJoystick, val joy2:BrickletJoystick) extends System {

  val degreesPerSecond = 240
  val distPerSecond = 300

  override def init(): Unit = {}

  override def execute(worldTimeDelta: Float): Unit = {
    PlayerControlledFamily.members().foreach(player => {
      if(player.playerId == 1) processInput(player, joy1, worldTimeDelta)
      else if(player.playerId == 2) processInput(player, joy2, worldTimeDelta)
    })
  }

  def processInput(player:PlayerControlled, joy:BrickletJoystick, worldTimeDelta: Float): Unit = {
    val position = joy.getPosition()
    if (position.x > 0) {
      val rotation = (player.positionAndRotation.rotation - worldTimeDelta * degreesPerSecond) % 360
      if (rotation < 0)
        player.positionAndRotation.rotation = 360 + rotation
      else
        player.positionAndRotation.rotation = rotation
    }
    if (position.x < 0) {
      player.positionAndRotation.rotation = ((player.positionAndRotation.rotation + worldTimeDelta * degreesPerSecond) % 360)
    }
    if (position.y > 0) {
      val delta = worldTimeDelta * distPerSecond
      val rotation = toRadians(360 - player.positionAndRotation.rotation)
      val x = sin(rotation) * delta
      val y = cos(rotation) * delta
      player.positionAndRotation.translate(x.toFloat, y.toFloat)
    }
    if (position.y < 0) {
      val delta = worldTimeDelta * -distPerSecond
      val rotation = toRadians(360 - player.positionAndRotation.rotation)
      val x = sin(rotation) * delta
      val y = cos(rotation) * delta
      player.positionAndRotation.translate(x.toFloat, y.toFloat)
    }
  }

  override def destroy(): Unit = {}
}

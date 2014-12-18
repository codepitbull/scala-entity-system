package de.codepitbull.gdx.iot.savetherobot.component

import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Component, Entity}

class PositionAndRotation(val entity:Entity, var x:Float, var y:Float, var rotation:Float) extends Component {
  PositionAndRotationFamily + this

  def translate(_x:Float, _y:Float): Unit = {
    x = x + _x
    y = y + _y
  }

  override def destroy(): Unit = {PositionAndRotationFamily - this}
}

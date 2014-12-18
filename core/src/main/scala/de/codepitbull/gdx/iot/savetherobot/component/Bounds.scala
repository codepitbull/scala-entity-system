package de.codepitbull.gdx.iot.savetherobot.component

import com.badlogic.gdx.math.Circle
import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Component, Entity}

class Bounds(val entity: Entity, val positionAndRotation: PositionAndRotation, _bound: Circle) extends Component {

  BoundsFamily + this

  def bound(): Circle = {
    _bound.setPosition(positionAndRotation.x, positionAndRotation.y)
    _bound
  }

  override def destroy(): Unit = BoundsFamily - this
}

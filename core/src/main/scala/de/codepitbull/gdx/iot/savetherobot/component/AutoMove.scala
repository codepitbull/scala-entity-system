package de.codepitbull.gdx.iot.savetherobot.component

import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Component, Entity}

class AutoMove(val entity: Entity, var speedX: Float, var speedY: Float, val positionAndRotation: PositionAndRotation) extends Component {

  AutoMoveFamily + this

  override def destroy(): Unit = AutoMoveFamily - this
}

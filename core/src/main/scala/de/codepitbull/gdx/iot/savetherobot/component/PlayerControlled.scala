package de.codepitbull.gdx.iot.savetherobot.component

import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Component, Entity}

class PlayerControlled(val entity: Entity, val playerId:Integer, var life:Int, val positionAndRotation: PositionAndRotation) extends Component {

  PlayerControlledFamily + this

  override def destroy(): Unit = PlayerControlledFamily - this
}

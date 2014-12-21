package de.codepitbull.gdx.iot.savetherobot.component

import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Entity, Component}

class DeathCounter(val entity:Entity, val playerId:Int, var counter:Int, val positionAndRotation: PositionAndRotation) extends Component {

  DeathCounterFamily + this

  override def destroy(): Unit = {
    DeathCounterFamily - this
  }
}

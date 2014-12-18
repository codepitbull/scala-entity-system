package de.codepitbull.gdx.iot.savetherobot.component

import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Entity, Component}

class DeathCounter(val entity:Entity, var counter:Int, val positionAndRotation: PositionAndRotation) extends Component{

  DeathCounterFamily + this

  def text() = "Deaths "+counter

  override def destroy(): Unit = {
    DeathCounterFamily - this
  }
}

package de.codepitbull.gdx.iot.savetherobot.system

import com.tinkerforge.BrickletSegmentDisplay4x7
import de.codepitbull.gdx.iot.savetherobot.component.DeathCounterFamily
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System

class TinkerForgeVisualSystem(val sd4x7: BrickletSegmentDisplay4x7) extends System {

  val display: List[Short] = List(0x3f, 0x06, 0x5b, 0x4f,
    0x66, 0x6d, 0x7d, 0x07,
    0x7f, 0x6f, 0x77, 0x7c,
    0x39, 0x5e, 0x79, 0x71)

  override def init(): Unit = {
  }

  override def execute(worldTimeDelta: Float): Unit = {
    var deaths: Map[Int, Int] = Map()
    for (death <- DeathCounterFamily.members()) deaths += (death.playerId -> death.counter)
    if (!deaths.contains(1))
      deaths += (1 -> 0)
    if (!deaths.contains(2))
      deaths += (2 -> 0)
    sd4x7.setSegments(Array(display(0), display(deaths(1)), display(0), display(deaths(2))), 7, false)
  }

  override def destroy(): Unit = {
  }
}

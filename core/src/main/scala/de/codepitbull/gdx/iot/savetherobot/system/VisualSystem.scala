package de.codepitbull.gdx.iot.savetherobot.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.tinkerforge.{BrickletSegmentDisplay4x7, IPConnection}
import de.codepitbull.gdx.iot.savetherobot.component.{DeathCounterFamily, VisualFamily, Visual}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System
import com.badlogic.gdx.graphics.g2d.BitmapFont

class VisualSystem(val sd4x7:BrickletSegmentDisplay4x7) extends System {

  val display:List[Short] = List(0x3f,0x06,0x5b,0x4f,
    0x66,0x6d,0x7d,0x07,
    0x7f,0x6f,0x77,0x7c,
    0x39,0x5e,0x79,0x71)

  var batch: SpriteBatch = null
  var font: BitmapFont = null

  override def init(): Unit = {
    batch = new SpriteBatch
    font = new BitmapFont
    font.setColor(Color.RED)
  }

  override def execute(worldTimeDelta: Float): Unit = {
    Gdx.gl.glClearColor(1, 1, 1, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin
    VisualFamily.members().foreach(_.visual().draw(batch))
    DeathCounterFamily.members().foreach(death => font.draw(batch, "Hits: "+death.counter, death.positionAndRotation.x, death.positionAndRotation.y))
    var deaths:Map[Int,Int] = Map()
    for(death <- DeathCounterFamily.members()) deaths += (death.playerId -> death.counter)
    if(!deaths.contains(1))
      deaths += (1 -> 0)
    if(!deaths.contains(2))
      deaths += (2 -> 0)
    sd4x7.setSegments(Array(display(0), display(deaths(1)), display(0), display(deaths(2))), 7, false)

    batch.end()
  }

  override def destroy(): Unit = {
    batch.dispose()
  }
}

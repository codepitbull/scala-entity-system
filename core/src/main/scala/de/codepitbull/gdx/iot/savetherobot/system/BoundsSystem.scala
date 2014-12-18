package de.codepitbull.gdx.iot.savetherobot.system

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import de.codepitbull.gdx.iot.savetherobot.component.{BoundsFamily, Bounds}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System

class BoundsSystem extends System {

  var shapeRenderer:ShapeRenderer = null

  override def init(): Unit = {
    shapeRenderer = new ShapeRenderer()
  }

  override def execute(worldTimeDelta: Float): Unit = {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    shapeRenderer.setColor(Color.BLUE)
    BoundsFamily.members().foreach(bound => shapeRenderer.circle(bound.bound.x, bound.bound.y, bound.bound.radius))
    shapeRenderer.end()
  }

  override def destroy(): Unit = {
    shapeRenderer.dispose()
  }

}

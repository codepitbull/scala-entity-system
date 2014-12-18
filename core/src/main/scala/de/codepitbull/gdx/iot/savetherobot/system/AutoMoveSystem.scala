package de.codepitbull.gdx.iot.savetherobot.system

import de.codepitbull.gdx.iot.savetherobot.component.{AutoMoveFamily, PositionAndRotation}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.System

class AutoMoveSystem(width: Float, height: Float) extends System {

  override def init(): Unit = {}

  override def execute(worldTimeDelta: Float): Unit = {
    AutoMoveFamily.members().foreach(
      autoMove => {
        if (autoMove.positionAndRotation.x < 0 && autoMove.speedX < 0)
          autoMove.speedX = -autoMove.speedX
        if (autoMove.positionAndRotation.y < 0 && autoMove.speedY < 0)
          autoMove.speedY = -autoMove.speedY
        if (autoMove.positionAndRotation.x > width - 32 && autoMove.speedX > 0)
          autoMove.speedX = -autoMove.speedX
        if (autoMove.positionAndRotation.y > height - 32 && autoMove.speedY > 0)
          autoMove.speedY = -autoMove.speedY
        autoMove.positionAndRotation.translate(autoMove.speedX * worldTimeDelta, autoMove.speedY * worldTimeDelta)
      }
    )

  }

  override def destroy(): Unit = {}
}

package de.codepitbull.gdx.iot.savetherobot.component

import com.badlogic.gdx.graphics.g2d.Sprite
import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Component, Entity}

class Visual(val entity: Entity, val positionAndRotation: PositionAndRotation, _sprite: Sprite) extends Component {

  VisualFamily + this

  def visual(): Sprite = {
    _sprite.setX(positionAndRotation.x)
    _sprite.setY(positionAndRotation.y)
    _sprite.setRotation(positionAndRotation.rotation)
    _sprite
  }

  override def destroy(): Unit = {
    VisualFamily - this
  }
}

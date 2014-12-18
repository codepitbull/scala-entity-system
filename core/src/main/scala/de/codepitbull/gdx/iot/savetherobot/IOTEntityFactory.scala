package de.codepitbull.gdx.iot.savetherobot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Circle
import de.codepitbull.gdx.iot.savetherobot.component._
import de.codepitbull.gdx.iot.savetherobot.entitysystem.Entity

object IOTEntityFactory {

  def robotX(entityId: Int, x: Int, y: Int, rot: Float): Entity = {
    val texture = new Texture(Gdx.files.internal("data/robot.png"))
    val sprite = new Sprite(texture)
    val rad = sprite.getWidth / 2
    val entity = new Entity(entityId, Map())
    val posRot = new PositionAndRotation(entity, x, y, rot)
    entity.addComponent(posRot)
    entity.addComponent(new Visual(entity, posRot, sprite))
    entity.addComponent(new Bounds(entity, posRot, new Circle(posRot.x, posRot.y, rad)))
    entity.addComponent(new AutoMove(entity, 250, 50, posRot))
  }

  def robotY(entityId: Int, x: Int, y: Int, rot: Float): Entity = {
    val texture = new Texture(Gdx.files.internal("data/robot.png"))
    val sprite = new Sprite(texture)
    val rad = sprite.getWidth / 2
    val entity = new Entity(entityId, Map())
    val posRot = new PositionAndRotation(entity, x, y, rot)
    entity.addComponent(posRot)
    entity.addComponent(new Visual(entity, posRot, sprite))
    entity.addComponent(new Bounds(entity, posRot, new Circle(posRot.x, posRot.y, rad)))
    entity.addComponent(new AutoMove(entity, 50, 350, posRot))
  }

  def player(entityId: Int, x: Int, y: Int, rot: Float): Entity = {
    val texture = new Texture(Gdx.files.internal("data/player.png"))
    val sprite = new Sprite(texture)
    val rad = sprite.getWidth / 2
    val entity = new Entity(entityId, Map())
    val posRot = new PositionAndRotation(entity, x, y, rot)
    entity.addComponent(posRot)
    entity.addComponent(new Visual(entity, posRot, sprite))
    entity.addComponent(new Bounds(entity, posRot, new Circle(posRot.x, posRot.y, rad)))
    entity.addComponent(new PlayerControlled(entity, posRot))
    val posDeathCountRot = new PositionAndRotation(entity, 20, 20, 0)
    entity.addComponent(new DeathCounter(entity, 0, posDeathCountRot))
  }
}

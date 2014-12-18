package de.codepitbull.gdx.iot.savetherobot.entitysystem

abstract class System {
  def init(): Unit
  def execute(worldTimeDelta: Float): Unit
  def destroy(): Unit
}

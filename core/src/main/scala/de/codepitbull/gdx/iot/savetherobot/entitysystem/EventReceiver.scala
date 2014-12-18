package de.codepitbull.gdx.iot.savetherobot.entitysystem

trait EventReceiver {
  def process(event:Event): Unit
}

package de.codepitbull.gdx.iot.savetherobot.system

import de.codepitbull.gdx.iot.savetherobot.component.DeathCounter
import de.codepitbull.gdx.iot.savetherobot.entitysystem._
import de.codepitbull.gdx.iot.savetherobot.event.{CollisionEvent, DeathEvent}

/**
 * Created by jmader on 19.12.14.
 */
class DeathSystem extends System with EventReceiver {


  override def init(): Unit = EventBus.register(classOf[CollisionEvent], this)

  override def execute(worldTimeDelta: Float): Unit = {}

  override def destroy(): Unit = {
    EventBus.unregister(classOf[CollisionEvent], this)
  }

  override def process(event: Event): Unit = {
    val colEvent = event.asInstanceOf[CollisionEvent]
    if (event.isInstanceOf[CollisionEvent]) {
      val colEvent = event.asInstanceOf[CollisionEvent]
      val deathCounter =
        if (colEvent.entity1.hasComponent(classOf[DeathCounter])) {
          colEvent.entity1.getComponent(classOf[DeathCounter]).asInstanceOf[DeathCounter]
        }
        else if (colEvent.entity2.hasComponent(classOf[DeathCounter])) {
          colEvent.entity2.getComponent(classOf[DeathCounter]).asInstanceOf[DeathCounter]
        }
        else null
      if (deathCounter != null) {
        deathCounter.counter -= 1
        if (deathCounter.counter <= 0)
          EventBus.send(new DeathEvent(deathCounter.entity))
      }
    }
  }
}

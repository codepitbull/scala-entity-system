package de.codepitbull.gdx.iot.savetherobot

import com.badlogic.gdx.{Game, Gdx}
import de.codepitbull.gdx.iot.savetherobot.IOTEntityFactory._
import de.codepitbull.gdx.iot.savetherobot.component.DeathCounter
import de.codepitbull.gdx.iot.savetherobot.entitysystem._
import de.codepitbull.gdx.iot.savetherobot.event.{CollisionEvent, DeathEvent}
import de.codepitbull.gdx.iot.savetherobot.system._

class IOTGame extends Game with EventReceiver {

  EventBus.register(classOf[CollisionEvent], this)
  EventBus.register(classOf[DeathEvent], this)

  var entities: Set[Entity] = Set()
  var deathCounterComponent: Entity = null
  var counter = 0
  val systems = List(new AutoMoveSystem(800, 480),
    new BoundsSystem,
    new VisualSystem,
    new PlayerControlledSystem,
    new CollisionSystem(800, 480))

  override def create(): Unit = {
    entities = Set(
      player(2, 32 * 4 * 2 + 10, 32 * 4 * 2 + 10, 0),
      robotX(3, 32 * 2 + 10, 32 * 2 + 10, 0),
      robotX(4, 32 * 2 * 2 + 10, 32 * 2 * 2 + 10, 0),
      robotX(5, 32 * 3 * 2 + 10, 32 * 3 * 2 + 10, 0),
      robotY(6, 32 * 2 + 10, 32 * 3 + 10, 0),
      robotY(7, 32 * 2 * 2 + 10, 32 * 2 * 3 + 10, 0),
      robotY(8, 32 * 3 * 3 + 10, 32 * 3 * 2 + 10, 0))
    systems.foreach(_.init())
  }

  override def dispose(): Unit = {
    systems.foreach(_.destroy())
  }

  override def render(): Unit = {
    val worldTimeDelta = Gdx.graphics.getDeltaTime()
    systems.foreach(_.execute(worldTimeDelta))
  }

  override def process(event: Event): Unit = {
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
        deathCounter.counter += 1
        if (deathCounter.counter > 5)
          EventBus.send(new DeathEvent(deathCounter.entity))
      }
    }
    if (event.isInstanceOf[DeathEvent]) {
      event.asInstanceOf[DeathEvent].entity.destroy()
      entities - event.asInstanceOf[DeathEvent].entity
    }
  }
}

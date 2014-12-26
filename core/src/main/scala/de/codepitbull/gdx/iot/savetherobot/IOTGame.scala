package de.codepitbull.gdx.iot.savetherobot

import com.badlogic.gdx.{Game, Gdx}
import com.tinkerforge.{BrickletJoystick, BrickletSegmentDisplay4x7, IPConnection}
import de.codepitbull.gdx.iot.savetherobot.IOTEntityFactory._
import de.codepitbull.gdx.iot.savetherobot.component.DeathCounter
import de.codepitbull.gdx.iot.savetherobot.entitysystem._
import de.codepitbull.gdx.iot.savetherobot.event.{CollisionEvent, DeathEvent}
import de.codepitbull.gdx.iot.savetherobot.system._

class IOTGame extends Game with EventReceiver {

  EventBus.register(classOf[DeathEvent], this)

  var entities: Set[Entity] = Set()
  var deathCounterComponent: Entity = null
  var counter = 0

//  Use with TinkerForge joysticks and 4x7-Display
//  val ipcon = new IPConnection
//  val sd4x7 = new BrickletSegmentDisplay4x7("kTU", ipcon)
//  val joy1 = new BrickletJoystick("gSq", ipcon)
//  val joy2 = new BrickletJoystick("hDp", ipcon)
//
//  ipcon.connect("localhost", 4223)
//  joy1.calibrate()
//  joy2.calibrate()

  var startDelay:Float = 5

  val systems = List(new AutoMoveSystem(800, 480),
    new BoundsSystem,
    new VisualSystem,
    new KeyboardControlledSystem,
    new CollisionSystem(800, 480),
    new DeathSystem
  )


  override def create(): Unit = {
    entities = Set(
      player(1, 1, 32 * 4 * 2 + 10, 32 * 4 * 2 + 10, 0),
      player(2, 2, 32 * 6 * 2 + 10, 32 * 4 * 2 + 10, 0),
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
//    ipcon.disconnect()
  }

  override def render(): Unit = {
    startDelay -= Gdx.graphics.getDeltaTime()
    if(startDelay < 0) {
      val worldTimeDelta = Gdx.graphics.getDeltaTime()
      systems.foreach(_.execute(worldTimeDelta))
    }

  }

  override def process(event: Event): Unit = {
    if (event.isInstanceOf[DeathEvent]) {
      event.asInstanceOf[DeathEvent].entity.destroy()
      entities - event.asInstanceOf[DeathEvent].entity
    }
  }
}

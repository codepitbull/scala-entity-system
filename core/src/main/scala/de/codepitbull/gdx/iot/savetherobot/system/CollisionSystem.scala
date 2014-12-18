package de.codepitbull.gdx.iot.savetherobot.system

import de.codepitbull.gdx.iot.savetherobot.component.{BoundsFamily, PlayerControlledFamily, Bounds}
import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Event, EventBus, Entity, System}
import de.codepitbull.gdx.iot.savetherobot.event.CollisionEvent

class CollisionSystem(width:Float,height:Float) extends System {

  var lastCollisions:Set[CollisionEvent] = Set()

  override def init(): Unit = {}

  override def execute(worldTimeDelta: Float): Unit = {
    PlayerControlledFamily.members().foreach(nav => {
      if(nav.positionAndRotation.x <= 0 )nav.positionAndRotation.x = 0
      else if(nav.positionAndRotation.x >= width-32 )nav.positionAndRotation.x = width-32
      if(nav.positionAndRotation.y <= 0 )nav.positionAndRotation.y = 0
      else if(nav.positionAndRotation.y >= height-32 )nav.positionAndRotation.y = height-32
    })

    val members = BoundsFamily.members().toList
    val collisions = col(members.head, members.drop(1)).toSet

    val newCollisions = collisions -- lastCollisions
    val removedCollisions = lastCollisions -- collisions

    lastCollisions = collisions

    newCollisions.foreach(EventBus.send(_))
  }


  def col(source:Bounds, targets:List[Bounds]): List[CollisionEvent] = {
    if(targets.length == 0)
      List[CollisionEvent]()
    else
      findCollisions(source, targets) ::: col(targets.head, targets.drop(1))
  }

  def findCollisions(toMatch:Bounds, matchees:List[Bounds]): List[CollisionEvent] = {
    for(target <- matchees if toMatch.bound().overlaps(target.bound()))
      yield new CollisionEvent(toMatch.entity, target.entity)
  }

  override def destroy(): Unit = {}
}

package de.codepitbull.gdx.iot.savetherobot.event

import de.codepitbull.gdx.iot.savetherobot.entitysystem.{Entity, Event}

class CollisionEvent(val entity1: Entity, val entity2: Entity) extends Event {

  def canEqual(other: Any): Boolean =
    other.isInstanceOf[CollisionEvent]

  override def hashCode(): Int = 41 * (41 + entity1.id) + entity2.id

  override def equals(other: scala.Any): Boolean =
    other match {
      case that: CollisionEvent =>
        (that canEqual this) &&
          entity1.id == that.entity1.id && entity2.id == that.entity2.id
      case _ => false
    }
}

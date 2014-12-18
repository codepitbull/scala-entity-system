package de.codepitbull.gdx.iot.savetherobot.entitysystem

object EventBus {
  private var receiver: Map[Class[_ <: Event], Set[EventReceiver]] = Map()

  def register(eventType: Class[_ <: Event], eventReceiver: EventReceiver): Unit = {
    if (receiver.contains(eventType)) {
      receiver += (eventType -> (receiver(eventType) + eventReceiver))
    }
    else {
      receiver += (eventType -> (Set(eventReceiver)))
    }
  }

  def unregister(eventType: Class[_ <: Event], eventReceiver: EventReceiver): Unit = {
    if (receiver.contains(eventType)) {
      receiver += (eventType -> (receiver(eventType) - eventReceiver))
    }
  }

  def send(event: Event): Unit = {
    if (receiver.contains(event.getClass))
      receiver(event.getClass).foreach(_.process(event))
  }
}

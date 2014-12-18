package de.codepitbull.gdx.iot.savetherobot.entitysystem

/**
 * Created by jmader on 12.12.14.
 */
class Entity(val id: Int, private var _components: Map[Class[_ <: Component], Component]) {

  def addComponent(component: Component): Entity = {
    _components += (component.getClass -> component)
    this
  }

  def hasComponent(clazz: Class[_ <: Component]): Boolean = {
    _components.contains(clazz)
  }

  def getComponent(clazz: Class[_ <: Component]): Component = {
    _components(clazz)
  }

  def destroy(): Unit = {
    _components.foreach(_._2.destroy())
  }
}


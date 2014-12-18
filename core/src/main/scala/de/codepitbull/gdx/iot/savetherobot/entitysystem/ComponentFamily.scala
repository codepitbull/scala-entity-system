package de.codepitbull.gdx.iot.savetherobot.entitysystem

/**
 * Created by jmader on 13.12.14.
 */
abstract class ComponentFamily[A] {
  var familyMembers:Set[A] = Set()

  def members():Set[A] = familyMembers

  def clear(): Unit = {
    familyMembers = Set()
  }

  def + (familyMember:A):Unit = {
    familyMembers += familyMember
  }

  def - (familyMember:A):Unit = {
    familyMembers -= familyMember
  }
}

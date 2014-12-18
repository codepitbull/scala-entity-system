package my.game.pkg

import com.badlogic.gdx.backends.lwjgl._
import de.codepitbull.gdx.iot.savetherobot.IOTGame

object Main extends App {
    val cfg = new LwjglApplicationConfiguration
    cfg.title = "Doom"
    cfg.height = 480
    cfg.width = 800
    cfg.forceExit = false
    new LwjglApplication(new IOTGame, cfg)
}

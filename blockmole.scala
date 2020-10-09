package blockmoles

object Color {
    import java.awt.{Color => JColor}
    val black = new JColor(0, 0, 0) 
    val mole = new JColor(51, 51, 0) 
    val soil = new JColor(153, 102, 51) 
    val grass = new JColor(25, 130, 35) 
    val tunnel = new JColor(204, 153, 102) //skapar olika färger som behövs i övriga moduler
 }

object BlockWindow { //har ett introprog.PixelWindow och ritar blockgrafik
    import introprog.PixelWindow 
    import Color._
    val windowSize = (30, 50) 
    val blockSize = 10 
    val window = new introprog.PixelWindow(300, 500, "Blockmole Window")
    type Pos = (Int, Int)
    def block(pos: Pos)(color: java.awt.Color = java.awt.Color.GRAY): Unit = { 
    val x = pos._1 * blockSize // räkna ut fönstrets x-kordinat i pixlar istf block 
    val y = pos._2 * blockSize // räkna ut fönstrets y-kordinat i pixlar istf block 
    window.fill(x, y, 10, 10, color) } 
    def rectangel(leftTop: Pos)(size: (Int,Int))(color: java.awt.Color = java.awt.Color.GRAY): Unit ={ 
        for(y <- leftTop._2 to leftTop._2 + size._2){ 
            for(x <- leftTop._1 to leftTop._1 + size._1) block(x,y)(color) } 
        }
        val maxWaitMillis = 10
        def waitForKey(): String = { window.awaitEvent(maxWaitMillis) 
            while(window.lastEventType != PixelWindow.Event.KeyPressed){ window.awaitEvent(maxWaitMillis) } 
            {println(s"KeyPressed: " + window.lastKey) 
            window.lastKey
        }
    }
}

object Mole {
    def Dig(): Unit = { 
        var x = BlockWindow.windowSize._1/2 
        var y = BlockWindow.windowSize._2/2 
        var quit = false
        while(!quit){
            BlockWindow.block(x, y)(Color.mole)
            val key = BlockWindow.waitForKey()
            BlockWindow.block(x,y)(Color.tunnel)
            if (key == "w") y -=1
            else if (key == "a") x -= 1
            else if (key == "s") y += 1
            else if (key == "d") x += 1
            else if (key == "q") quit = true
        }
    }
}

object Main { def drawWorld(): Unit = { 
    import Color._ 
    BlockWindow.rectangel(0,0)(size = (30,4))(Color.grass) 
    BlockWindow.rectangel(0,4)(size = (30,46))(Color.soil) 
}
    def main(args: Array[String]): Unit = { 
        drawWorld() 
        Mole.Dig() } 
}
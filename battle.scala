case BlockWindow{
    import itroprog.pixelwindow

    val nuberOfBlocks :(Int,Int)
    val title : String = "Block window"
    val pixelwindow = new pixelwindow(numberOfBlocks._1 = blockSize, numberOfBlocks._2=blockSize,title)
    def setBlock(pos:Pos, color: java.awt.color): Unit =
    def getBlock(pos: Pos): java.awt.color = 
    de write (
        text:String
        pos:Pos
        color: java.awt.color
        textSize:Int=blockSize
        ): Unit = pixelwindow.drawText(
            text, pos.x=blockSize, pos.y=blockSize,color,textSize
        )
    def nextEvent()
    
    







}
package blockbattle
object Game {
val windowSize = (30, 50)
val windowTitle = "EPIC BLOCK BATTLE"
val blockSize = 14
val skyRange = 0 to 7
val grassRange = 8 to 8
object Color { ??? }
/** Used with the different ranges and eraseBlocks */
def backgroundColorAtDepth(y: Int): java.awt.Color = ???
}
class Game(
val leftPlayerName: String = "LEFT",
val rightPlayerName: String = "RIGHT"
) {
import Game._ // direkt tillgång till namn på medlemmar i kompanjon
val window = new BlockWindow(windowSize, windowTitle, blockSize)
val leftMole: Mole = ???
val rightMole: Mole = ???
def drawWorld(): Unit = ???
/** Use to erase old points, e.g updated score */
def eraseBlocks(x1: Int, y1: Int, x2: Int, y2: Int): Unit = ???
def update(mole: Mole): Unit = ??? // update, draw new, erase old
def gameLoop(): Unit = ???
def start(): Unit = {
println("Start digging!")
println(s"$leftPlayerName ${leftMole.keyControl}")
println(s"$rightPlayerName ${rightMole.keyControl}")
drawWorld()
gameLoop()
}

var quit = false
val delayMillis = 80
def gameLoop(): Unit = {
while(!quit) {
val t0 = System.currentTimeMillis
handleEvents() // ändrar riktning vid tangenttryck etc.
update(leftMole) // flyttar, ritar, suddar, etc.
update(rightMole)
val elapsedMillis = (System.currentTimeMillis - t0).toInt
Thread.sleep((delayMillis - elapsedMillis) max 0)
}

def handleEvents(): Unit = {
var e = window.nextEvent()
while (e != BlockWindow.Event.Undefined) {
e match {
case BlockWindow.Event.KeyPressed(key) =>
??? // ändra riktning på resp. mullvad
case BlockWindow.Event.WindowClosed =>
??? // avsluta spel-loopen
}
e = window.nextEvent()
})
}
window.setBlock(mole.nextPos, mole.color) // draw new
window.setBlock(mole.pos, Color.tunnel) // erase old
mole.move() // update

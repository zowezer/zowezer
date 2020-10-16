package blockbattle
case class Pos(x: Int, y: Int) {
    def moved(delta: (Int, Int)): Pos = new Pos(x+delta._1, y+delta._2)}


case class KeyControl(left: String, right: String, up: String, down: String){
  def direction(key: String): (Int, Int) = key match{
    case `left` => (-1,0)
    case `right` => (1,0)
    case `up` => (0,-1)
    case `down` => (0,1)
    case _ => (0,0)
  }
  def has(key: String): Boolean = key match {
    case `left` => true
    case `right` => true
    case `up` => true
    case `down` => true
    case _ => false
   }
  }











class Mole(
    val name: String,
    var pos: Pos,
    var dir: (Int, Int),
    val color: java.awt.Color,
    val keyControl: KeyControl)
    {
    var points = 0
    override def toString =s"Mole[name=$name, pos=$pos, dir=$dir, points=$points]"/**Om keyControl.has(key) så uppdateras riktningen dir enligt keyControl*/
    
    def setDir(key: String): Unit = {
      if (keyControl.has(key))  dir = keyControl.direction(key)
      }
    
    def reverseDir(): Unit = dir = (dir._1*-1,dir._2*-1)
    def move(): Unit = pos = nextPos
    def nextPos: Pos = pos.moved(dir)
    }
































 class BlockWindow(
   val nbrOfBlocks: (Int, Int),
   val title: String = "BLOCK WINDOW",
   val blockSize: Int = 14) {
     import introprog.PixelWindow 
     val pixelWindow =new PixelWindow(nbrOfBlocks._1*blockSize, nbrOfBlocks._2*blockSize, title)
     def setBlock(pos: Pos, color: java.awt.Color): Unit = ???
     def getBlock(pos: Pos): java.awt.Color = ???
     def write(
       text: String,
       pos: Pos,
       color: java.awt.Color,
       textSize: Int = blockSize): Unit =
       pixelWindow.drawText(text, pos.x*blockSize, pos.y*blockSize, color, textSize)
     def nextEvent(maxWaitMillis: Int = 10): BlockWindow.Event.EventType  = {
       importBlockWindow.Event._
       pixelWindow.awaitEvent(maxWaitMillis)
       pixelWindow.lastEventType match{
         case PixelWindow.Event.KeyPressed => KeyPressed(pixelWindow.lastKey)
         case PixelWindow.Event.WindowClosed=>WindowClosed
         case _  => Undefined
         }
      }
  }
object BlockWindow {
  def delay(millis: Int): Unit = Thread.sleep(millis)
object Event {
  trait EventType
   case classKeyPressed(key: String) extends EventType 
   case object WindowClosed          extends EventType 
   case object Undefined             extends EventType
   }
}

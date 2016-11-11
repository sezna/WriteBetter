import scala.io.Source

object AuthorLookup {
  def main(args:Array[String]) {
    println("The AuthorLookup is running")
    val text = readFile("Charles Dickens")
    val matches = wordLookup("the", text._2)
    matches.foreach(x => println(x))
  }
  /**
   * Read an author file. Input is an author name and a word you are looking for.
   *
   * TBD: look up word here or return some data structure that is handled in another function?
   */
  def wordLookup(word: String, text: String):Array[String] = {
    val textSplit = text.split(' ')
    println("textSplit length: " + textSplit.length)
    var toReturn:Array[String] = Array()
      for (i <- 0 until textSplit.length) {
        if (textSplit(i) == word) {
          println("found a matching word")
          if (i < textSplit.length - 1) toReturn :+= textSplit(i + 1)
          if (i > 0) toReturn :+= textSplit(i - 1)
        }
      }
      toReturn
  }
  def readFile(author: String):(String, String) = {
    val text = Source.fromFile("AuthorFiles/" + author.replace(' ', '_')).getLines.toList 
    (text(0), text(1)) // (Title, Text)
  }
  /**
   * Write to an author file. Should be an entire text with a title.
   */
  def writeFile(toWrite: String) {}
  
}

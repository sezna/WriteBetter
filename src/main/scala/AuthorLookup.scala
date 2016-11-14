import scala.io.Source

object AuthorLookup {
  def main(args: Array[String]) {
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
  def findUsage(beforeWord: String, word: String, afterWord: String, author: String) {
    val text = readFile(author).split(' ')
    for (i <- 0 until text.length) {
      if (text(i) == word && i > 0 && text(i - 1) == beforeWord) { /* do something */ }
      if (text(i) == word && text(i + 1) == afterWord) { /* do something */ }
    }

  }
  def wordLookup(word: String, text: String): Array[String] = {
    val textSplit = text.split(' ')
    var toReturn: Array[String] = Array()
    for (i <- 0 until textSplit.length) {
      if (textSplit(i) == word) {
        if (i < textSplit.length - 1) toReturn :+= textSplit(i + 1)
        if (i > 0) toReturn :+= textSplit(i - 1)
      }
    }
    toReturn
  }
  def readFile(author: String): (String, String) = {
    /* TODO make this read multiple books and return them in an array instead of a single tuple */
    val text = Source.fromFile("AuthorFiles/" + author.replace(' ', '_')).getLines.toList
    (text(0), text(1)) // (Title, Text)
  }
  /**
   * Write to an author file. Should be an entire text with a title.
   */
  def writeFile(toWrite: String) {}

}

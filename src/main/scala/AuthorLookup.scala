package WriteBetter
import scala.io.Source

class AuthorLookup {

  /**
   * Read an author file. Input is an author name and a word you are looking for.
   *
   * TBD: look up word here or return some data structure that is handled in another function?
   */
  def findUsage(beforeWord: String, afterWord: String, author: String): Array[(String, Double, String)] = { // (word, match %, context sentence)
    // TODO context sentences
    val text = readFile(author)._2.split(' ')
    var toReturn: Array[(String, Double, String)] = Array()
    for (i <- 0 until text.length) {
      if (text(i) == beforeWord && i < text.length - 1 && !toReturn.contains(text(i + 1), 1.0, "") && !toReturn.contains(text(i + 1), 0.5, "")) {
        if (i < text.length + 2 && text(i + 2) == afterWord) {
          // case that beforeWord and afterWord are matched
          toReturn :+= (text(i + 1), 1.0, "")
        }
        // case that only beforeWord is matched
        toReturn :+= (text(i + 1), 0.5, "")
      }
      if (i > 2 && text(i) == afterWord && text(i - 2) != beforeWord && !toReturn.contains(text(i - 1), 1.0, "") && !toReturn.contains(text(i - 1), 0.5, "")) {
        // case that only afterWord is matched
        toReturn :+= (text(i - 1), 0.5, "")
      }
    }
    toReturn
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

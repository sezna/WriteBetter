package WriteBetter
import WriteBetter._
import scala.io.Source


/**
 * This object is used to access the BigHugeLabs thesaurus API.
 */
class Thesaurus {
val MINWORDLENGTH = 4
  def getSynonyms(inputWord: String, inputPart: String = ""): Array[String] = {
    if (inputWord.length < MINWORDLENGTH) { return Array[String]() }
    val entry = getThesaurusEntry(inputWord)
    var toReturn: Array[String] = Array()
    for (line <- entry) {
      val splitLine = line.split('|')
      val (part, designation, wordWithPunct) = (splitLine(0), splitLine(1), splitLine(2))
      val word = wordWithPunct
      if (designation == "syn") {
        if (inputPart != "") {
          if (inputPart == part) toReturn :+= word
        } else toReturn :+= word
      }
    }
    toReturn
  }
  def getThesaurusEntry(word: String): Array[String] = {
    var toReturn:Array[String] = Array()
    try {
    toReturn = Source.fromURL("http://words.bighugelabs.com/api/2/9a0d1e46117e2bdb3bf6e1a1568faa3e/" + word + "/utf-8").mkString.split("\n")
    }
    catch {
      case e: Exception => {} 
    }
    toReturn
  }
}

import scala.io.Source
/**
 * This object is used to access the BigHugeLabs thesaurus API.
 */
object Thesaurus {
  def main(args: Array[String]) {
    println("The thesaurus is running")
  }
  def getSynonyms(inputWord: String, inputPart: String = ""): Array[String] = {
    val entry = getThesaurusEntry(inputWord)
    var toReturn: Array[String] = Array()
    for (line <- entry) {
      val splitLine = line.split('|')
      val (part, designation, word) = (splitLine(0), splitLine(1), splitLine(2))
      if (designation == "syn") {
        if (inputPart != "") {
          if (inputPart == part) toReturn :+= word
        } else toReturn :+= word
      }
    }
    toReturn
  }
  def getThesaurusEntry(word: String): Array[String] = {
    Source.fromURL("http://words.bighugelabs.com/api/2/9a0d1e46117e2bdb3bf6e1a1568faa3e/" + word + "/utf-8").mkString.split("\n")
  }
}

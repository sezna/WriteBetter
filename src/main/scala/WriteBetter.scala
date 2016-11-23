package WriteBetter

// TODO
// I am getting the same word "test" with different results from the thesaurus.

object Main extends App {
  val text = "This is a test. I want to test out this function so I would like to use a thesaurus to do this. A tall cat. A lively dog. A purple man. A tall man.".replaceAll("""[\p{Punct}]""", "").split(' ').toArray
  println("real main function")
  val thesaurus = new Thesaurus()
  val authorlookup = new AuthorLookup()
  var substitutions:Array[(String, Array[String])] = Array()
  for (i <- 1 until text.length - 1) {
    var candidates: Array[String] = Array()
    val synonyms = thesaurus.getSynonyms(text(i))
    val usages = authorlookup.findUsage(text(i - 1), text(i + 1), "Charles Dickens")
    for (usage <- usages) {
      if (synonyms.contains(usage._1)) {
        candidates :+= usage._1
      }
    }
  substitutions :+= (text(i), candidates)
  } 
  def finalprint(word: String, subs: Array[String]) {
    if (subs.length > 0) {
    println("Perhaps you want to substitute " + word + " for:")
    subs.foreach(println(_))
    }
  }
  substitutions.foreach(x => finalprint(x._1, x._2))
}

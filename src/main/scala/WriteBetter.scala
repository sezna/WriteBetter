package WriteBetter

// TODO
// I am getting the same word "test" with different results from the thesaurus.

object Main extends App {
  val text = "This is a test. I want to test out this function so I would like to use a thesaurus to do this. A tall cat. A lively dog. A purple man. A tall man.".replaceAll("""[\p{Punct}]""", "").split(' ').toArray.distinct
  val thesaurus = new Thesaurus()
  val authorlookup = new AuthorLookup()
  for (i <- 1 until text.length - 1) {
    val synonyms = thesaurus.getSynonyms(text(i))
		// usages is an array of (replacement word, match percentage, context string)
    val usages = authorlookup.findUsage(text(i - 1), text(i + 1), "Charles Dickens")
		var toprint:Array[String] = Array()	
    for (usage <- usages) {
      if (synonyms.contains(usage._1)) {
				toprint :+= (usage._1 + "(" + usage._2 + "): " + usage._3)
      }
    }
		if (toprint.length > 0) {
			println("Perhaps you want to substitute \"" + text(i) + "\" for: ")
			toprint.foreach(println(_))	
		}
  } 
}

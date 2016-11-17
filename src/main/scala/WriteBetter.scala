package WriteBetter

object Main extends App {
  println("real main function")
  val thesaurus = new Thesaurus()
  val syns = thesaurus.getSynonyms("tall")
  syns.foreach(println(_))
  val authorlookup = new AuthorLookup()
  val usages = authorlookup.findUsage("you", "a", "Charles Dickens")
  usages.foreach(x => println(x._1))
}

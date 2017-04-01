package umls

import java.io.File

import org.scalatest._
import Matchers._

class UmlsTagger2Spec {

  it should "testGetFull" in {
    val tagger = new UmlsTagger2("http://localhost:8983/solr")
    val phrases = List("Lung Cancer", "Heart Attack", "Diabetes")
    phrases.foreach(phrase => {
      Console.println()
      Console.println("Query: %s".format(phrase))
      val suggestions = tagger.select(phrase)
      suggestions match {
        case Some(suggestion) => {
          Console.println(tagger.formatSuggestion(suggestion))
          Assert.assertNotNull(suggestion.cui)
        }
        case None =>
          Assert.fail("No results for [%s]".format(phrase))
      }
    })
  }
  
  it should "testGetPartial" in {
    val tagger = new UmlsTagger2("http://localhost:8983/solr")
    val phrases = List(
        "Heart Attack and diabetes",
        "carcinoma (small-cell) of lung",
        "asthma side effects")
    phrases.foreach(phrase => {
      Console.println()
      Console.println("Query: %s".format(phrase))
      val suggestions = tagger.tag(phrase)
      suggestions match {
        case Some(psuggs) => {
          psuggs.foreach(psugg => {
            Console.println(tagger.formatSuggestion(psugg))    
          })
          Assert.assertNotNull(psuggs)
        }
        case None =>
          Assert.fail("No results for [%s]".format(phrase))
      }
    })
  }
  
  it should "testAnnotateConcepts" in {
    val tagger = new UmlsTagger2("http://localhost:8983/solr")
    val phrases = List("Lung Cancer", 
        "Heart Attack", 
        "Diabetes",
        "Heart Attack and diabetes",
        "carcinoma (small-cell) of lung",
        "asthma side effects"
    )
    phrases.foreach(phrase => {
      Console.println()
      Console.println("Query: %s".format(phrase))
      val suggestions = tagger.annotateConcepts(phrase)
      suggestions.foreach(suggestion => {
        Console.println(tagger.formatSuggestion(suggestion))
      })
    })
  }
}


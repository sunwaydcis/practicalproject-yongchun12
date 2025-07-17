package ch.makery.address

import ch.makery.address.model.Person
import javafx.fxml.FXMLLoader
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes.*
import javafx.scene as jfxs
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer

object MainApp extends JFXApp3:

  //Window Root Pane
  var roots: Option[scalafx.scene.layout.BorderPane] = None
  // ... AFTER THE OTHER VARIABLES ...

  /**
   * The data as an observable list of Persons.
   */
  val personData = new ObservableBuffer[Person]()

  /**
   * Constructor
   */
  // Data
  personData += new Person("Hans", "Muster")
  personData += new Person("Ruth", "Mueller")
  personData += new Person("Heinz", "Kurz")
  personData += new Person("Cornelia", "Meier")
  personData += new Person("Werner", "Meyer")
  personData += new Person("Lydia", "Kunz")
  personData += new Person("Anna", "Best")
  personData += new Person("Stefan", "Meier")
  personData += new Person("Martin", "Mueller")

  // ... THE REST OF THE CLASS ...

  override def start(): Unit =

    // transform path of RootLayout.fxml to URI for resource location.
    val rootResource = getClass.getResource("view/RootLayout.fxml")

    // initialize the loader object.
    val loader = new FXMLLoader(rootResource)

    // Load root layout from fxml file.
    loader.load()

    // retrieve the root component BorderPane from the FXML
    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "AddressApp"
      scene = new Scene():
        root = roots.get

    // call to display PersonOverview when app start
    showPersonOverview()

  // actions for display person overview window
  def showPersonOverview(): Unit =
    val resource = getClass.getResource("view/PersonOverview.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.get.center = roots

  val string1 = new StringProperty("Hello, ScalaFX!") //publisher
  val string2 = new StringProperty("Sunway!") //subscriber
  val string3 = new StringProperty("Segi") //subscriber

  string1.onChange( (a, b, c) => {
    println("string 1 has change")
  })

  string1.onChange( (a, b, c) => {
    println("string 1 has change from " + b + " to " + c)
  })

  string1.value = "Taylor University" // change string1 value

  // Subscribe to changes in string1
  string2 <==> string1

  // subscribe to changes in string1
  string3 <== string1

  // Change string2 values
  string2.value = "Monash University"

  println(string1.value)
  println(string2.value)
  println(string3.value)

  // Or Retrieve String value
//  println(String1())

end MainApp

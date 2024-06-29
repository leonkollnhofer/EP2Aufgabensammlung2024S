# Aufgabenblatt 8

## Allgemeine Anmerkungen
Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 10.6., 11h durch `git commit` und `git push` 
abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert:

[Vector3](../src/AB8/Vector3.java),
[Body](../src/AB8/Body.java),
[HierarchicalSystem](../src/AB8/HierarchicalSystem.java),
[MultiBody](../src/AB8/MultiBody.java),
[BodyIterable](../src/AB8/BodyIterable.java),
[BodyIterator](../src/AB8/BodyIterator.java),
[SystemComparator](../src/AB8/SystemComparator.java),
[LexicographicOrderComparator](../src/AB8/LexicographicOrderComparator.java),
[DistanceComparator](../src/AB8/DistanceComparator.java),
[MassComparator](../src/AB8/MassComparator.java),
[NameComparator](../src/AB8/NameComparator.java),
[StateFileNotFoundException](../src/AB8/StateFileNotFoundException.java),
[StateFileFormatException](../src/AB8/StateFileFormatException.java),
[ReadDataUtil](../src/AB8/ReadDataUtil.java),
das Verzeichnis [states](../states) mit `txt`-Dateien,
[Simulation](../src/AB8/Simulation.java),
[SpaceDraw](../src/AB8/SpaceDraw.java),
[Test](../src/AB8/Test.java).

Vorgegebene Programmteile dürfen nur an den Stellen verändert werden, die mit TODO markiert sind.
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen eingefügt werden. Definieren Sie
keine geschachtelten oder (anonymen) inneren Klassen, also keine Klassen, die in einer anderen
Klasse, einem Objekt oder einer Methode stehen. Wenn Sie zusätzlich zu den gefragten
Klassen weitere Klassen definieren, achten Sie darauf, dass die Klassen im
Unterverzeichnis [AB8](../src/AB8) erstellt werden und als erste Zeile die Deklaration
`package AB8;` enthalten. Implementieren Sie keine unnötigen Getter-/Setter-Methoden.

## Ziel
Ziel der Aufgabe ist das Verständnis und die Anwendung der Konzepte: Java-Collections, 
Iterator als Sichtweise, Eingabe mit Validierung, Exceptions (S. 110-123).

## Beschreibung der gegebenen Dateien

- Die Klassen bzw. Interfaces [Vector3](../src/AB8/Vector3.java), [Body](../src/AB8/Body.java), 
  [MultiBody](../src/AB8/MultiBody.java), [HierarchicalSystem](../src/AB8/HierarchicalSystem.java),
  [BodyIterable](../src/AB8/BodyIterable.java), [BodyIterator](../src/AB8/BodyIterator.java),
  [SystemComparator](../src/AB8/SystemComparator.java),
  [DistanceComparator](../src/AB8/DistanceComparator.java),
  [MassComparator](../src/AB8/MassComparator.java) kennen Sie schon aus AB7. In AB8 wurden im 
  Vergleich zu AB7 in 
  [HierarchicalSystem](../src/AB8/HierarchicalSystem.java) Spezifikationen geändert, und in  
  [BodyIterator](../src/AB8/BodyIterator.java) und 
  [SystemComparator](../src/AB8/SystemComparator.java) Methoden ergänzt, was Änderungen in weiteren 
  Klassen notwendig macht. Weiters kommen zwei zusätzliche Implementierungen von 
  [SystemComparator](../src/AB8/SystemComparator.java) hinzu:
  [LexicographicOrderComparator](../src/AB8/LexicographicOrderComparator.java) und 
  [NameComparator](../src/AB8/NameComparator.java).
- [states](../states) ist ein Verzeichnis, in dem mehrere Dateien mit der Endung `.txt` 
  mitgeliefert werden. Diese enthalten Daten von je einem Himmelskörper sowie dessen Positionen und
  Geschwindigkeitsvektoren für den 22. jedes Monats von Mai 2024 bis April 2025. Die Angaben sind 
  wie gewohnt in kartesischen Koordinaten, wobei die Sonne den Urspung des Koordinatensystems 
  bildet und die Ekliptik die x-y-Ebene darstellt. Die Daten stammen von 
  [https://ssd.jpl.nasa.gov/horizons.cgi#top](https://ssd.jpl.nasa.gov/horizons.cgi#top). 
   
    **ACHTUNG**: Die Werte sind in km bzw. km/sec angegeben! 
                      
- [StateFileNotFoundException](../src/StateFileNotFoundException.java) und 
  [StateFileFormatException](../src/StateFileFormatException.java) enthalten die Definition
  von speziellen `IOException`-Klassen. Diese sollen Sie vervollständigen.
- [ReadDataUtil](../src/ReadDataUtil.java) ist eine Klasse mit einer statischen Methode zum
  Einlesen von Position- und Bewegungsvektoren von Himmelskörpern aus Dateien. 
  Diese sollen Sie vervollständigen.
- [Simulation](../src/Simulation.java) ist ein Gerüst für eine ausführbare Klasse. Hier soll
  die Simulation analog zur Klasse [AB7.Simulation](../src/AB7/Simulation.java) implementiert 
  werden, mit dem Unterschied, dass die Bewegungsdaten der Himmelskörper aus Dateien eingelesen 
  und validiert werden. Dabei auftretende Ausnahmen werden behandelt (=abgefangen).
- [Test](../src/AB8/Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer
  Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der
  Ausführung dieser Klasse alle Tests als erfolgreich ("successful") ausgegeben werden.
  Zu Testzwecken werden auch bestimmte Ausnahmen ausgelöst, welche 
  abgefangen und behandelt werden. Andere Ausnahmen sollen nicht ausgelöst werden. Sie müssen diese 
  Klasse nicht verändern, können aber eigene Testfälle hinzufügen.

## Aufgaben
1. Nutzen Sie als Vorlage für die folgenden Implementierungen Ihre Lösungen aus AB7, das 
   heißt, kopieren Sie relevante Teile aus AB7 nach AB8, wie zum Beispiel 
   [Body](../src/AB8/Body.java), [MultiBody](../src/AB8/MultiBody.java), etc..
2. Implementieren Sie [SystemComparator](../src/AB8/SystemComparator.java) in 
   [LexicographicOrderComparator](../src/AB8/LexicographicOrderComparator.java),
   [DistanceComparator](../src/AB8/DistanceComparator.java),
   [MassComparator](../src/AB8/MassComparator.java) und
   [NameComparator](../src/AB8/NameComparator.java). Beachten Sie die zusätzliche Methode 
   `thenComparing`.
3. Ändern Sie [MultiBody](../src/AB8/MultiBody.java) gemäß der geänderten Spezifikation von
   `toString()`.
4. Ändern Sie den Iterator von `HierachicalSystem`-Klassen so, dass `remove()` 
   überschrieben wird. Der Iterator kann damit Einträge in Objekten von `HierachicalSystem` 
   löschen. Achten Sie darauf, dass in bestimmten Fällen eine `java.lang.IllegalStateException` 
   ausgelöst werden soll. 
   Durch `remove()` wird die dahinterliegende Datenstruktur 
   (das [MultiBody](../src/AB8/MultiBody.java)-Objekt) geändert. Ein Iterator der Klasse 
   [Body](../src/AB8/Body.java) unterstützt die Operation `remove()` nicht (`remove()` löst eine 
   `java.lang.UnsupportedOperationException` aus).
5. Vervollständigen Sie die Definition der beiden Exceptionklassen 
   [StateFileNotFoundException](../src/AB8/StateFileNotFoundException.java) und
   [StateFileFormatException](../src/AB8/StateFileFormatException.java).
6. _Bonusaufgabe_:
   Mit der Bonusaufgabe können Sie bis zu 6 Punkte für dieses Aufgabenblatt erreichen, wobei 
   aber 4 Punkte weiterhin als Bewertungsgrundlage bestehen bleiben (d.h., Sie können durch die 
   Bonuspunkte eventuell verlorene Punkte in einem anderen Aufgabenblatt wieder wettmachen).
   1. Validierung von Eingabedaten:
      - Implementieren Sie in der Klasse [ReadDataUtil](../src/AB8/ReadDataUtil.java) die Methode 
      `readConfiguration`. Es soll ein gepufferter Stream zum Einlesen genutzt werden 
      (siehe Skriptum Seite 128). Erstellen Sie zum Testen auch Varianten der txt-Dateien mit 
      Formatfehlern.
      - Fügen Sie der Klasse `Body` bei Bedarf 
      eine Methode `setState(Vector3 position, Vector3 velocity)` zum Setzen der Position 
      und des Geschwindigkeitsvektors (`currentMovement`) des Himmelskörpers hinzu.
   2. Ausnahmebehandlung:
      In der Klasse `Simulation` sollen nun die Himmelskörper mit Daten aus den gegebenen 
      txt-Dateien initialisiert werden. Dabei sollen die meisten der Himmelskörper vorkommen, die in
      [Simulation](../src/AB8/Simulation.java) erzeugt werden. 
      Nutzen Sie Objekte von `HierarchicalSystem` und ihre Iteratoren, um die 
      Himmelskörper der Simulation zu verwalten und Untersysteme graphisch darzustellen. Zusätzlich 
      können Sie das java-Collection-Framework nutzen 
      (siehe auch [AB7.Simulation](../src/AB7/Simulation.java)).
      Ändern Sie die Klasse `Simulation` so, dass sie zwei Kommandozeilenargumente verarbeitet. 
      Das erste Argument ist ein String mit der Angabe des Pfades zum Verzeichnis, wo die 
      entsprechenden txt-Dateien (z.B. `Venus.txt`,`Mercury.txt`,`Earth.txt`) mit den Konfigurationen 
      der Himmelskörper zu finden sind. Die Dateien haben die Namen der Himmelskörper mit Endung 
      `.txt`. Das zweite Argument ist ein String mit einer Datumsangabe der Form YYYY-MMM-DD, also z.B. 
      2024-May-22, die den Tag der auszulesenden Daten (Position und Bewegungsvektor) bestimmt. 
      Die Klasse soll beim Auftreten von Problemen bei der Ausführung entsprechende Fehlermeldungen
      ausgeben und die Ausführung in bestimmten Fällen beenden. Beispiele für Aufrufe im
      Kommandozeileninterpreter mit entsprechenden Fehlermeldungen (Sie können zum Ausführen das 
      Terminal in IntelliJ nutzen oder die Programmargumente unter `Edit Configurations` angeben):
      ```
           $ javac -cp .:../lib/CodeDraw.jar AB8/*.java
           $ java -cp .:../lib/CodeDraw.jar AB8.Simulation ../states 2024-May-22
           Running simulation ...
           $ java -cp .:../lib/CodeDraw.jar AB8.Simulation ../states
           Error: wrong number of arguments.
           $ java -cp .:../lib/CodeDraw.jar AB8.Simulation ../states 2027-Dec-22
           Warning: State not available for Sun.
           Running simulation without Sun.
           Warning: State not available for Mercury.
           Running simulation without Mercury.
           ...
           $ java -cp .:../lib/CodeDraw.jar AB8.Simulation ../states-altered 2024-May-22
           Warning: File ../states-altered/Venus.txt does not have required format. 
           Running simulation without Venus.
           Warning: File ../states-altered/Mars.txt not found. 
           Running simulation without Mars.
           Running simulation ...
           $ java -cp .:../lib/CodeDraw.jar AB8.Simulation ../states -17
           Error: State has wrong format (requires YYYY-MM-DD), aborting. 
           $ java -cp .:../lib/CodeDraw.jar AB8.Simulation blah 2024-May-22
           Warning: File blah/Sun.txt not found. 
           Running simulation without Sun.
           Warning: File blah/Mercury.txt not found. 
           Running simulation without Mercury.
           ...
      ```
   
7. Freiwillige Zusatzaufgabe (ohne Bewertung):
   Ändern Sie die Klasse `Simulation` so, dass ein drittes optionales Kommandozeilenargument
   verarbeitet werden kann. Dieses gibt an, wieviele Tage simuliert werden sollen. Beispielsweise
   kann eine zweite Datumsangabe möglich sein, oder die Anzahl an Tagen. 
   Sobald dieser Zeitpunkt in der Simulation erreicht wurde, können die aktuellen Positionen der
   Himmelskörper mit den in den txt-Dateien angegebenen Positionen verglichen werden (z.B. 
   durch erneutem Aufruf von `readConfiguration` und `draw`). Wie groß sind die Abweichungen 
   der von NASA errechneten Positionen zu den Positionen, die Ihre Simulation liefert?

### Denkanstöße (ohne Bewertung)

1. Haben Sie die Methode `remove()` des Iterators so implementiert, dass der Aufruf keine 
   zusätzliche Suche nach dem zu löschenden Eintrag benötigt?
2. Entstehen beim Löschen Objekte vom Typ `MultiBody`, die nur einen Himmelskörper repräsentieren?
   Wie kann man das verhindern?
3. Wie verhalten sich die von der Methode `asOrderedList()` zurückgelieferten Listen, wenn deren 
   enthaltene Himmelskörper durch `setState` verändert werden? Werden dadurch die Himmelskörper 
   der ursprünglichen Objekte vom Typ `HierarchicalSystem` auch geändert?
4. Wie verhalten sich Ihre Iteratoren, wenn Objekte geändert werden?
5. Wie kann man durch Einfügen von Zeichen `,` und newlines (`\n`) aus den `txt`-Dateien eine 
   "fehlerhafte" Datei machen, die trotzdem von der Methode akzeptiert wird? Kann man solche 
   Probleme verhindern?

#### _Punkteaufteilung_

- Anpassung der Klassen, die [SystemComparator](../src/AB8/SystemComparator.java) implementieren: 
  1 Punkt.
- Anpassung aller relevanter Klassen, so dass Iteratoren von 
  [HierarchicalSystem](../src/AB8/HierarchicalSystem.java) die 
  Methode `remove()` implementieren: 2 Punkte.
- Anpassung von `toString()` in [MultiBody](../src/AB8/MultiBody.java): 0.5 Punkte.
- Implementierung der Exceptionklassen 
  [StateFileNotFoundException](../src/AB8/StateFileNotFoundException.java) und
  [StateFileFormatException](../src/AB8/StateFileFormatException.java): 0.5 Punkte.
- Anpassung von `readConfiguration` in [ReadDataUtil](../src/AB8/ReadDataUtil.java), 
  sowie Implementierung von [Simulation](../src/AB8/Simulation.java): 2 Bonuspunkte.

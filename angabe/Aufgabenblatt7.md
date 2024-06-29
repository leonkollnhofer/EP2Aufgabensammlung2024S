# Aufgabenblatt 6

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 3.6. 11h durch `git commit` und `push`
abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert:
[Vector3](../src/AB7/Vector3.java),
[Body](../src/AB7/Body.java),
[HierarchicalSystem](../src/AB7/HierarchicalSystem.java),
[MultiBody](../src/AB7/MultiBody.java),
[BodyIterable](../src/AB7/BodyIterable.java), 
[BodyIterator](../src/AB7/BodyIterator.java),
[SystemComparator](../src/AB7/SystemComparator.java),
[DistanceComparator](../src/AB7/DistanceComparator.java),
[MassComparator](../src/AB7/MassComparator.java),
[Simulation](../src/AB7/Simulation.java),
[SpaceDraw](../src/AB7/SpaceDraw.java),
[Test](../src/AB7/Test.java).
Vorgegebene Programmteile dürfen nur an den Stellen verändert werden, die mit TODO markiert sind.
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen eingefügt werden. Definieren Sie 
keine geschachtelten oder (anonymen) inneren Klassen, also keine Klassen, die in einer anderen
Klasse, einem Objekt oder einer Methode stehen. Wenn Sie zusätzlich zu den gefragten
Klassen weitere Klassen definieren, achten Sie darauf, dass die Klassen im
Unterverzeichnis [AB7](../src/AB7) erstellt werden und als erste Zeile die Deklaration
`package AB7;` enthalten, um Konflikte mit späteren Aufgabenblättern zu vermeiden.
Implementieren Sie keine unnötigen Getter-/Setter-Methoden.

## Ziel

Ziel der Aufgabe ist die Anwendung der Konzepte: Ausnahmen propagieren, 
Java-Collections-Framework (siehe Skriptum Seite 118-127) und geordnete Datensammlungen.

## Beschreibung der gegebenen Dateien

- [Vector3](../src/AB7/Vector3.java) ist eine vollständig gegebene Klasse, die Sie in 
  ähnlicher Form schon aus früheren Aufgabenblättern kennen.
- [SpaceDraw](../src/AB7/SpaceDraw.java) ist eine vollständig gegebene Klasse, mit der die
  Himmelskörper graphisch dargestellt werden können. Im Unterschied zu früheren Aufgabenblättern
  werden in der Simulation nun mehrere Instanzen von [SpaceDraw](../src/AB7/SpaceDraw.java) erzeugt.
- [HierarchicalSystem](../src/AB7/HierarchicalSystem.java) ist das vollständig vorgegebene 
  Interface für Systeme von Himmelskörpern (diese bleiben durch die Wirkung der Gravitation 
  in einem System zusammen, wie zum Beispiel das Sonnensystem). Ein solches System kann 
  baumförmig aus einer Hierarchie von Untersystemen aufgebaut sein. Das hierarchische System 
  kann dabei aus beliebig vielen weiteren hierarchischen Systemen bestehen. Blätter des Baums 
  bilden einzelne Himmelskörper. Zum Beispiel beinhaltet das Sonnensystem Untersysteme, wie das 
  System Erde-Mond und das System Mars-Deimos-Phobos oder das Jupitersystem mit Jupiter und 
  seinen Monden.
- [Body](../src/AB7/Body.java) ist das Gerüst für eine Klasse, die einen einzelnen Himmelskörper 
  repräsentiert. Ein einzelner Himmelskörper kann als das einfachste System betrachtet werden,
  daher implementiert [Body](../src/AB7/Body.java) das Interface 
  [HierarchicalSystem](../src/AB7/HierarchicalSystem.java).
- [MultiBody](../src/AB7/MultiBody.java) ist das Gerüst für eine Klasse, die ein System von 
  mehr als einem Himmelskörper repräsentiert. Auch diese Klasse implementiert
  [HierarchicalSystem](../src/AB7/HierarchicalSystem.java).
- [BodyIterable](../src/AB7/BodyIterable.java) ist ein vollständig vorgegebenes Interface für
  iterierbare Objekte mit Elementen vom Typ `Body`. `HierarchicalSystem` erweitert dieses Interface.
- [BodyIterator](../src/AB7/BodyIterator.java) ist ein vollständig vorgegebenes Interface
  für einen Iterator über Elemente vom Type `Body`.
- [SystemComparator](../src/AB7/SystemComparator.java) ist eine vollständig gegebenes Interface für 
  Objekte (Komparatoren), die eine Methode zur Verfügung stellen, mit der Systeme von 
  Himmelskörpern verglichen werden können. Solche Objekte repräsentieren eine Ordnungsrelation 
  für Systeme. Zum Beispiel können Systeme auf Basis ihrer Gesamtmasse verglichen werden.
- [MassComparator](../src/AB7/MassComparator.java) ist das Gerüst einer Klasse für
  Komparatoren, die Systeme auf Basis ihrer Masse vergleichen. Diese Klasse implementiert
  [SystemComparator](../src/AB7/SystemComparator.java).
- [DistanceComparator](../src/AB7/DistanceComparator.java) ist das Gerüst einer Klasse für 
  Komparatoren, die Systeme auf Basis ihrer Distanz zu einem Referenzpunkt (`Vector3`) vergleichen.
  [DistanceComparator](../src/AB7/DistanceComparator.java) implementiert 
  [SystemComparator](../src/AB7/SystemComparator.java).
- [Simulation](../src/AB7/Simulation.java) ist eine vollständig vorgegebene Klasse für die 
  Simulation des Sonnensystems.
- [Test](../src/AB7/Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer
  Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der 
  Ausführung dieser Klasse alle Tests als erfolgreich ("successful") ausgegeben werden. 
  Zu Testzwecken wird auch eine `java.util.NoSuchElementException` ausgelöst, welche abgefangen und 
  behandelt wird. Andere Ausnahmen sollten nicht ausgelöst werden. Sie müssen diese Klasse nicht 
  verändern, können aber eigene Testfälle hinzufügen.

## Aufgaben
Ihre Aufgaben sind folgende:
1. Vervollständigen Sie die gegebenen Klassen [Body](../src/AB7/Body.java), 
   [MultiBody](../src/AB7/MultiBody.java), [MassComparator](../src/AB7/MassComparator.java) und
   [DistanceComparator](../src/AB7/DistanceComparator.java) gemäß der Kommentare in den Dateien.
   Achten Sie darauf, dass Iteratoren eine `java.util.NoSuchElementException` auslösen können.
2. Aktivieren Sie in [Simulation](../src/AB7/Simulation.java) den auskommentierten Block und führen 
   Sie die Simulation aus. Sie sollten drei graphische Fenster sehen, eines zeigt das gesamte 
   Sonnensystem, das zweite den Ausschnitt vom Untersystem Erde-Mond und das dritte den Ausschnitt 
   vom Untersystem Mars-Deimos-Phobos.

Verwenden Sie dazu vorwiegend Klassen aus dem Java-Collection-Framework. Sie dürfen aber auch Arrays 
und eigene Klassen nutzen (z.B. angepasste Kopien von Klassen aus früheren Aufgabenblättern).

Hinweis: Das Interface [SystemComparator](../src/AB7/SystemComparator.java) erweitert das  
vorgefertigte Java-Interface `Comparator<HierarchicalSystem>`. Daher können Objekte von 
`SystemComparator` auch zusammen mit vorgefertigten Java-Collection-Klassen benutzt werden 
(z.B. `java.util.TreeSet<HierarchicalSystem>`).

### Denkanstöße (ohne Bewertung)
1. An welchen Stellen lassen sich Komparatoren bei der Implementierung der Lösung 
   einsetzen? Wie könnte man eine Methode `toStringByName` in `HierarchicalSystem` in den 
   entsprechenden Klassen implementieren, wenn diese die Himmelskörper nach Namen sortiert listen 
   soll.

#### _Punkteaufteilung_
- Vervollständigen von [Body](../src/AB7/Body.java): 1 Punkt
- Vervollständigen von [MultiBody](../src/AB7/MultiBody.java): 3 Punkte
- Vervollständigen von [MassComparator](../src/AB7/MassComparator.java) und 
  [DistanceComparator](../src/AB7/DistanceComparator.java) : 1 Punkte
- Gesamt: 5 Punkte


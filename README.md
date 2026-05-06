```markdown
# Simulateur de Lancement Spatial - Programme Artemis

Ce projet est une application Java en console modélisant un simulateur de lancement de fusées. Il permet de configurer un lanceur, d'y attacher une capsule et des boosters, de lui assigner une mission spatiale, et de simuler le résultat du lancement avec un archivage persistant des données en JSON natif.

## 1. Compilation et Exécution

Le projet respecte l'arborescence conventionnelle Java (`src/main/java`). Les commandes suivantes doivent être exécutées depuis un terminal ouvert à la racine exacte du projet.

**Étape 1 : Compilation**
```bash
javac -d bin -sourcepath src/main/java src/main/java/artemis/simulator/Simulator.java
```
*Cette commande va générer les fichiers bytecode dans un dossier `bin` caché à la racine.*

**Étape 2 : Exécution**
```bash
java -cp bin artemis.simulator.Simulator
```
*Note : Le fichier `launch_history.json` est généré et mis à jour automatiquement à la racine lors des lancements.*

## 2. Mission Personnelle : Starlink Satelite

En plus des missions imposées (Orbite terrestre, ISS, Lune, Mars), ce simulateur intègre une cinquième mission personnalisée : **StarlinkMission**.

**Justification de la conception :**
*   **Héritage :** La classe `StarlinkMission` hérite proprement de la classe abstraite `Mission`.
*   **Logique métier :** Cette mission modélise une opération complexe de maintenance et de déploiement du réseau Starlink. Contrairement aux déploiements automatisés classiques, ce scénario spécifique nécessite un équipage (`isCrewed = true`) pour une expédition prolongée (3 à 4 ans) en orbite moyenne (1500 km). Le coefficient de carburant de 1.1 a été choisi pour refléter l'énergie supplémentaire requise pour les manœuvres orbitales multiples et le maintien en vie de l'équipage sur cette durée. Cela force l'utilisateur à bien réfléchir à la compatibilité de sa capsule (qui doit être habitée) et aux réserves de carburant du lanceur.

## 3. Architecture et Modélisation (UML)

Le projet applique de manière rigoureuse les principes de la Programmation Orientée Objet (POO) demandés dans le cahier des charges :
*   **Héritage et Polymorphisme :** Utilisation de classes abstraites (`Launcher`, `Capsule`, `Mission`) déclinées en sous-classes concrètes. Le calcul du carburant exploite le polymorphisme.
*   **Composition :** L'entité centrale `Rocket` est composée par agrégation d'un lanceur, d'une capsule et d'une liste dynamique de boosters.
*   **Encapsulation & Exceptions :** Protection des états internes et levée d'exceptions métiers personnalisées (`InsufficientFuelException`, `PayloadExceededException`, etc.) gérées au plus près de la logique de domaine.

**Diagramme UML :**
Le diagramme de classes de l'application est disponible à la racine du dépôt sous le nom de fichier : `uml_architecture.png` *(ou .pdf)*.

## 4. Déclaration d'utilisation d'IA

Dans le cadre de ce projet académique, des outils d'Intelligence Artificielle ont été utilisés comme assistants au développement technique, dans le respect des règles d'intégrité :
*   **Cas d'usage :** Audit de l'arborescence du projet, aide à la mise en place de la sérialisation/désérialisation JSON sans librairie externe (utilisation stricte de `java.nio.file.Files`), et revue des mécanismes de levée d'exceptions (`try/catch` multiples).
*   **Garantie :** L'ensemble de la logique métier a été analysée et validée manuellement. Les concepts de polymorphisme et de composition appliqués dans le code sont maîtrisés et peuvent être expliqués, justifiés ou modifiés en direct lors de la soutenance.
```
# Dokumentasjon - Tic Tac Toe

`- [Dokumentasjon - Tic Tac Toe](#dokumentasjon---tic-tac-toe)
  - [Generelt](#generelt)
  - [Om oppgaven](#om-oppgaven)
  - [Flyt i applikasjonen (hva mapper til hva i oppgaveteksten)](#flyt-i-applikasjonen-hva-mapper-til-hva-i-oppgaveteksten)
  - [Skjermer](#skjermer)
    - [Startskjerm - skjermbilde](#startskjerm---skjermbilde)
    - [Spillskjerm - skjermbilde](#spillskjerm---skjermbilde)
    - [Statistikkskjerm - skjermbilde](#statistikkskjerm---skjermbilde)
  - [Arkitektur](#arkitektur)
    - [Fragments](#fragments)
    - [Spillogikk](#spillogikk)
  - [AI](#ai)
  - [Brukertest](#brukertest)
  - [Versjoner](#versjoner)
  - [Biblioteker](#biblioteker)
  - [Ikon](#ikon)
  - [Play store](#play-store)
  - [Skjermbilder](#skjermbilder)
    - [Bilde Startskjerm](#bilde-startskjerm)
    - [Bilde Spillskjerm](#bilde-spillskjerm)
    - [Bilde Statistikkskjerm](#bilde-statistikkskjerm)
  - [Kildeliste](#kildeliste)

## Generelt
Jeg har bygget en implementasjon av _Tic Tac Toe_, slik oppgaven beskriver.
I dette dokumetnet skal jeg beskrive loesningen min. Det vaere seg hvordan koden er strukturert,hvorfor den ser ut som den gjoer og hvorfor den treffer maalet om aa vaere en god applikasjon.

## Om oppgaven 
Oppgaven her var aa skrive en appliksasjon som implementerte _Tic Tac Toe_ ("bondesjakke"). I tillegg var det krav om foelgende: 
1. Applikasjoen skal ha en fragment-struktur
2. Applikasjonen skal gjoere bruk av lokal lagring 
3. Applikasjonen skal ha logikk for aa kunne spille alene mot telefonen

## Flyt i applikasjonen (hva mapper til hva i oppgaveteksten)
Det foerste som moeter brukeren er [startskjermen](###startskjerm). Her kan spilleren velge spillere og stoerrelse paa brettet. Naar det er gjort, gaar man videre til [spillskjermen](###spillskjerm). Det er der selve spillet foregaar. Naar spillet er slutt, faar man opp en varselboks med informasjon om spillets resultat. Varselboksen gir mulighet til aa starte paa nytt eller gaa til [statistikkskjermen](###statistikkskjerm). Statistikkskjermen viser en oversikt over samlede resultater for alle brukere. Applikasjonen gir ogsaa mulighet for aa bytte fargetema, gjennom en standard-meny i app-baren. Det er mulig aa naa statistikkskjermen herfra. 


## Skjermer 

### Startskjerm - [skjermbilde](#bilde-startskjerm)
Denne skjermen er det foerste som moeter en bruker naar appen aapnes. Her velger man to ting: hvem som skal spille og hvor stort brettet skal vaere. 

Spillerene velges gjennom nedtrekkslister("spinners"). Disse er mye brukt i Android, og de gir mulighet for ett valg. Det er akkurat det jeg oensker her. I tillegg til aa vise ordinaere spiller, gir den siste av nedtrekkslistene mulighet for aa velge "TTTBot" som motspiller. Spillerene hentes fra applikasjonens lokale ROOM-database. Dersom man ikke har lagt til noen brukere, faar man varsel om at det maa gjoeres foer man faar lov til aa spille. 

Brukere legges enkelt til ved aa trykke paa en _Floating Action Button_ ("FAB"). En FAB er egentlig ment for aa vaere knappen som utfoere hovedfunksjoen i et program[<sup>1</sup>](#1). Derfor kan man tenke seg at min bruk ikke er helt 100% korrekt, ettersom hovedfunksjonaliteten er aa starte spillet, ikke aa legge til brukere. Min egen erfaring med Android (og _Material Design_ generelt) sier at det derimot er veldig vanlig aa ha "legg til"-funksjonalitet i slike knapper, med pluss-logo. Jeg fikk heller ingen reaksjoner paa det da jeg [brukertestet](#brukertest) appen. Derfor har jeg latt det vaere. 

Hovedfunksjonaliteten gjoeres ved den store "Start Game"-knappen. Den var tydelig for alle jeg viste appen til. 

### Spillskjerm - [skjermbilde](#bilde-spillskjerm)
Her foregaar selve spillet. Spillet vises paa et kvadratisk brett i midten. Hver "rute" paa brettet starter med aa innehodle "_". For aa gjoere trekk, trykker spilleren bare der trekket skal gjoeres. 

Under og over spillet vises de to spillernavnene. Paa denne maaten kan man legge telefonen mellom seg, som med et tradisjonelt brettspill. Navnet paa spilleren som venter paa motstanderens trekk, vil vaere graatt. I utgangspunktet var dette tenkt aa vaere tydeligere (faret bakgrunn/tydeligere tekster). Jeg oppdaget at brukere synes dette var distraherende. Derfor tonet jeg det ned, og har naa kun graatt/svart som bytter. 

### Statistikkskjerm - [skjermbilde](#bilde-statistikkskjerm)
Her vises alle spillere i en tabell, med kolonner for antall seiere, tap og uavgjort-spill. Dette er ikke en side hvor brukeren skal bruke mye tid. Inntrykket mitt var at brukerene som testet appen, synes siden var morsom i kort tid. Deretter ville de vekk, tilbake til selve spillet. 


## Arkitektur

### Fragments 
Programmet bruker en fragment-arkitektur, slik som 
oppgaven spesifiserer. Fragments gir en fordel over activities her fordi de er mindre ressurskrevende aa starte opp enn activities. 
Fragments er mer fleksible enn activities var tenkt til aa vaere. De kan gjenbrukes og, dersom man oensker, kan man ha flere i samme skjerm (f.eks endring ved rotasjon). Det er ikke anbefalt at de skal vaere saa store. De skal dele opp ganske store bolker. 

Jeg bruker kun ett fragment om gangen, og har samme skjerm uavhengig av rotasjon. Derfor er det foerst og fremst kostnaden ved aa starte en fragment som jeg sparer.

![Activity to framgents](photos/diagrams/activity-to-fragment.png)

Hele appen vises i en felles activity. Dersom en ny skjerm skal vises, byttes bare fragmenten som er aktivt. Dette lar meg bruke alle fordelene som fragments gir. Activity-objektet kan naaes gjennom klassevariabler i Fragments.
```kotlin
fun replaceMainFragment(fragment: Fragment) {

    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.activity_base_frame_layout, fragment)
        .commit()
}
```

### Spillogikk
Spillogikken ligger separat fra GUI. 

![Sekvensdiagram for game-listeners](./photos/diagrams/game-listener-sequence.png)
Jegh har forsoekt aa skille logikken for spillet fra GUI. Faktisk implemetnerte jeg selve logikken i foer jeg i det hele tatt tenkte paa hvordan det skulle rendres. Problemstillingen jeg moette da jeg startet med GUI var foelgende: hvordan skal GUI si ifra til spillogikken at noe skal oppdateres og motsatt. Etter hvert kom jeg frem til loesningen som er modellert over: 

`Game.kt` har tre public klassevariable som er mutable: 

```kotlin
//Game.kt
var onFirstPlayer:  ((player: Player) -> Unit)? = null
var onSecondPlayer:  ((player: Player) -> Unit)? = null
var onGameOver:  ((result: Result) -> Unit)? = null
```

`GameFragment.kt` (som holder paa et Game-objekt) kan saa sette sine GUI-spesifikke implementasjoner paa disse: 
```kotlin
//GameFragment.kt
game.apply {

    onFirstPlayer = {

        // Gjoer GUI-spesifik ting!
    }

    onSecondPlayer = {

        // Gjoer GUI-spesifik ting!
    }
}
```

Her kom Kotlin meg til gode. Dette kunne ogsaa vaert gjort i Java, f.eks. med funksjonelle interfaces. Det ble allikevel lettere i Kotlin fordi funksjoner er "first class citizens"[<sup>2</sup>](#2). 

Logikken har vaert bygget slik at den stoetter alle dimensjone, fra start av. Dette gjorde det forholdsvis enkelt for meg aa legge inn mulighet for nettopp flere dimensjoner, ikke bare 3x3.

Selve er strukturert som et 2D-array. Logikken og strukturen rundt, kan sies aa vaere noe "over-engineered" for formaalene denne applikasjonen trengte. Med det sagt, har det gitt noen fordeler ogsaa. Logikken ble laget paa et veldig tidlig stadium i utviklingen. Derfor kunne man tenke seg at det ville vaere vanskelig aa debugge det hele, dersom det ikke var godt strukturert. Her fikk jeg igjen for arbeidet, som gjorde det lett a finne feil. Koden har ogsaa tilhoerende, automatiserte tester, som var kjekt for a se at jeg ikke oedela noe de gangene jeg gjorde endringer.  

## AI 
Akkurat som med spillogikken, har jeg laget en AI som fungerer, helt uavhengig av dimensjonen paa brettet. Stort sett har jeg gjort slik at AI-koden ikke skal gjoere noe som handler om en spesiell dimensjon. Fordelen med dette, er at den fungerer paa akurat den brettstoerrelsen som oenskes.

Ulempen er at man ikke kan dra nytte av trekk ved spesielle stoerrelser. F.eks. vil man i 3x3 ha et par trekk som er ekstra gode i starten. For aa faa med dette, har jeg med logikk kun for de to foerste trekkene som AI gjoer, ved 3x3. 

Med mindre brettet er 3x3 og det er et av de foerste trekkene, er strategien foelgende: 
* Hvis et trekk leder til at spillet er over, ta det 
* Forsoek aa bygge paa eksisterede rekker, hvis det er mulig aa vinne i rekkens retning 
* Fa en tilfeldig posisjon paa en rekke det er mulig aa vinne paa 
* Fa en tilfeldig posisjon 

Dette fungerer bra.


Algoritmen er ikke ytelsesoptimalisert. For disse formaalene, tenker jeg at det er greit, i og med at jeg ikke lar brukeren velge stoerrelse fullstendig fritt (man kan ikke ha 100x100, f.eks.).

Jeg har ogsaa maalt hastigheten. Google anbefaler at oppgaver skal kjoers i separate traader dersom de tar mer enn 16 millisekunder[<sup>3</sup>](#3). I smaa brettsoerrelser, naar jeg ikke denne grensen. Paa stoerre stoerrelser, gaar jeg derimot langt over. 

Jeg maalte tiden manuelt, med foelgende kode. 

```kotlin 
var sum = 0.toLong()
val n = 100
val before = System.currentTimeMillis()
for(i in 0..n) {

    it.selectCoordinate(board)
    val after = System.currentTimeMillis()

    val difference = after - before
    sum += difference
}

val average = sum / n
print(average)
```

Paa et tidspunkt vurderte jeg aa bruke en kjent algoritme, som MinMax eller noe tilsvarende. Allikevel landet jeg paa aa snekre sammen min egen loesning. Grunnen til det er foerst og fremst at det er morsommere. Slik jeg tolket oppgaven, forstor jeg det ogsaa slik at det var det som var oenskeli; at man skulle lage sin egen. 

## Brukertest
Jeg har hatt noen uformelle brukertester med venner og bekjente. Jeg har passet paa aa la baade "tekniske" og "ikke-tekniske" kjente. Det vil si at ogsaa testet folk som ikke er vant til aa bruke mange apper og som sjelden laerer seg aa bruke nye programmer. 

I begynnelsen hadde jeg mye tydeligere, visuell indikator paa hvilken spiller sin tur det var. Noen brukere hang seg opp i at det var distraherende, og at det burde tones ned. Det har jeg gjort i den ferdige versjonen. 

Da jeg testet hadde jeg heller ikke noen tekst som forklart de forksjellige input-feltene paa startskjermen.  Da var det noen av de ikke-teknsike brukerene som ikke skoente at det eksisterte valg de maatte ta stilling til. Dette er ogsaa lagt til i nyere versjoner. 

Appen har foerst og fremst blitt kjoert paa min egen [Moto E Play](https://www.motorola.com/us/products/moto-e-play-gen-5).

## Versjoner
I appens '.gradle'-fil staar target-versjonen paa API-nivaa 28. Dette er for aa foelge Google sitt kommende krav
om at alle alle nye apper som skal publiseres paa Play Store maa ha denne versjonen eller hoeyere[<sup>3</sup>](#4). https://developer.android.com/distribute/best-practices/develop/target-sdk

TODO: mer om minimumskrav -> hvor mange som stoettes


## Biblioteker
Jeg har valgt aa unngaa bruke tunge rammeverk. Generelt sett har jeg oensket aa holde meg paa ganske "vanilla"-nivaa.
Det var fordi jeg oensket at oppgaven min skulle passe mer mot pensum, samt at jeg ikke ville pakke bort for mange ting foer jeg forsto dem.

Room er et unntak, i og med at det kan beskrives som et rammeverk som abstraherer ganske mye for deg. Jeg oensket allikevel
aa bruke Room av to grunner:
1. Det ble brukt i undervisningen, som jeg oensket aa ligge tett opp mot.
2. Jeg har ganske god kjennskap til SQL o.l. fra tidligere kurs og fritidsprosjekter. Derfor var det ikke saa farlig at
de funksjonaliteten ble pakket inn.

Der jeg skal kjoere kode i flere traader, bruker jeg [Anko](https://github.com/Kotlin/anko) sin `doAsync`. Her kunne jeg gatt for `AsyncTask`, slik som jeg gjoer i Modulist (app nr. 2). Sistnevnte er flott fordi den gir deg en god del kontroll, med forskjellige metoder som lar deg gjoere ting underveis i kjoeringen. `AsyncTask` gir deg ogsaa en ganske eksplisitt maate a definere hva som skal skje paa (med generic-parametere osv.), som jeg har sanasen for. Allikevel gikk jeg for `doAsync`, fordi det kun skulle brukes ett sted. Dessuten ble det kort, fint og lettleselig: 
```kotlin
doAsync {

  val coordinate = botPlayer.selectCoordinate(game.board)

  uiThread {

      game.clickAt(coordinate)
      (fragment_game_grid_view.adapter as GameGridAdapter).notifyDataSetChanged()
  }
}
```

## Visuelt
Jeg har holdt meg til Material Design, og Google sine standard-komponenter. Disse er kjente for brukeren. Det er lagt opp til at brukeren skal kunne endre fargetema gjennom "options-menyen". 

Jeg har ogsaa laget et lite ikon til appen.
![startskjerm](./photos/icon.png)

## Navngivning 
TODO skriv her 

## Play store

## Skjermbilder
### Bilde Startskjerm
![startskjerm](./photos/screenshots/startskjerm.png)
### Bilde Spillskjerm
![spillskjerm](./photos/screenshots/spillskjerm.png)
### Bilde Statistikkskjerm
![statistikkskjerm](./photos/screenshots/statistikkskjerm.png)

## Kildeliste
* <span id="1">1:</span> Uspesifiert forfatter, Google. 2019. “Floating Action Buttons”. https://material.io/develop/android/components/floating-action-button/ (lastet ned 27. April 2019)
* <span id="2">2:</span> UMarcos Placona. 10 Mai 2018. “Functions are first-class citizens in Kotlin”. https://realkotlin.com/tutorials/2018-05-10-functions-are-first-class-citizens-in-kotlin/ (lastet ned 27. April 2019)
*  <span id="3">3:</span> Uspesifiert forfatter, Google. 2019. “Better performance through threading”. https://developer.android.com/topic/performance/threads (lastet ned 27. April 2019)
* <span id="4">4:</span> Uspesifiert forfatter, Google. 2019. “Meet Google Play's target API level requirement.” Google, March 8, 2017. https://developer.android.com/distribute/best-practices/develop/target-sdk (lastet ned 11. April 2019)

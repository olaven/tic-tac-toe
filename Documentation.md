# Dokumentasjon - Tic Tac Toe

- [Dokumentasjon - Tic Tac Toe](#dokumentasjon---tic-tac-toe)
  - [Generelt](#generelt)
  - [Om oppgaven](#om-oppgaven)
  - [Flyt i applikasjonen (hva mapper til hva i oppgaveteksten)](#flyt-i-applikasjonen-hva-mapper-til-hva-i-oppgaveteksten)
  - [Skjermer](#skjermer)
    - [Startskjerm](#startskjerm)
    - [Spillskjerm](#spillskjerm)
    - [Statistikkskjerm](#statistikkskjerm)
  - [Versjoner](#versjoner)
  - [Biblioteker](#biblioteker)
  - [Screenshots TODO](#screenshots-todo)
  - [Arkitektur](#arkitektur)
    - [Fragments](#fragments)
    - [Spill-logikk](#spill-logikk)
  - [Skjermbilder](#skjermbilder)
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

### Startskjerm 
TODO: bildelink 
Denne skjermen er det foerste som moeter en bruker naar appen aapnes. Her velger man to ting: hvem som skal spille og hvor stort brettet skal vaere. 

Spillerene velges gjennom nedtrekkslister("spinners"). Disse er mye brukt i Android, og de gir mulighet for ett valg. Det er akkurat det jeg oensker her. I tillegg til aa vise ordinaere spiller, gir den siste av nedtrekkslistene mulighet for aa velge "TTTBot" som motspiller. Spillerene hentes fra applikasjonens lokale ROOM-database. Dersom man ikke har lagt til noen brukere, faar man varsel om at det maa gjoeres foer man faar lov til aa spille. 

Brukere legges enkelt til ved aa trykke paa en _Floating Action Button_ ("FAB"). En FAB er egentlig ment for aa vaere knappen som utfoere hovedfunksjoen i et program[<sup>1</sup>](#1). Derfor kan man tenke seg at min bruk ikke er helt 100% korrekt, ettersom hovedfunksjonaliteten er aa starte spillet, ikke aa legge til brukere. Min egen erfaring med Android (og _Material Design_ generelt) sier at det derimot er veldig vanlig aa ha "legg til"-funksjonalitet i slike knapper, med pluss-logo. Jeg fikk heller ingen reaksjoner paa det da jeg [brukertestet](#brukertest) appen. Derfor har jeg latt det vaere. 

Hovedfunksjonaliteten gjoeres ved den store "Start Game"-knappen. Den var tydelig for alle jeg viste appen til. 

### Spillskjerm 
TODO: bildelink 
Her foregaar selve spillet. Spillet vises paa et kvadratisk brett i midten. Hver "rute" paa brettet starter med aa innehodle "_". For aa gjoere trekk, trykker spilleren bare der trekket skal gjoeres. 

Under og over spillet vises de to spillernavnene. Paa denne maaten kan man legge telefonen mellom seg, som med et tradisjonelt brettspill. Navnet paa spilleren som venter paa motstanderens trekk, vil vaere graatt. 

### Statistikkskjerm 
TODO: bildelink 


## Arkitektur
### Fragments 
Programmet bruker en fin og kjapp fragment-arkitektur, slik som 
oppgaven spesifiserer. 
### Spill-logikk
var lur fordi den lot meg sette andre dimensjoner. Den var ogsaa lett a enhetsteste. kunne gjort annerledes, fordeler ulemper 

## Brukertest

## Versjoner
I appens '.gradle'-fil staar target-versjonen paa API-nivaa 28. Dette er for aa foelge Google sitt kommende krav
om at alle alle nye apper som skal publiseres paa Play Store maa ha denne versjonen eller hoeyere[<sup>2</sup>](#2). https://developer.android.com/distribute/best-practices/develop/target-sdk

Minimumsversjonen er satt til SPOER TM

Minimuns versjonen stoetter X mange

Dersom jeg skulle pushet versjonen enda lavere, ville jeg kommet i konflikt med b.la. support-bibliotekene jeg bruker,
og jeg konkluderte med at applikasjonen stoettet mer enn bredt nok uten dette. Faktisk anslaar Android Studio at stoetten ligger paa rundt hundre prosent. 


## Biblioteker
Jeg har valgt aa unngaa bruke tunge rammeverk. Generelt sett har jeg oensket aa holde meg paa ganske "vanilla"-nivaa.
Det var fordi jeg oensket at oppgaven min skulle passe mer mot pensum, samt at jeg ikke ville pakke bort for mange ting
foer jeg forsto dem.

Room er et unntak, i og med at det kan beskrives som et rammeverk som abstraherer ganske mye for deg. Jeg oensket allikevel
aa bruke Room av to grunner:
1. Det ble brukt i undervisningen, som jeg oensket aa ligge tett opp mot.
2. Jeg har ganske god kjennskap til SQL o.l. fra tidligere kurs og fritidsprosjekter. Derfor var det ikke saa farlig at
de funksjonaliteten ble pakket inn.

TODO: Gjennomgang av alle bibliotek


## Skjermbilder

## Kildeliste
* <span id="1">1:</span> Uspesifiert forfatter, Google. 2019. “Floating Action Buttons”. https://material.io/develop/android/components/floating-action-button/ (lastet ned 27. April 2019)
* <span id="2">2:</span> Uspesifiert forfatter, Google. 2019. “Meet Google Play's target API level requirement.” Google, March 8, 2017. https://developer.android.com/distribute/best-practices/develop/target-sdk (lastet ned 11. April 2019)

[1]: "her er det noe" 
# Dokumentasjon - Tic Tac Toe
## Generelt
Jeg har bygget en implementasjon av _Tic Tac Toe_, slik oppgaven beskriver.
I dette dokumetnet skal jeg beskrive loesningen min. Det vaere seg hvordan koden er strukturert,
hvorfor den ser ut som den gjoer og hvorfor den treffer maalet om aa vaere en god applikasjon.

## Versioner
I appens '.gradle'-fil staar target-versionen paa API-nivaa 28. Dette er for aa foelge Google sitt kommende krav
om at alle alle nye apper som skal publiseres paa Play Store maa ha denne versjonen eller hoeyere[^1]. https://developer.android.com/distribute/best-practices/develop/target-sdk

Minimumsversionen er satt til SPOER TM

Minimuns versjonen stoetter X mange

Dersom jeg skulle pushet versjonen enda lavere, ville jeg kommet i konflikt med b.la. support-bibliotekene jeg bruker,
og jeg konkluderte med at applikasjonen stoettet mer enn bredt nok uten dette. Faktisk anslaar Android Studio at stoetten ligger paa rundt hundre prosent. 


## Traader
TODO: skriv om database og traader

AI-koden var noe jeg vurderte aa kjoere i separate traader. Google skriver i guidene sine[^2] at brukeren kan oppleve at
UI er "sluggish" dersom arbeid ikke blir ferdig paa 16 millisekunder. Jeg maalte tiden det tok aa velge et move i AI-koden.
Stort sett holdt den seg rundt 5 millisekunder paa min _Motorola E5 Play_. Dette er ikke en veldig kraftig telefon[^3], og jeg
velger derfor aa regne med at de fleste brukeren av applikasjonen vil ha kjappere eller like kjapp telefon. Paa bakgrunn
av dette, har jeg valgt at aa ikke implementere traader her.

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

## Arkitektur
### Fragments 
Programmet bruker en fin og kjapp fragment-arkitektur, slik som 
oppgaven spesifiserer. 
### Spill-logikk
var lur fordi den lot meg sette andre dimensjoner. Den var ogsaa lett a enhetsteste. kunne gjort annerledes, fordeler ulemper 

## Skjermbilder

## Kildeliste
TODO: Fiks chicago style
* Manjoo, Farhad. 2017. “Snap Makes a Bet on the Cultural Supremacy of the Camera.” New York Times, March 8, 2017. https://www.nytimes.com/2017/03/08/technology/snap-makes-a-bet-on-the-cultural-supremacy-of-the-camera.html.
* [^1] Uspesifiert forfatter, Google. 2019. “Meet Google Play's target API level requirement.” New York Times, March 8, 2017. https://developer.android.com/distribute/best-practices/develop/target-sdk
* [^2] https://developer.android.com/topic/performance/threads
* [^3] https://www.motorola.com/us/products/moto-e-play-gen-5#specs
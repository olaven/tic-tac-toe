# tic-tac-toe
An implementation of Tic Tac Toe. Written as part of exam in Android Programming

## Todo (by no means final)
### GUI 
- [X] Start screen where players can enter their name
- [X] Board is a grid-layout (inside main layout)
- [X] Board should update on changes!
- [X] Start screen should use spinners
- [X] Player whose turn it is should light up
- [ ] Grid should display icons, not textviews (perhaps even just a button, more semantically correct?)
- [ ] Option to play agains AI
- [ ] Previous players as options on start screen?
- [ ] The side of player whose turn it is lights up
- [ ] Resultscreen with game-history
- [ ] Change THEME (dark mode++ etc + Fun modes like Streken-mode, Wood-mode, morsomme theme-modes)
- [ ] Icon
### Game 
- [X] Representation of a board with squares
- [X] Being able to modify board
- [X] Concept of player (superclass for both AI and human)
- [X] Board should only be AxA, not AxB
- [X] Game class modelling the game
- [X] Choose users
- [ ] lagre spillstate
- [ ] Determine if game is over or not
- [ ] Change board default board dimension
- [ ] Change n-goal (has to be <= board dimension)
- [ ] Implement solo play (random moves before AI) 
### Other
- [X] store users in resources
- [X] Familiarize with UI testing
- [ ] brukerinput på start må lagres midlertidig ved onPause og vises igjen ved onResume
- [ ] Finne bruk av public directories (Photos, Downloads etc.) -> kanskje profilbilder?
- [ ] clean up in x/y, row/column getting mixed! x = column, y = row!
- [ ] Use life-cycle-methods correctly!
- [ ] Determine android version
- [ ] Records of previous games stored in local db
- [ ] Changing default dimension in settings
- [ ] Class diagram
- [ ] Publish to Play store
- [ ] Popup when the AI has defeated 10 opponents (exam description)
- [ ] clean gradle of unused dependencies
### AI
- [X] AI should make some move
- [ ] Figure out approach 
- [ ] How to score different fields (i.e. what is biggest threat)
- [ ] calculation should probably not run in main thread
### Testing 
- [X] use [assertJ](http://joel-costigliola.github.io/assertj)
- [X] set up travis
- [ ] vurder https://google.github.io/truth/ fremfor assertj
- [ ] Skill på ulike testkategorier som beskrevet https://developer.android.com/training/testing/fundamentals
- [ ] @LargeTest og lignende.
- [ ] "70 percent small, 20 percent medium, and 10 percent large." - https://developer.android.com/training/testing/fundamentals
- [ ] set up tests for older phones / OS versions
- [ ] Unit tests 
- [ ] Android GUI tests
- [ ] Test on different images on travis
- [ ] >90% line coverage
### Documentation
- [ ] Hvilken versjon prosjektet er bygget på (bruk statistikk fra statistia)
- [ ] Bare veldig enkle ting (max sharedprefs) onResume/-pause. OnStop/Start resten
- [ ] Metoder for restoring av state: (onSaveInstanceState / onRestoreInstanceState og onCreate https://stackoverflow.com/questions/12683779/are-oncreate-and-onrestoreinstancestate-mutually-exclusive)
- [ ] state i start-activity https://developer.android.com/topic/libraries/architecture/saving-states#options_for_preserving_ui_state
- [ ] bruk sharedprefs for innstillinger. Hvorfor? https://stackoverflow.com/questions/24822101/when-to-use-getsharedpreferences-vs-savedinstancestate
- [ ] Convesions
    - [ ] lyttere i kode, ikke XML -> ryddigere
    - [ ] bruke kotlin sin mapping (navn?) fremfor å bruke findviewbyid
- [ ] Named parameters, where and why?
- [ ] Multithreading 
  - [ ] Hvor 
  - [ ] Hvorfor viktig (ytelse, UI-lag, mobil osv.)
- [ ] Hvorfor lagres data på måten det gjøres (shared preferences, fil, database)
- [ ] Håndtering av API-nøkler
- [ ] bruk testdefinisjoner fra https://developer.android.com/training/testing/fundamentals
- [ ] "70 percent small, 20 percent medium, and 10 percent large." - https://developer.android.com/training/testing/fundamentals
- [ ] V-modell
- [ ] Reflekter rundt hvorfor assertj/https://google.github.io/truth/ ble valgt
- [ ] Målsetninger om hvorfor man tester og hvorfor på forskjellig nivå
    - [ ] Bottom up?
    - [ ] GOogles filosofi (video fra undervisningsforum)
- [ ] Gå over dokumentasajonsliste i modulist og agenda
- [ ] Use of comments
- [ ] testnavn
- [ ] Multithreading
- [ ] Comment the use of CanCopy -> krøkkete å vite om noe er data class -> unngå bug hvor alle i grid har samme refereranse
- [ ] Refactor alltid når det er noe som føles mer riktig -> Råd fra tidligere undervisning (Finn artikler om det samme)
- [ ] om bruk av "="-funksjoner (slå opp term)


## Naming (by no means final)
### IDs
* Forsøke å separere spill og GUI
activity/fragment/etc_type_name
i.e.
activity_main_button_sign_in

## Notes on AI / Game rules

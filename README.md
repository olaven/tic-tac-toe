# tic-tac-toe
An implementation of Tic Tac Toe. Written as part of exam in Android Programming

## Todo (by no means final)
### GUI 
- [X] Start screen where players can enter their name
- [X] Board is a grid-layout (inside main layout)
- [X] Board should update on changes!
- [X] Start screen should use spinners
- [ ] Player whose turn it is should light up
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
- [ ] Determine if game is over or not
- [ ] Change board default board dimension
- [ ] Change n-goal (has to be <= board dimension)
- [ ] Implement solo play (random moves before AI) 
### Other
- [X] store users in resources
- [ ] brukerinput på start må lagres midlertidig ved onPause og vises igjen ved onResume
- [ ] clean up in x/y, row/column getting mixed! x = column, y = row!
- [ ] Use life-cycle-methods correctly!
- [ ] Determine android version
- [ ] Records of previous games stored in local db
- [ ] Changing default dimension in settings
- [ ] Class diagram
- [ ] Publish to Play store
- [ ] Popup when the AI has defeated 10 opponents (exam description)
### AI 
- [ ] Figure out approach 
- [ ] How to score different fields (i.e. what is biggest threat)
- [ ] calculation should probably not run in main thread
### Testing 
- [X] use [assertJ](http://joel-costigliola.github.io/assertj)
- [ ] set up travis or similar
- [ ] set up tests for older phones / OS versions
- [ ] Unit tests 
- [ ] Android GUI tests 
- [ ] >90% line coverage
### Documentation
- [ ] Convesions
    - [ ] lyttere i kode, ikke XML -> ryddigere
    - [ ] bruke kotlin sin mapping (navn?) fremfor å bruke findviewbyid
- [ ] Named parameters, where and why?
- [ ] Use of comments
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

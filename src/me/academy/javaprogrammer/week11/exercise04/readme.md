Exercise04
- Refactor code done in Exercise03 and add following features:
  - user is presented with the list of horses participating in the race
  and selects a horse to bet on
  - during the race a standings table is displayed and updating in real-time
  - when the race has finished a message is displayed 
    informing if the horse selected won or not

Requirements
- define `HorseListener` interface with methods for handling `HorseEvent`s
triggered during the race
- try to build the application following Observer pattern
    
Result of current implementation:
![horse race bet dialog](doc/horse_race_bet_dialog.png)

![horse race start](doc/horse_race_start.png)

![horse race progress](doc/horse_race_progress.png)

![horse race end lose](doc/horse_race_end_lose.png)

![horse race end win](doc/horse_race_end_win.png)

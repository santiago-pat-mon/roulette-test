# roulette
This project is a API REST for a roulette, create rouletts, open and close rouletts, determine winners and you awards, create bets whit number between 0 and 36 or color red o black and a bet until 10k units.

Technology necesary for this API:
  - OpenJDK: >=11.0.10
  - Redis: >= 5.0.7
  
For deploy API in your PC is necesary what Redis server is turn-on and execute ./target/roulette-test-0.0.1.jar.

Entrypoints: 
| URL | Function |
|:---:|---|
| ***{hostname}*:8080/roulette** | List all roulettes in the API whit your status and bet's. |
| ***{hostname}*:8080/roulette/create** | Create a roulette whit status close. |
| ***{hostname}*:8080/roulette/open/_{idRoulette}_** | Open a roulette specify. |
| ***{hostname}*:8080/roulette/_{idRoulette}_/createBet** | Create a bet whit numbers between 0 and 36, color red or black and a bet until 10k unit's. |
| ***{hostname}*:8080/roulette/close/_{idRoulette}_** | Close roulette specify, determine winners and lossers|
 

# Overview
As a person who knows nothing about Kotlin or chess, this code has been a real challenge. 

I started working on this about two weeks ago now, and I wanted to learn Kotlin, so I thought that doing a chess game will be fun. It would've been more fun IF I would've actually known how to play chess.
Although hard, I think with a little more time in my hands, this would've been a great multi-player game.
It started without players, and letters. Just to later realize having turns would make more fun, so I started to implement that, and change the letters to actual [chess pieces](https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode)

[Video explaining my code](https://www.loom.com/share/66b4234ed5e84e5eada3adaab6e124ff?sid=e666ebd1-c6c9-4b42-bf9d-d183ac4521bf)

# Development Environment

I worked with IntelliJ for this code. It was actually my first time using this development environment, but it was very straight forward.

Some of the libraries I used were java.util.* for the "Scanner" class (which job is to take the user input for the moves) of course, I could also just do a readline() that would've also done the job, but I wanted to learn everything I could from Kotlin in this project.
The other library I used for this project was the import kotlin.math.abs, the "abs" function is used to get an absolute value of a number. In this case I used for calculating the value between row and column in the "isValidMove" function.

# Useful Websites

{Make a list of websites that you found helpful in this project}

- [Golden Thumb](https://www.youtube.com/@goldthumb/shorts) - This is a programmer in toronto who did a chess game in Kotlin as well, I used him for some ideas, but he has a ton of 10 min videos so I watched some,not all to get ideas for my code.
- [How to actually play Chess](https://www.chess.com/learn-how-to-play-chess)

# Future Work

- FIX BUG: switch in between players so its not only player one playing.
- Implement winning rules
- implement points
package gt.com.jpvr.joker;

import java.util.Random;

public class JokeProvider {

    private String[] jokes = new String[]{
            "What's the best thing about Switzerland?\nI don't know, but the flag is a big plus.",
            "I invented a new word!\nPlagiarism!",
            "Did you hear about the mathematician who's afraid of negative numbers?\nHe'll stop at nothing to avoid them.",
            "Why do we tell actors to \"break a leg?\"\nBecause every play has a cast.",
            "Did you hear about the actor who fell through the floorboards?\nHe was just going through a stage.",
            "Helvetica and Times New Roman walk into a bar. The bartender shouts:\n\"Get out of here! We don't serve your type!\"",
            "Yesterday I saw a guy spill all his Scrabble letters on the road.\nI asked him, \"What's the word on the street?\"",
            "Once my dog ate all the Scrabble tiles.\nFor days he kept leaving little messages around the house.",
            "Knock! Knock! - Who's there? - Control Freak.\nCon… Okay, now you say, \"Control Freak who?\"",
            "Hear about the new restaurant called Karma?\nThere's no menu: You get what you deserve.",
            "A woman in labor suddenly shouted, \"Shouldn't! Wouldn't! Couldn't! Didn't! Can't!\"\n\"Don't worry,\" said the doc. \"Those are just contractions.\"",
            "A bear walks into a bar and says, \"Give me a whiskey and … cola.\" \"Why the big pause?\" asks the bartender.\nThe bear shrugged. \"I'm not sure; I was born with them.\"",
            "Did you hear about the claustrophobic astronaut?\nHe just needed a little space.",
            "Why don't scientists trust atoms?\nBecause they make up everything.",
            "Why did the chicken go to the séance?\nTo get to the other side.",
            "Where are average things manufactured?\nThe satisfactory.",
            "How do you drown a hipster?\nThrow him in the mainstream.",
            "What sits at the bottom of the sea and twitches?\nA nervous wreck.",
            "What does a nosy pepper do?\nGets jalapeño business!",
            "How does Moses make tea?\nHe brews.",
            "Why can't you explain puns to kleptomaniacs?\nThey always take things literally.",
            "How do you keep a bagel from getting away?\nPut lox on it.",
            "A man tells his doctor, \"Doc, help me. I'm addicted to Twitter!\"\nThe doctor replies, \"Sorry, I don't follow you …\"",
            "What kind of exercise do lazy people do?\nDiddly-squats.",
            "Why don't Calculus majors throw house parties?\nBecause you should never drink and derive.",
            "What do you call a parade of rabbits hopping backwards?\nA receding hare-line.",
            "What does Charles Dickens keep in his spice rack?\nThe best of thymes, the worst of thymes.",
            "What's the different between a cat and a comma?\nA cat has claws at the end of paws; A comma is a pause at the end of a clause.",
            "Why should the number 288 never be mentioned?\nIt's two gross.",
            "What did the Tin Man say when he got run over by a steamroller?\n\"Curses! Foil again!\"",
            "What did the bald man exclaim when he received a comb for a present?\nThanks— I'll never part with it!"
    };

    public String getRandomJokeStr() {
        return jokes[new Random().nextInt(jokes.length)];
    }

//    public Joke getRandomJoke() {
//        String jokeStr = getRandomJokeStr();
//
//        int index = jokeStr.indexOf("\n");
//
//        String setup = jokeStr.substring(0, index);
//        String punchline = jokeStr.substring(index + 1);
//
//        return new Joke(setup, punchline);
//    }
}

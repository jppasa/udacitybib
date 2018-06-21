package gt.com.jpvr.joker;

public class Joke {
    private String setup;
    private String punchline;

    public Joke(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public String asString() {
        return toString();
    }

    @Override
    public String toString() {
        return setup + "\n" + punchline;
    }
}

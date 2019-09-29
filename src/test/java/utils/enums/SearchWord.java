package utils.enums;

public enum SearchWord {
    DRESS("dress");

    private String searchWord;

    SearchWord(String mark) {
        this.searchWord = mark;
    }

    @Override
    public String toString() {
        return searchWord;
    }
}

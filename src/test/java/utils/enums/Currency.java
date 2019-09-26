package utils.enums;

public enum Currency {
    USD("$"),
    UAH("₴"),
    EUR("€");

    private String mark;

    Currency (String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
      return  mark;
    }
}

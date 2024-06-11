package ru.gb.webServise.models.entities;

//агрегирует действующие продукты
public enum ProductName {
    CARD_PRIV("Карта привелегий"), CARD_VOZM("Карта возможностей");
    private String title;

    ProductName(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

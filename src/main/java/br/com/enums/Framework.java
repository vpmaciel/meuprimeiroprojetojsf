package br.com.enums;

public enum Framework {
    SPRING("Spring MVC"),
    HIBERNATE("Hibernate"),
    JPA("JPA"),
    PRIMEFACES("PrimeFaces"),
    JSF("JSF");

    private String descricao;

    Framework(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
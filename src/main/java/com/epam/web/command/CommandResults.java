package com.epam.web.command;

/**
 * Класс результата странички, в зависимости то условий которой выполняется редирект или переход на следующую страничку
 * с передачей запроса.
 */
public class CommandResults {
    private String page;
    private boolean isRedirect;

    private CommandResults(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public CommandResults(){}

    public static CommandResults forward(String page) {
        return new CommandResults(page, false);
    }

    public static CommandResults redirect(String page) {
        return new CommandResults(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}

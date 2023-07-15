package com.project.boardgamerentalfront.form;

import com.project.boardgamerentalfront.MainView;
import com.project.boardgamerentalfront.service.StatisticService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class StatisticForm extends FormLayout {
    private MainView mainView;
    private Button numberOfUsers = new Button("Number of users");
    private Button numberOfGames = new Button("Number of games");
    private Button numberOfAllRents= new Button("Number of all rents");
    private Button numberOfLastMonthRents = new Button("Number of last month rents");
    private Button numberOfLastWeekRents = new Button("Number of last week rents");
    private Button amountOfAllEarnedMoney = new Button("Amount of all earned money");
    private Button amountOfLastMonthEarnedMoney = new Button("Amount of last month earned money");
    private Button amountOfLastWeekEarnedMoney = new Button("Amount of last week earned money");

    private StatisticService statisticService = StatisticService.getInstance();

    public StatisticForm(MainView mainView) {
        VerticalLayout buttons = new VerticalLayout(numberOfUsers, numberOfGames, numberOfAllRents,
                numberOfLastMonthRents, numberOfLastWeekRents, amountOfAllEarnedMoney, amountOfLastMonthEarnedMoney,
                amountOfLastWeekEarnedMoney);
        add(buttons);
        this.mainView = mainView;
        numberOfUsers.addClickListener(event -> numberOfUsers());
        numberOfGames.addClickListener(event -> numberOfGames());
        numberOfAllRents.addClickListener(event -> numberOfAllRents());
        numberOfLastMonthRents.addClickListener(event -> numberOfLastMonthRents());
        numberOfLastWeekRents.addClickListener(event -> numberOfLastWeekRents());
        amountOfAllEarnedMoney.addClickListener(event -> amountOfAllEarnedMoney());
        amountOfLastMonthEarnedMoney.addClickListener(event -> amountOfLastMonthEarnedMoney());
        amountOfLastWeekEarnedMoney.addClickListener(event -> amountOfLastWeekEarnedMoney());
    }

    private void amountOfLastWeekEarnedMoney() {
        mainView.refreshInfoField("Amount of last week earned money: " +
                String.valueOf(statisticService.getAmountOfLastWeekEarnedMoney())+" "+mainView.getCurrentCurrency());
    }

    private void amountOfLastMonthEarnedMoney() {
        mainView.refreshInfoField("Amount of last month earned money: " +
                String.valueOf(statisticService.getAmountOfLastMonthEarnedMoney())+" "+mainView.getCurrentCurrency());
    }

    private void amountOfAllEarnedMoney() {
        mainView.refreshInfoField("Amount of all earned money: " +
                String.valueOf(statisticService.getAmountOfAllEarnedMoney())+" "+mainView.getCurrentCurrency());
    }

    private void numberOfLastWeekRents() {
        mainView.refreshInfoField("Number of last week rents: " +
                String.valueOf(statisticService.getNumberOfLastWeekRents()));
    }

    private void numberOfLastMonthRents() {
        mainView.refreshInfoField("Number of last month rents: " +
                String.valueOf(statisticService.getNumberOfLastMonthRents()));
    }

    private void numberOfAllRents() {
        mainView.refreshInfoField("Number of all rents: " +
                String.valueOf(statisticService.getNumberOfAllRents()));
    }

    private void numberOfGames() {
        mainView.refreshInfoField("Number of games: " +
                String.valueOf(statisticService.getNumberOfGames()));
    }

    private void numberOfUsers() {
        mainView.refreshInfoField("Number of users: " +
                String.valueOf(statisticService.getNumberOfUsers()));
    }
}

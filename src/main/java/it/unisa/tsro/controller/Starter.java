package it.unisa.tsro.controller;

import it.unisa.tsro.model.dao.TsroDao;

public class Starter {
    public static void main(String[] args) {
        TsroDao tsroDao = new TsroDao();
        tsroDao.recuperaRepositoryPerLaTabellaInIndex();
    }
}

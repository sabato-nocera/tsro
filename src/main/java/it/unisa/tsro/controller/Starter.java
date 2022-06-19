package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.SoftwareBean;
import it.unisa.tsro.model.dao.TsroDao;

import java.util.List;

public class Starter {
    public static void main(String[] args) {
        TsroDao tsroDao = new TsroDao();
        List<SoftwareBean> softwareList = tsroDao.recuperaRepositoryPerLaTabellaInIndex();
        for(SoftwareBean softwareBean : softwareList){
            System.out.println(softwareBean.getRepositoryName());
            System.out.println(tsroDao.recuperaRepositoryMiPiace(softwareBean.getRepositoryUrl()));
        }
    }
}

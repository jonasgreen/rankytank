package com.rankytank.client.service;

import com.jg.core.client.CoreServerService;

/**
 *
 */
public class RankyTankService extends CoreServerService {

    private static RankyTankService instance;

    private RankyTankService() {
    }

    public static RankyTankService getInstance() {
        if (instance == null) {
            instance = new RankyTankService();
        }
        return instance;
    }



}

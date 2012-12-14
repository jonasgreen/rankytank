package com.rankytank.server;

import com.jg.core.server.CoreServlet;
import com.jg.core.server.services.ActionHandlerRegistry;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceServlet extends CoreServlet {



    @Override
    protected void init(ActionHandlerRegistry registry) {
   //     registry.add(new CreateOnOffListHandler());

        //Reps for generic methods
    //    registry.add(new OnOffListRepository());

    }
}

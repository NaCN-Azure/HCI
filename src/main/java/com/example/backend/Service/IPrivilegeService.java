package com.example.backend.Service;

import com.example.backend.Model.Privilege;

public interface IPrivilegeService {

    /**
     * @param vid
     * @param bid
     * @return
     */
    Privilege getPrivilegeByVidAndBid(long vid, long bid);
}

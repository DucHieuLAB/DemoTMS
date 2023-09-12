package com.tms.api.scheduled;

import com.tms.api.service.BaseService;
import com.tms.api.service.FillterLeadService;
import com.tms.api.utils.PhoneHeper;
import com.tms.commons.DBResponse;
import com.tms.dao.BlackListDao;
import com.tms.dto.request.blacklist.GetBlackList;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.BlackList;
import com.tms.dto.response.LeadBasket;
import com.tms.dto.response.ScheduleUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LeadFillter extends BaseService {

    @Autowired
    BlackListDao blackListDao;
    @Autowired
    FillterLeadService fillterLeadService;

    @Scheduled(fixedDelay = 4000 )
    public void fillterInvalidLead() throws Exception{
        // Get List To Fillter
        GetLeadToFillter getLeadToFillter = new GetLeadToFillter();
        getLeadToFillter.setInAttribute3("0");
        List<LeadBasket> leadBaskets  = fillterLeadService.getListToFillter( getLeadToFillter );

        // Get Black List
        GetBlackList getBlackList = new GetBlackList();
        DBResponse<List<BlackList>> blackLists = blackListDao.getBlackList(sessionId,getBlackList);

        // declare variable
        UpdLeadFillter invalidLeadByPhone = new UpdLeadFillter();

        //Begin fillter invalid phone
        UpdLeadFillter uppdate ;
        List<Integer> leadsInvalidPhone = new ArrayList<>();
        for (LeadBasket l : leadBaskets){
            // Check phone num valid
            if (!PhoneHeper.checkPhoneValid(l.getPhone())){
                l.setStatus(5);
                l.setComment("Nghĩ cách config chỗ này");
                continue;
            }
            // check in blacklist
            if(isInBlackList(l,blackLists)){
                l.setStatus(5);
                l.setComment("Nghĩ cách config chỗ này");
                continue;
            }
        }
        // Check 
    }

    private boolean isInBlackList(LeadBasket l, DBResponse<List<BlackList>> blackLists) {
        List<BlackList> list = blackLists.getResult();
        // if list is empty return false
        if (list == null ||list.size() == 0){
            return false;
        }

        for (BlackList blackList : list){
            if (l.getLeadId() == blackList.getLeadId()){
                return true;
            }
        }
        return false;
    }


}

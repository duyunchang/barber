
package com.geekcattle.service.console;

import com.geekcattle.domain.entity.BaseEntity;
import com.geekcattle.domain.entity.console.Log;
import com.geekcattle.manager.console.LogMapper;
import com.geekcattle.util.DateUtil;
import com.geekcattle.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author 
 */
@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;
    
    @Autowired 
    private BaseEntity baseEntity;

    public List<Log> getPageList(Log log) {
    	Sort sort = new Sort(Sort.Direction.DESC, "logTime");  
    	Pageable pageable = new PageRequest(baseEntity.getOffset(), baseEntity.getLimit(), sort); 
    	
        return logMapper.findAll(pageable).getContent();
        
//        PageHelper.offsetPage(log.getOffset(), log.getLimit(), CamelCaseUtil.toUnderlineName(log.getSort())+" "+log.getOrder());
//        return logMapper.selectAll();
    }

    public void insert(Log log){
        logMapper.save(log);
    }
    
    public void insertLoginLog(String username, String ip, String action){
        Log  log = new Log();
        log.setLogId(UuidUtil.getUUID());
        log.setLogUser(username);
        log.setLogTime(DateUtil.getCurrentTime());
        log.setLogIp(ip);
        log.setLogAction(action);
        this.insert(log);
    }


}
